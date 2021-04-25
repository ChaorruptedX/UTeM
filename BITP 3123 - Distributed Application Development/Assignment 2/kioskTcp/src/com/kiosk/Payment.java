package com.kiosk;

import javax.swing.*;

import com.kiosk.models.Card;
import com.kiosk.models.Message;
import com.kiosk.models.Order;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;  

public class Payment  extends javax.swing.JFrame
{
	private JTextField txtPay;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private Order order;
	private KioskClient kioskClient;
	
     Payment(Order order, KioskClient kioskClient){  
    	
    	 this.order = order;
    	 this.kioskClient = kioskClient;
    	 
        JFrame f= new JFrame("CheckBox Example");
        f.setSize(917,581);  
        f.getContentPane().setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Total Amount");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(331, 322, 103, 13);
        f.getContentPane().add(lblNewLabel_2);
        
        txtPay = new JTextField();
        txtPay.setBounds(331, 345, 96, 19);
        txtPay.setText(String.valueOf(order.getTotalAmount()));
        f.getContentPane().add(txtPay);
        txtPay.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Enter Your Card Number");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(297, 182, 209, 13);
        f.getContentPane().add(lblNewLabel_3);
        
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBounds(274, 407, 91, 41);
        f.getContentPane().add(btnNewButton_1);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(10, 10, 883, 85);
        f.getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Food Ordering System");
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        
        JButton btnNewButton_6 = new JButton("Pay");
        btnNewButton_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Card card = new Card();
        		card.setCardNo1(textField_1.getText());
        		card.setCardNo2(textField_2.getText());
        		card.setCardNo3(textField_3.getText());
        		card.setCardNo4(textField_4.getText());
        		card.setExpired(textField.getText());
        		card.setTotalAmount(order.getTotalAmount());
        		
        		Message fromServer = kioskClient.processPayment(card, order);
        		String status = "Unsuccessful";
        		if(fromServer.getRequest().equals("true")){
        			status = "Successful";
        		}
        		JOptionPane.showMessageDialog(null, "Payment is "+status);
        		f.dispose();
        	}
        });
        btnNewButton_6.setBounds(421, 407, 85, 41);
        f.getContentPane().add(btnNewButton_6);
        
        textField_1 = new JTextField();
        textField_1.setBounds(386, 221, 45, 19);
        f.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setBounds(331, 221, 45, 19);
        f.getContentPane().add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(274, 221, 45, 19);
        f.getContentPane().add(textField_3);
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setBounds(441, 221, 39, 19);
        f.getContentPane().add(textField_4);
        textField_4.setColumns(10);
        
        textField = new JTextField();
        textField.setBounds(331, 293, 96, 19);
        f.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Expired Date");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(331, 266, 96, 13);
        f.getContentPane().add(lblNewLabel_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(224, 138, 319, 361);
        f.getContentPane().add(panel_1);
        f.setVisible(true);  
     }  
public static void main(String args[])  
    {  
    //new Payment();  
    }	
}  