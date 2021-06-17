package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.User;

public class Logout {
	
	public void logout(HttpSession session) {	
		
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// 세션 id로 해당 유저 정보가 존재하는지 확인
				System.out.println("리스트에서 찾았다.");
				com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
				Connection conn = driver.getConnection();
				String sql = "update User set SessionId=? where SessionId=?";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt. setString(1, "");
					pstmt.setString(2, session.getId());
					
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
				
				com.java.DTO.UserList.refresh();//로그아웃 후 리프레쉬 직접 호출

				break;
			}
		}
		
		/*
		 * for (int i = 0; i < lTemp.size(); i++) { if
		 * (lTemp.get(i).getSessionID().equals(session.getId())) {// 해당 세션id를 가진 유저를 찾음
		 * User tUser = lTemp.get(i); tUser.setSessionID("");// 찾아서 할당된 세션 id를 ""로
		 * 만듬.(null로 만들 경우, 예외처리도 안됨) com.java.DTO.UserList.getList().set(i, tUser);//
		 * 해당 유저 정보를 대체. } }
		 */
	}
}
