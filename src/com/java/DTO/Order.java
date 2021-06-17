package com.java.DTO;

public class Order {
	private String OrderID, UserID, OrderType, CoName, DateTime;
	private int OrderedVolume, RemainVolume, IsConcluded, Price;
	
	public Order(String OrderID, String UserID, String OrderType, int OrderedVolume, int RemainVolume,
			String DateTime, String CoName, int IsConcluded, int Price) {
		this.OrderID = OrderID; this.UserID = UserID; this.OrderType = OrderType; this.CoName = CoName;
		this.DateTime = DateTime; this.OrderedVolume = OrderedVolume; this.RemainVolume = RemainVolume; 
		this.IsConcluded = IsConcluded; this.Price = Price;
	}
	
	public String getOrderID() {return OrderID;}
	public void setOrderID(String orderID) {this.OrderID = orderID;}
	
	public String getUserID() {return UserID;}
	public void setUserID(String userID) {this.UserID = userID;}
	
	public String getOrderType() {return OrderType;}
	public void setOrderType(String orderType) {this.OrderType = orderType;}
	
	public int getOrderedVolume() {return OrderedVolume;}
	public void setOrderedVolume(int orderedVolume) {this.OrderedVolume = orderedVolume;}
	
	public int getRemainVolume() {return RemainVolume;}
	public void setRemainVolume(int remainVolume) {this.RemainVolume = remainVolume;}
	
	public String getDateTime() {return DateTime;}
	public void setDateTime(String dateTime) {this.DateTime = dateTime;}
	
	public String getCoName() {return CoName;}
	public void setCoName(String coName) {this.CoName = coName;}
	
	public int getIsConcluded() {return IsConcluded;}
	public void setIsConcluded(int isConcluded) {this.IsConcluded = isConcluded;}
	
	public int getPrice() {return Price;}
	public void setPrice(int price) {this.Price = price;}
	}
