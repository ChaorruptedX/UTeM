package com.kiosk;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import com.kiosk.models.ItemProduct;

public class KioskClient2_reciept extends JFrame {
    
    String str;
    ResultSet rs;
    Vector records;
    GridBagLayout gb1;
    GridBagConstraints gbc;
    JScrollPane sp;
    JTextArea result;
    JLabel label;
    JFrame f;
    Publisher pub;
    int i=0;
    ObjectInputStream br = null;
    Socket clientSocket = null;
    ArrayList<ItemProduct> listItemProduct = new ArrayList<ItemProduct>();
    ItemProduct itemProduct = null;
    
    @SuppressWarnings("unchecked")
	public KioskClient2_reciept()
    {
    	
        label = new JLabel("Product Details");
        result = new JTextArea(20,60);
        str = "";
        pub = null;
        records = new Vector();
        gb1 = new GridBagLayout();
        gbc = new GridBagConstraints();
        getContentPane().setLayout(gb1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(label,gbc);
          gbc.gridx = 0;
        gbc.gridy = 1;
        sp = new JScrollPane(result);
        getContentPane().add(sp,gbc);
        
            try {
                clientSocket = new Socket("localhost",1400);
                DataOutputStream outToServer  = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeUTF("getItems");

                  br = new ObjectInputStream(clientSocket.getInputStream());
                  listItemProduct = (ArrayList<ItemProduct>) br.readObject();
                  br.close();
                  result.setText("");
                  int i =0;
                  
                  result.append("ID\tPName\t\tPrice");
                  result.append("\n-----------------------------------------------------------------------\n");
                  
                  
                  while(i < listItemProduct.size())
                  {
                	  itemProduct = listItemProduct.get(i);
                      str = String.valueOf(itemProduct.getItemProduct());
                      result.append(str + "\t");
                         str =itemProduct.getName();
                      result.append(str + "\t\t");
                           str = String.valueOf(itemProduct.getPrice());
                      result.append(str + "\n"); 
                      i++;
                      
            
                      
                  }
                  records.removeAllElements();
                  
                  
                    
            } catch (IOException ex) {
                Logger.getLogger(KioskClient2_reciept.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(KioskClient2_reciept.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<ItemProduct> getItems(){
    	ArrayList<ItemProduct> listItemProduct = new ArrayList<ItemProduct>();
         try {
        	DataOutputStream outToServer  = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeUTF("getItems");
			
			 br = new ObjectInputStream(clientSocket.getInputStream());
             listItemProduct = (ArrayList<ItemProduct>) br.readObject();
             br.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return listItemProduct;
    }
    public static void main(String[ ] args)
	{
		KioskClient2_reciept server1=new KioskClient2_reciept();
      
                server1.setSize(500, 300); 
                server1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                server1.pack(); 
                server1.setVisible(true); 
                
	     }
}