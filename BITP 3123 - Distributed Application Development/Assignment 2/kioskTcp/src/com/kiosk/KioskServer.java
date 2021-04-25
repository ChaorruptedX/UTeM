package com.kiosk;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import com.kiosk.models.Card;
import com.kiosk.models.ItemProduct;
import com.kiosk.models.Message;
import com.kiosk.models.Order;
import com.kiosk.models.OrderTransaction;
import com.kiosk.models.OrderedItem;

public class KioskServer extends Thread {

	Statement stmt = null;
	ResultSet rs = null;
	ServerSocket server = null;
	Socket client = null;
	Connection con = null;
	Database db = new Database();

	public KioskServer() {
		try {
			server = new ServerSocket(1400);
			System.out.println("Starting the Server");
			start();
		} catch (IOException ex) {
			Logger.getLogger(KioskServer.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@SuppressWarnings("unchecked")
	public void run() {
		while (true) {
			try {
				client = server.accept();
				System.out.println("Connection accepted");
				
				ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream is = new ObjectInputStream(client.getInputStream());
				//DataInputStream inputFromClient = new DataInputStream(client.getInputStream());
				
				Message msg = (Message) is.readObject();
				//String request = inputFromClient.readUTF();
				String request = msg.getRequest();
				
				//String[] arrSplit = request.split("|");
				
				switch (request) {
				case "getItems":
					ArrayList<ItemProduct> listItemProduct = getItems();
					os.writeObject(listItemProduct);
					break;
				case "saveOrder":
					Order order = (Order) msg.getObj();
					order = saveOrder(order);
					
					Message serverMsg = new Message();
					serverMsg.setRequest("save");
					serverMsg.setObj(order);
					os.writeObject(serverMsg);
					break;
				case "processPayment":
					// act as client and send card no to credit card authorization server
					ArrayList<Object> listObj = (ArrayList<Object>)msg.getObj();
					Card card = (Card) listObj.get(0);
					Order confirmOrder = (Order) listObj.get(1);
					
					try {
						//connect card server to check credit card
						Socket cs = new Socket("localhost", 1401);
						
						ObjectOutputStream csos = new ObjectOutputStream(cs.getOutputStream());
						ObjectInputStream csis = new ObjectInputStream(cs.getInputStream());
						
						Message toCardServer = new Message();
						toCardServer.setRequest("validateCard");
						toCardServer.setObj(card);
						csos.writeObject(toCardServer);
						
						Message fromCardServer = (Message)csis.readObject();
										
						String paymentStatus = fromCardServer.getRequest();
						
						OrderTransaction orderTransaction = saveOrderTransaction(paymentStatus, card, confirmOrder);
						
						Message frmServer = new Message();
						frmServer.setRequest(fromCardServer.getRequest());
						frmServer.setObj(orderTransaction);
						os.writeObject(frmServer);

					} catch (IOException ex) {
						Logger.getLogger(KioskClient.class.getName()).log(Level.SEVERE, null, ex);
					}
					
					break;
				}

				System.out.println("OutputStream received");

			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(KioskServer.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	public ArrayList<ItemProduct> getItems() {
		ArrayList<ItemProduct> listItemProduct = new ArrayList<ItemProduct>();
		try {
			stmt = Database.getCon().createStatement();

			rs = stmt.executeQuery("select * from itemproduct");

			while (rs.next()) {
				ItemProduct itemProduct = new ItemProduct();
				itemProduct.setItemProduct(rs.getInt(1));
				itemProduct.setName(rs.getString(2));
				itemProduct.setPrice(rs.getFloat(3));
				listItemProduct.add(itemProduct);
				System.out.println("row returned");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemProduct;
	}

	public Order saveOrder(Order order) {
		String sql_insert = "INSERT INTO `order` (TotalAmount, OrderReferenceNumber) VALUES (?,?)";
		try {
			PreparedStatement statement = Database.getCon().prepareStatement(sql_insert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setFloat(1, order.getTotalAmount());
			statement.setInt(2, order.getOrderReferenceNumber());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating order failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					order.setOrderId(generatedKeys.getInt(1));
					
					int i =0;
					for(OrderedItem orderedItem : order.getOrderedItems()){
						OrderedItem curOrderedItem = saveOrderedItem(order.getOrderId(), orderedItem);
						order.getOrderedItems().set(i, curOrderedItem);
						i++;
					};
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public OrderedItem saveOrderedItem(int orderId, OrderedItem orderedItem) {
		String sql_insert = "INSERT INTO ordereditem (ItemProduct, Quantity, SubTotalAmount, `Order`) VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = Database.getCon().prepareStatement(sql_insert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, orderedItem.getItemProduct().getItemProduct());
			statement.setInt(2, orderedItem.getQuantity());
			statement.setFloat(3, orderedItem.getSubTotalAmount());
			statement.setInt(4, orderId);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating ordered item failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					orderedItem.setOrderedItem(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating ordered item failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderedItem;
	}
	
	public OrderTransaction saveOrderTransaction(String status, Card card, Order order) {
		OrderTransaction orderTransaction = null;
		String sql_insert = "INSERT INTO ordertransaction (TransactionDate, `Order`, AmountCharged, TransactionStatus, Last4Digits, OrderMode) VALUES (?,?,?,?,?,?)";
		try {
			java.util.Date utilDate = new java.util.Date();
			
			PreparedStatement statement = Database.getCon().prepareStatement(sql_insert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, new java.sql.Date(utilDate.getTime()));
			statement.setInt(2, order.getOrderId());
			statement.setFloat(3, order.getTotalAmount());
			statement.setString(4, status);
			statement.setString(5, card.getCardNo4());
			statement.setString(6, "");

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating ordered item failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					
					orderTransaction.setOrderTransactionId(generatedKeys.getInt(1));
					orderTransaction.setAmountCharged(order.getTotalAmount());
					orderTransaction.setLast4Digits(Integer.valueOf(card.getCardNo4()));
					orderTransaction.setTransactioDate(new java.sql.Date(utilDate.getTime()));
					orderTransaction.setOrderMode("");
					orderTransaction.setTransactionStatus(status);
					
				} else {
					throw new SQLException("Creating ordered item failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderTransaction;
	}

	public static void main(String args[]) {
		new KioskServer();

	}
}