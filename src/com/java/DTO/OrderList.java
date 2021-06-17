package com.java.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.DAO.DBConnecter;

public class OrderList {
	private static ArrayList<Order> list = new ArrayList<Order>();
	public static ArrayList<Order> getList() {
		refresh();
		return list;
	}
	
	public static String delete(String OrderID, String UserID) {
		DBConnecter driver = new DBConnecter();
		Connection conn = driver.getConnection();
		PreparedStatement pstmt;
		Order order = null;
		int value = 0;
		for(Order o : OrderList.getList()) {
			if(o.getOrderID().equals(OrderID)) {
				order = o;
				break;
			}
		}
		if(order.getOrderType().startsWith("Buy")) {
			value = order.getPrice() * order.getRemainVolume();
			UserList.changeCapital(UserID, value);
		}
		String SQL = "UPDATE `benefitmaker`.`orderlist` SET `IsConcluded` = '1' WHERE (`OrderID` = ?) and (`UserID` = ?);";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, OrderID);
			pstmt.setString(2, UserID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	public static void refresh() {
		ArrayList<Order> newList = new ArrayList<Order>();
		DBConnecter driver = new DBConnecter();
		Connection conn = driver.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from orderlist");
			while (rs.next()) {
				String OrderID = rs.getString(1);
				String UserID = rs.getString(2);
				String OrderType = rs.getString(3);
				int OrderedVolume = rs.getInt(4);
				int RemainVolume = rs.getInt(5);
				String DateTime = rs.getString(6);
				String CoName = rs.getString(7);
				int IsConcluded = rs.getInt(8);
				int Price = rs.getInt(9);

				Order tOrder = new Order(OrderID, UserID, OrderType, OrderedVolume, RemainVolume, DateTime, CoName
						,IsConcluded, Price);
				newList.add(tOrder);
			}
			if (stmt != null && !stmt.isClosed()) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("refresh!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = newList;
	}
}

