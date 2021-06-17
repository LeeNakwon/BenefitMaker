package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import com.java.DTO.User;

public class Login {
	private String ID, PW;
	private HttpSession session;
	private boolean flag;
	private int toLogin;
	private User tUser;
	private String description;

	public Login(String ID, String PW, HttpSession session) {
		this.ID = ID;
		this.PW = PW;
		this.session = session;
	}

	public void IsValid() {// id, pw, 세션 id 가 같은지 검사
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		System.out.println(lTemp.size());
		for (int i = 0; i < lTemp.size(); i++) {
			if ((lTemp.get(i).getID().equals(ID)) && (lTemp.get(i).getPW().equals(PW))) {// id,pw가 같고
				// 세션id 가 ""이면 수락
				if (lTemp.get(i).getSessionID().equals("")) {
					toLogin = i;
					tUser = lTemp.get(i);
					this.flag = true;
					return;
				} else {// 세션 id가 다르면 로그인 거부
					this.flag = false;
					this.description = "다른 곳에서 로그인 되어 있습니다.";
					return;
				}
			}
		}
		this.flag = false;
		this.description = "아이디 또는 비밀번호가 다릅니다.";// id, pw가 달라도 로그인 거부
	}

	public void login() {// 세션 아이디 세팅
		if (flag == true) {//해당 유저가 존재하며, 다른 곳에서 로그인 하지 않았을 경우(세션 ID가 없을 경우)

			com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
			Connection conn = driver.getConnection();
			String sql = "update User set SessionId=? where Id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, session.getId());
				pstmt.setString(2, ID);

				pstmt.executeUpdate();
				System.out.println("DB 업데이트 완료.");
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

			com.java.DTO.UserList.refresh();//로그인 후 리프레쉬 직접 호출
		}
	}
	
	public boolean getresult() {// 플래그 가져오기
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
