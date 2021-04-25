package com.kiosk.models;

import java.awt.List;
import java.util.ArrayList;

public class Order implements java.io.Serializable{
	
	// Declaration of attributes
	
	private int orderId;
	
	// Implementation of 1:M
	private ArrayList<OrderedItem> orderedItems;
	
	private float totalAmount;
	private int orderReferenceNumber;
	
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderedItems
	 */
	public ArrayList<OrderedItem> getOrderedItems() {
		return orderedItems;
	}
	
	/**
	 * @param orderedItems the orderedItems to set
	 */
	public void setOrderedItems(ArrayList<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}
	
	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * @return the orderReferenceNumber
	 */
	public int getOrderReferenceNumber() {
		return orderReferenceNumber;
	}
	
	/**
	 * @param orderReferenceNumber the orderReferenceNumber to set
	 */
	public void setOrderReferenceNumber(int orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}
	
	
	
	
	

}