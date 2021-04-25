package com.kiosk;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import com.kiosk.models.Card;
import com.kiosk.models.ItemProduct;
import com.kiosk.models.Message;
import com.kiosk.models.Order;
import com.kiosk.models.OrderedItem;

public class KioskClient {

	String str;
	ResultSet rs;
	GridBagLayout gb1;
	GridBagConstraints gbc;
	JScrollPane sp;
	JTextArea result;
	JLabel label;
	JFrame f;
	Publisher pub;
	int i = 0;
	ObjectInputStream br = null;
	Socket clientSocket = null;
	ArrayList<ItemProduct> listItemProduct = new ArrayList<ItemProduct>();
	ItemProduct itemProduct = null;

	public KioskClient() {

		try {
			clientSocket = new Socket("localhost", 1400);

		} catch (IOException ex) {
			Logger.getLogger(KioskClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public void connectSocket() {
		try {
			clientSocket = new Socket("localhost", 1400);

		} catch (IOException ex) {
			Logger.getLogger(KioskClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ItemProduct> getItems() {
		ArrayList<ItemProduct> listItemProduct = new ArrayList<ItemProduct>();
		try {
			ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
			
			Message msg = new Message();
			msg.setRequest("getItems");
			
			os.writeObject(msg);

			//br = new ObjectInputStream(clientSocket.getInputStream());
			listItemProduct = (ArrayList<ItemProduct>) is.readObject();
			os.close();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemProduct;
	}
	
	public Message saveOrder(Order order) {
		Message serverMsg=null;
		try {
			if(clientSocket.isClosed())
				connectSocket();
			
			ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
			
			Message msg = new Message();
			msg.setRequest("saveOrder");
			msg.setObj(order);
			
			os.writeObject(msg);
			
			serverMsg = (Message)is.readObject();
			
			os.close();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverMsg;
	}
	
	public Message template(Order order) {
		Message serverMsg=null;
		try {
			if(clientSocket.isClosed())
				connectSocket();
			
			ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
			
			Message msg = new Message();
			msg.setRequest("saveOrder");
			msg.setObj(order);
			
			os.writeObject(msg);
			
			serverMsg = (Message)is.readObject();
			
			os.close();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverMsg;
	}
	
	public Message processPayment(Card card, Order order) {
		Message out = new Message();
		
		try {
			if(clientSocket.isClosed())
				connectSocket();
			
			ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
			
			ArrayList<Object> listObj = new ArrayList<Object>();
			listObj.add(card);
			listObj.add(order);
			
			Message msg = new Message();
			msg.setRequest("processPayment");
			msg.setObj(listObj);
			
			os.writeObject(msg);
			
			out = (Message)is.readObject();
			
			//test
			out.setRequest("true");
			
			os.close();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}

	public static void main(String[] args) {
		KioskClient server1 = new KioskClient();

	}
}