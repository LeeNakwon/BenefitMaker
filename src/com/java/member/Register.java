package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.DTO.User;

public class Register {
	// DB���� ȸ������ �߰��ϰ� ����Ʈ�� ��������.
	private String ID = "", PW = "", NickName = "", BirthDay = "", Email = "", SessionID = "";
	private int Capital1st = 10000000, CapitalCurrent = 10000000;
	private int RankPer = 0, RankAmt = 0;
	private long Per = 0, Amt =0;
	private boolean flag = false;
	private String description;

	public Register(String id, String pw, String nick, String birth, String email) {
		this.ID = id;
		this.PW = pw;
		this.NickName = nick;
		this.BirthDay = birth;
		this.Email = email;
	}

	public void register() {
		IsValid();//�Է°� �� �ߺ��Ǵ� ���� �ִ��� �˻�
		
		if(flag) {//�ߺ����� �������� �ʴ´ٸ� ��� ����. �ƴ϶�� �ƹ��͵� ���� ����.
			com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
			Connection conn = driver.getConnection();
			String sql = "insert into User(ID, PW, Capital1st, CapitalCurrent, NickName, RankPer, RankAmt, BirthDay, Email, SessionId, isActive, Per, Amt) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, ID);
				pstmt.setString(2, PW);
				pstmt.setInt(3, Capital1st);
				pstmt.setInt(4, CapitalCurrent);
				pstmt.setString(5, NickName);
				pstmt.setInt(6, RankPer);
				pstmt.setInt(7, RankAmt);
				pstmt.setString(8, BirthDay);
				pstmt.setString(9, Email);
				pstmt.setString(10, SessionID);
				pstmt.setInt(11, 1);
				pstmt.setLong(12, Per);
				pstmt.setLong(13, Amt);
				
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
			
			com.java.DTO.UserList.refresh();
		}
	}
	
	public void IsValid() {// id, pw, �г���, �̸����� ������ �˻�
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		System.out.println(lTemp.size());
		for (int i = 0; i < lTemp.size(); i++) {
			if ((lTemp.get(i).getID().equals(ID))) {// id�� ���ų�
				this.flag = false;
				this.description = "������ id�� �����մϴ�.";
				return;
			} else if ((lTemp.get(i).getPW().equals(PW))) {// pw�� ���ų�
				this.flag = false;
				this.description = "������ pw�� �����մϴ�.";
				return;
			} else if ((lTemp.get(i).getNickName().equals(NickName))) {// �г����� ���ų�
				this.flag = false;
				this.description = "������ �г����� �����մϴ�.";
				return;
			} else if ((lTemp.get(i).getEmail().equals(Email))) {// �̸����� ������
				this.flag = false;
				this.description = "������ �̸��� �ּҰ� �����մϴ�.";
				return;
			}
		}
		
		// ���� ���� �� �ߺ��Ǵ� ���� ���ٸ�
		this.flag = true;
		this.description = "�ߺ��Ǵ� ������ �����ϴ�.";
	}

	public boolean getresult() {// �÷��� ��������
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
