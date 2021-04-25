package com.kiosk;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import com.kiosk.models.Card;
import com.kiosk.models.ItemProduct;
import com.kiosk.models.Message;
import com.kiosk.models.Order;
import com.kiosk.models.OrderedItem;

public class CardServer extends Thread {

	Statement stmt = null;
	ResultSet rs = null;
	ServerSocket server = null;
	Socket client = null;
	Connection con = null;
	Database db = new Database();

	public CardServer() {
		try {
			server = new ServerSocket(1401);
			System.out.println("Starting the Card Server");
			start();
		} catch (IOException ex) {
			Logger.getLogger(CardServer.class.getName()).log(Level.SEVERE, null, ex);
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
				case "validateCard":
					Card card = (Card) msg.getObj();
					card = checkCard(card);
					
					Message serverMsg = new Message();
					serverMsg.setRequest(card.getStatus());
					serverMsg.setObj(card);
					os.writeObject(serverMsg);
					break;
				case "end":
					// act as client and send card no to credit card authorization server
					break;
				}

				System.out.println("OutputStream received");

			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(CardServer.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	public Card checkCard(Card card) {
		try {
			stmt = Database.getConCard().createStatement();
			
			String cardNo = card.getCardNo1()+card.getCardNo2()+card.getCardNo3()+card.getCardNo4();
			
			rs = stmt.executeQuery("select * from card WHERE card_no='"+cardNo+"' AND expired_date='"+card.getExpired()+"'");
			
			card.setStatus("false");
			while (rs.next()) {
				card.setStatus("true");
				System.out.println("card valid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return card;
	}

	public static void main(String args[]) {
		new CardServer();

	}
}