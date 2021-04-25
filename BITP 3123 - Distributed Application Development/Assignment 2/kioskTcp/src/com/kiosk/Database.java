package com.kiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	 private static Connection con = null;
	 private static Connection conCard = null;
	 
	 public static Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		Database.con = con;
	}
	
	public static Connection getConCard() {
		return conCard;
	}

	public void setConCard(Connection conCard) {
		Database.conCard = conCard;
	}

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 this.setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/kioskappdb_dev","root",""));
			 this.setConCard(DriverManager.getConnection("jdbc:mysql://localhost:3306/cardappdb_dev","root",""));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	 }
}
