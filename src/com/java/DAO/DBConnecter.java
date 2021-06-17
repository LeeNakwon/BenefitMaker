package com.java.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecter {
	public Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/benefitmaker?allowPublicKeyRetrieval=true&useSSL=false";
			conn = DriverManager.getConnection(url,"root","1234");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
