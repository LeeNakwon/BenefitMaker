package com.java.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.java.DTO.Company;
import com.java.DTO.Order;
import com.java.DTO.User;

public class Conclude extends Thread{
	ArrayList<Company> CTemp = com.java.DTO.CompanyList.getList();
	ArrayList<Order> OTemp = com.java.DTO.OrderList.getList();
	ArrayList<User> UTemp = com.java.DTO.UserList.getList();
	public int start = 0;
	public int end = 0;
	
	@Override
	public void run() {
		while(true) {
			crawl(start, end);	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("123123123");
				e.printStackTrace();
			}
		}
	}
	
	public void crawl(int start, int end) {
		for (int i = start; i < end; i++) {
			String Code = CTemp.get(i).getCode();
			String Name = CTemp.get(i).getName();
			String url = "https://finance.naver.com/item/main.nhn?code=" + Code;
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				System.out.println("asdf");
				System.out.println("restart");
				crawl(start, end);
				e.printStackTrace();
			}
			Elements element = doc.select("p.no_today");
			String LivePrice = element.select("span").get(0).text();
			LivePrice = LivePrice.replaceAll(",", "");
			doConclude(Name, LivePrice);
			com.java.DTO.OrderList.refresh();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void doConclude(String name, String livePrice) {
		int currentPrice = Integer.parseInt(livePrice);
		long money = 0;
		int remainVolume;
		int isConcluded;
		String OID;
		String UID;
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;

		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		String time = dateFormat.format(date);
		int timeToInt = Integer.parseInt(time);

		for (int i = 0; i < OTemp.size(); i++) {
			OID = OTemp.get(i).getOrderID();
			UID = OTemp.get(i).getUserID();
			isConcluded = OTemp.get(i).getIsConcluded();
			if (OTemp.get(i).getCoName().equals(name) && isConcluded == 0) {
				switch (OTemp.get(i).getOrderType()) {
				case "Buy,Distinct":
					if (180000 > timeToInt && timeToInt >= 90000) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (timeToInt > 180000) {
						com.java.DTO.OrderList.delete(OID, UID);
					}
					break;
				case "Buy,Market":
					if (OTemp.get(i).getPrice() >= currentPrice && (180000 > timeToInt && timeToInt >= 90000)) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (timeToInt > 180000) {
						com.java.DTO.OrderList.delete(OID, UID);
					}
					break;
				case "Buy,OptMarket":
					if (OTemp.get(i).getPrice() >= currentPrice && (153000 > timeToInt && timeToInt >= 90000)) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (153000 < timeToInt) {
						int optPrice = OTemp.get(i).getPrice();
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();

							pstmt = conn.prepareStatement("select CapitalCurrent from user where ID = ?");
							pstmt.setString(1, UID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								money = rs.getLong(1);
							}

							money = money + (OTemp.get(i).getRemainVolume() * (optPrice - currentPrice));

							pstmt = conn.prepareStatement("update user set CapitalCurrent = ? where ID = ?");
							pstmt.setLong(1, money);
							pstmt.setString(2, UID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					break;
				case "Sell,Distinct":
					remainVolume = 0;
					isConcluded = 1;
					try {
						if (180000 > timeToInt && timeToInt >= 90000) {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();

							pstmt = conn.prepareStatement("select CapitalCurrent from user where ID = ?");
							pstmt.setString(1, UID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								money = rs.getLong(1);
							}

							money = money + (OTemp.get(i).getRemainVolume() * currentPrice);

							pstmt = conn.prepareStatement("update user set CapitalCurrent = ? where ID = ?");
							pstmt.setLong(1, money);
							pstmt.setString(2, UID);
							pstmt.executeUpdate();
						} else if (timeToInt > 180000) {
							com.java.DTO.OrderList.delete(OID, UID);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case "Sell,Market":
					if (OTemp.get(i).getPrice() <= currentPrice && (180000 > timeToInt && timeToInt >= 90000)) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();

							pstmt = conn.prepareStatement("select CapitalCurrent from user where ID = ?");
							pstmt.setString(1, UID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								money = rs.getLong(1);
							}

							money = money + (OTemp.get(i).getRemainVolume() * currentPrice);

							pstmt = conn.prepareStatement("update user set CapitalCurrent = ? where ID = ?");
							pstmt.setLong(1, money);
							pstmt.setString(2, UID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (timeToInt > 180000) {
						com.java.DTO.OrderList.delete(OID, UID);
					}
					break;
				case "Sell,OptMarket":
					if (OTemp.get(i).getPrice() <= currentPrice && (153000 > timeToInt && timeToInt >= 90000)) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();

							pstmt = conn.prepareStatement("select CapitalCurrent from user where ID = ?");
							pstmt.setString(1, UID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								money = rs.getLong(1);
							}

							money = money + (OTemp.get(i).getRemainVolume() * currentPrice);

							pstmt = conn.prepareStatement("update user set CapitalCurrent = ? where ID = ?");
							pstmt.setLong(1, money);
							pstmt.setString(2, UID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (153000 < timeToInt) {
						remainVolume = 0;
						isConcluded = 1;
						try {
							pstmt = conn.prepareStatement(
									"update orderlist set RemainVolume = ?, IsConcluded = ? where OrderID = ?");
							pstmt.setInt(1, remainVolume);
							pstmt.setInt(2, isConcluded);
							pstmt.setString(3, OID);
							pstmt.executeUpdate();

							pstmt = conn.prepareStatement("select CapitalCurrent from user where ID = ?");
							pstmt.setString(1, UID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								money = rs.getLong(1);
							}

							money = money + (OTemp.get(i).getRemainVolume() * currentPrice);

							pstmt = conn.prepareStatement("update user set CapitalCurrent = ? where ID = ?");
							pstmt.setLong(1, money);
							pstmt.setString(2, UID);
							pstmt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					break;
				}
			}
		}
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}