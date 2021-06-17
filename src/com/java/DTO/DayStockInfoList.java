package com.java.DTO;

import com.java.DAO.DBConnecter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class DayStockInfoList {
	private static ArrayList<DayStockInfo> list = new ArrayList<DayStockInfo>();
	public static ArrayList<DayStockInfo> getList() {
		refresh();
		return list;
	}
	private static void refresh() {
		DBConnecter connector = new DBConnecter();
		Connection conn = connector.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from benefitmaker.daystockinfo");
			while(rs.next()) {
				String Code = rs.getString(1);
				Timestamp Date = rs.getTimestamp(2);
				if(isExist(Code,Date)) {
					continue;
				}
				int Start = rs.getInt(3);
				int Close = rs.getInt(4);
				int AdjClose = rs.getInt(5);
				int Volume = rs.getInt(6);
				int High = rs.getInt(7);
				int Low = rs.getInt(8);
				DayStockInfo sTemp = new DayStockInfo(Code, Date, Start, Close, AdjClose, Volume, High, Low);
				list.add(sTemp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static boolean isExist(String Code, Timestamp Date) {
		for(int i=0; i < list.size(); i++) {
			if((list.get(i).getCode().equals(Code)) && (list.get(i).getDate() == Date)) {
				return true;
			}
		}
		return false;
	}
}
