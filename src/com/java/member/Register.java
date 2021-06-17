package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.DTO.User;

public class Register {
	// DB에서 회원정보 추가하고 리스트는 리프레쉬.
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
		IsValid();//입력값 중 중복되는 것이 있는지 검사
		
		if(flag) {//중복값이 존재하지 않는다면 등록 실행. 아니라면 아무것도 하지 않음.
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
	
	public void IsValid() {// id, pw, 닉네임, 이메일이 같은지 검사
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		System.out.println(lTemp.size());
		for (int i = 0; i < lTemp.size(); i++) {
			if ((lTemp.get(i).getID().equals(ID))) {// id가 같거나
				this.flag = false;
				this.description = "동일한 id가 존재합니다.";
				return;
			} else if ((lTemp.get(i).getPW().equals(PW))) {// pw가 같거나
				this.flag = false;
				this.description = "동일한 pw가 존재합니다.";
				return;
			} else if ((lTemp.get(i).getNickName().equals(NickName))) {// 닉네임이 같거나
				this.flag = false;
				this.description = "동일한 닉네임이 존재합니다.";
				return;
			} else if ((lTemp.get(i).getEmail().equals(Email))) {// 이메일이 같으면
				this.flag = false;
				this.description = "동일한 이메일 주소가 존재합니다.";
				return;
			}
		}
		
		// 위의 값들 중 중복되는 값이 없다면
		this.flag = true;
		this.description = "중복되는 정보가 없습니다.";
	}

	public boolean getresult() {// 플래그 가져오기
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
