package com.java.DTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.DAO.DBConnecter;

public class CompanyList {
	private static ArrayList<Company> list= new ArrayList<Company>();
	private static boolean isFirst = true;
	public static ArrayList<Company> getList() {
		if(isFirst == true) {
			refresh();
			isFirst = false;
		}
		return list;
	}
	private static void refresh() {
		DBConnecter driver = new DBConnecter();
		Connection conn = driver.getConnection();
		Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from companylist");
				while(rs.next()) {
					String Code = rs.getString(1);
					String Name = rs.getString(2);
					String token = "";
					int length = Code.length();
					if(length != 6) {
						for(int i=0; i < (6-length); i++) {
							token += "0";
						}
						Code = token + Code;
					}
					Company cTemp = new Company(Code,Name);
					list.add(cTemp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private static boolean isExist(String Code) {
		for(int i=0; i < list.size(); i++) {
			if(list.get(i).getCode().equals(Code)) {
				return true;
			}
		}
		return false;
	}
}
