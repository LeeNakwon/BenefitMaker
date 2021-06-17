package com.java.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class BookmarkList {
	private static ArrayList<Bookmark> list = new ArrayList<Bookmark>();
	private static String UserID;

	public static ArrayList<Bookmark> getList() {
		refresh();
		return list;
	}

	public static boolean isExist(String Name, HttpSession session) {
		UserList.refresh();
		boolean isSame = UserList.IsSessionSame(session.getId());
		boolean result = false;
		UserID = UserList.getBySessionID(session.getId()).getID();
		if (isSame == true) {
			ArrayList<Bookmark> temp = getList();
			for (int i = 0; i < temp.size(); i++) {
				if((temp.get(i).getUserID().equals(UserID) && temp.get(i).getCoName().equals(Name))) {
					result = true;
				}
			}
		}
		return result;
	}

	public static void insertBookmark(String Code, HttpSession session) {
		UserList.refresh();
		boolean isSame = UserList.IsSessionSame(session.getId());
		UserID = UserList.getBySessionID(session.getId()).getID();
		if (isSame == true) {
			insert(UserID, Code);
		}
	}

	public static void deleteBookmark(String Code, HttpSession session) {
		ArrayList<User> temp = com.java.DTO.UserList.getList();

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getSessionID().equals(session.getId())) {
				delete(UserID, Code);
			}
		}
	}

	public static void insert(String ID, String companyCode) {
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn;
		PreparedStatement pstmt;
		String realCode = null;
		try {
			conn = driver.getConnection();
			ArrayList<Company> list = CompanyList.getList();
			for (Company c : list) {
				if (c.getCode().equals(companyCode)) {
					pstmt = conn.prepareStatement("select Code from companylist where Name = ?");
					pstmt.setString(1, c.getName());
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						realCode = rs.getString(1);
					}
					pstmt = conn.prepareStatement("insert into bookmark (ID, Code) values(?, ?)");
					pstmt.setString(1, ID);
					pstmt.setString(2, realCode);
					pstmt.executeUpdate();
					System.out.println("insert success");
					pstmt.close();
					refresh();
					break;
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delete(String ID, String companyCode) {
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn;
		PreparedStatement pstmt;
		String realCode = null;
		try {
			conn = driver.getConnection();
			ArrayList<Company> list = CompanyList.getList();
			for (Company c : list) {
				if (c.getCode().equals(companyCode)) {
					pstmt = conn.prepareStatement("select Code from companylist where Name = ?");
					pstmt.setString(1, c.getName());
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						realCode = rs.getString(1);
					}
					pstmt = conn.prepareStatement("delete from bookmark where ID = ? and Code = ?");
					pstmt.setString(1, ID);
					pstmt.setString(2, realCode);
					pstmt.executeUpdate();
					System.out.println("delete success");
					pstmt.close();
					refresh();
					break;
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void refresh() {
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn;
		Statement stmt;
		try {
			conn = driver.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select ID, Name from bookmark, companylist where bookmark.Code = companylist.Code");
			while (rs.next()) {
				String ID = rs.getString(1);
				String Name = rs.getString(2);
				String SessionID = "";
				Bookmark tBookmark = new Bookmark(ID, Name, SessionID);
				list.add(tBookmark);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}