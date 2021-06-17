package com.java.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.DAO.DBConnecter;

public class UserList {
	private static ArrayList<User> list = new ArrayList<User>();

	public static ArrayList<User> getList() {
		refresh();
		return list;
	}

	
	public static User getBySessionID(String SessionID) {
		for(int i=0; i < list.size(); i++) {
			if(list.get(i).getSessionID().equals(SessionID)) {
				return list.get(i);
			}
		}
		return null;
	}
	
	public static boolean changeCapital(String UserID, int value) {
		DBConnecter driver = new DBConnecter();
		Connection conn = driver.getConnection();
		User temp = null;
		for(User u : list) {
			if(u.getID().equals(UserID)) {
				temp = u;
			}
		}
		value += temp.getCapitalCurrent();
		String SQL = "UPDATE `benefitmaker`.`user` SET `CapitalCurrent` = ? WHERE (`ID` = ?);";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, UserID);
			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean IsSessionSame(String SessionID) {
		for(User u : list) {
			if(u.getSessionID().equals(SessionID)) {
				return true;
			}
		}
		return false;
	}
	
	public static void refresh() {
		ArrayList<User> newList = new ArrayList<User>();
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from User");
			while (rs.next()) {
				String ID = rs.getString(1);
				String PW = rs.getString(2);
				long Capital1st = rs.getLong(3);
				long CapitalCurrent = rs.getLong(4);
				String NickName = rs.getString(5);
				int RankPer = rs.getInt(6);
				int RankAmt = rs.getInt(7);
				String Birthday = rs.getString(8);
				String Email = rs.getString(9);
				String SessionID = rs.getString(10);
				int IsActive = rs.getInt(11);
				double Per = rs.getDouble(12);
				long Amt = rs.getLong(13);

				if(IsActive == 0) {
					continue;
				}
				
				User tUser = new User(ID, PW, Capital1st, CapitalCurrent, NickName, RankPer, RankAmt, Birthday, Email,
						SessionID, IsActive, Per, Amt);
				newList.add(tUser);
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
