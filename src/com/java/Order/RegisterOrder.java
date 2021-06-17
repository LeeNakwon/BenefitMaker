package com.java.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.java.DTO.*;
import com.java.member.*;

public class RegisterOrder {
	private String description = "";
	private boolean flag = false;
	
	private HttpSession session = null;
	private String userID = "";
	private String type = "";
	private String orderType = "";
	private String option = "";
	private int orderedVolume = 0;
	private int remainVolume = 0;
	private String dateTime = "";
	private String coName = "";
	private int isConcluded = 0;
	private int price = 0;

	
	public RegisterOrder(HttpSession session, String userID, String type, int orderedVolume, String coName, int price) {
		this.session = session;
		this.type = type; String[] s=type.split(","); this.userID = userID; this.orderType = s[0]; this.option = s[1]; 
		this.orderedVolume = orderedVolume; this.remainVolume = orderedVolume; this.coName = coName; this.price = price;
	}
	
	public void register() {
		if(!isValid()) {
			flag = false;
			return;
		}
		
		conclude();//ü��ó��
		updateUserInfo();//������ �� ���� �����ݾ� �̸� ����, �ŵ���� �Ϻ� ü��� ��ŭ �����ݾ� �߰�
		
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		String sql = "insert into OrderList(OrderID, UserID, OrderType, OrderedVolume, RemainVolume, DateTime, CoName, IsConcluded, Price) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			
			ArrayList<Order> list = com.java.DTO.OrderList.getList();
			String orderID = Integer.toString(list.size()+1);
			
			SimpleDateFormat form = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			String dateTime = form.format(time);
			
			pstmt.setString(1, orderID);
			pstmt.setString(2, userID);
			pstmt.setString(3, type);
			pstmt.setInt(4, orderedVolume);
			pstmt.setInt(5, remainVolume);
			pstmt.setString(6, dateTime);
			pstmt.setString(7, coName);
			pstmt.setInt(8, isConcluded);
			pstmt.setInt(9, price);
			
			pstmt.executeUpdate();
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		com.java.DTO.OrderList.refresh();

		flag = true;
	}
	
	public void conclude() {
		if(option.equals("Market")) {//���尡 �ֹ��� ���
			double rand = Math.random();
			
			remainVolume = (int)Math.floor((rand * remainVolume));
			if(remainVolume == 0) {//���� ������ ü��ƴٸ� ü��ó��.
				isConcluded = 1;
			}
		}
	}
	
	public boolean isValid() {
		if(orderType.equals("Sell") || orderType.equals("Buy")) {//����Ÿ���� ���� �Ǵ� �ŵ��� �ƴ� ���	
		}
		else {
			description = "�߸��� �ֹ� Ÿ���Դϴ�.";
			return false;
		}
		
		ArrayList<Company> list = com.java.DTO.CompanyList.getList();
		
		for(int i = 0;i<list.size();i++) {
			if (list.get(i).getName().equals(coName)) {//�ش� ����簡 �����ϸ�
				return true;
			}
		}
		
		description = "�������� �ʴ� ����� �̸��Դϴ�.";
		return false;//�ش� ����簡 �������� ����
	}
	
	public void updateUserInfo() {
		Update update = new Update(session);
		User user = update.findUser(session);
		
		if(orderType.equals("Buy")) {//���� �ں��� �̸� ���
			user.setCapitalCurrent(user.getCapitalCurrent()-(long)(price*orderedVolume));
		}
		else if(orderType.equals("Sell") || option.equals("Market")){//�̸� ü��� ��ŭ ���� �ں��� �����ֱ�
			user.setCapitalCurrent(user.getCapitalCurrent()+(long)(price*(orderedVolume-remainVolume)));
		}
		else {
			return;
		}
		System.out.println("DB ������Ʈ ��.");
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		String sql = "update User set CapitalCurrent=? where SessionId=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, user.getCapitalCurrent());
			pstmt.setString(2, session.getId());
			
			pstmt.executeUpdate();
			System.out.println("DB ������Ʈ �Ϸ�.");
			if (pstmt != null && !pstmt.isClosed()) {
				try {
					pstmt.close();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.java.DTO.UserList.refresh();//ȸ������ ���� �� �������� ���� ȣ��
	}
	
	public boolean getFlag() {return flag;}
	
	public String getDescription() {return description;}
}
