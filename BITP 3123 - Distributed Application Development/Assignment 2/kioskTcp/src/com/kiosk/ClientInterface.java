package com.kiosk;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.kiosk.models.ItemProduct;
import com.kiosk.models.Message;
import com.kiosk.models.Order;
import com.kiosk.models.OrderedItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class ClientInterface extends javax.swing.JFrame {
	private JTextField txtName;
	private JTextField txtPrice;
	private JTable table;
	private JTable table_1;

	private KioskClient kioskClient;
	private ArrayList<ItemProduct> listItemProduct;
	private ArrayList<OrderedItem> listOrderedItem;
	private ItemProduct currentItemProduct = null;
	private Order order = null;
	private Order confirmOrder = null;

	ClientInterface() {		
		kioskClient = new KioskClient();
		ArrayList<OrderedItem> listOrderedItem = new ArrayList<OrderedItem>();

		JFrame f = new JFrame("CheckBox Example");
		f.setSize(917, 581);
		f.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Qty");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(398, 218, 45, 21);
		f.getContentPane().add(lblNewLabel_1);

		JSpinner TxtQty = new JSpinner();
		TxtQty.setBounds(527, 221, 70, 20);
		f.getContentPane().add(TxtQty);

		JLabel lblNewLabel_2 = new JLabel("Item Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(398, 132, 103, 13);
		f.getContentPane().add(lblNewLabel_2);

		txtName = new JTextField();
		txtName.setBounds(527, 131, 96, 19);
		f.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(527, 176, 96, 19);
		f.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(398, 177, 70, 13);
		f.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Print Receipt");
		btnNewButton_1.setBounds(483, 469, 103, 41);
		f.getContentPane().add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 10, 883, 85);
		f.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Food Ordering System");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));

		JLabel lblNewLabel_5 = new JLabel("Your Order");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(574, 306, 103, 13);
		f.getContentPane().add(lblNewLabel_5);

		JButton btnNewButton_6 = new JButton("Confirm");
		btnNewButton_6.setBounds(689, 469, 85, 41);
		f.getContentPane().add(btnNewButton_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 122, 311, 354);
		f.getContentPane().add(scrollPane);

		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);

		DefaultTableModel tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		tableModel.addColumn("Name");
		tableModel.addColumn("Price");

		listItemProduct = kioskClient.getItems();

		int i = 0;

		while (i < listItemProduct.size()) {
			ItemProduct itemProduct = listItemProduct.get(i);
			tableModel.insertRow(i, new Object[] { itemProduct.getName(), String.valueOf(itemProduct.getPrice()) });
			i++;
		}
		
	    table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        String selectedData = null;

	        int selectedRow = table.getSelectedRow();
        //int[] selectedColumns = table.getSelectedColumns();
	        ItemProduct itemProduct = listItemProduct.get(selectedRow);
	        currentItemProduct = itemProduct;
	        
	        txtName.setText(itemProduct.getName());
	        txtPrice.setText(String.valueOf(itemProduct.getPrice()));
	        
	        System.out.println("Selected: " + selectedRow);
	      }

	    });

		panel_1.add(new JScrollPane(table));

		JButton btnNewButton = new JButton("Add To cart");
		btnNewButton.setBounds(659, 220, 103, 21);
		f.getContentPane().add(btnNewButton);
		
		DefaultTableModel tableModelOrder = new DefaultTableModel();
		
		btnNewButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            String itemName = txtName.getText();
	            Float price = Float.valueOf(txtPrice.getText());
	            int quantity = Integer.valueOf((Integer)TxtQty.getValue());
	            Float subtotal = price*quantity;
	            
	            //insert orderItem
	            OrderedItem orderedItem = new OrderedItem();
	            orderedItem.setItemProduct(currentItemProduct);
	            orderedItem.setQuantity(quantity);
	            orderedItem.setSubTotalAmount(subtotal);
	            
	            listOrderedItem.add(orderedItem);
	            
	            tableModelOrder.insertRow(tableModelOrder.getRowCount(), new Object[] { currentItemProduct.getName(), String.valueOf(currentItemProduct.getPrice()), String.valueOf(quantity), String.valueOf(subtotal) });
	         }
	      });
		
		//button confirm listener
		btnNewButton_6.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 btnNewButton.setEnabled(false);
	        	// initialize a Random object somewhere; you should only need one
	        	 Random random = new Random();

	        	 // generate a random integer from 0 to 899, then add 100
	        	 int x = random.nextInt(900) + 100;
	        	 String timeStamp = new SimpleDateFormat("yyMMdd").format(new java.util.Date());
	        	 timeStamp = timeStamp + String.valueOf(x);
	        	 
	        	 float totalAmount = (float) 0;
	        	 for(OrderedItem orderedItem : listOrderedItem){
	        		 totalAmount += orderedItem.getSubTotalAmount();
	        	 }
	        	 
	        	 //send to server
	        	 order = new Order();
	        	 order.setOrderedItems(listOrderedItem);
	        	 order.setTotalAmount(totalAmount);
	        	 order.setOrderReferenceNumber(Integer.valueOf(timeStamp));
	        	 Message out = kioskClient.saveOrder(order);
	        	 confirmOrder = (Order)out.getObj();
	        	 
	        	 JOptionPane.showMessageDialog(null,out.getRequest());
	        	 
	        	 Payment payment = new Payment(confirmOrder, kioskClient);
	         }
	      });

		
		table_1 = new JTable(tableModelOrder);
		tableModelOrder.addColumn("Name");
		tableModelOrder.addColumn("Price");
		tableModelOrder.addColumn("Quantity");
		tableModelOrder.addColumn("Subtotal");
		table_1.setBounds(380, 294, 468, 155);
		f.getContentPane().add(table_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(380, 105, 468, 164);
		f.getContentPane().add(panel_2);
		f.setVisible(true);
	}

	public static void main(String args[]) {
		new ClientInterface();
	}
}