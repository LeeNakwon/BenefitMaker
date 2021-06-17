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
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// ���� id�� �ش� ���� ������ �����ϴ��� Ȯ��
				System.out.println("����Ʈ���� ã�Ҵ�.");
				com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
				Connection conn = driver.getConnection();
				String sql = "update User set SessionId=? where SessionId=?";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt. setString(1, "");
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
				
				com.java.DTO.UserList.refresh();//�α׾ƿ� �� �������� ���� ȣ��

				break;
			}
		}
		
		/*
		 * for (int i = 0; i < lTemp.size(); i++) { if
		 * (lTemp.get(i).getSessionID().equals(session.getId())) {// �ش� ����id�� ���� ������ ã��
		 * User tUser = lTemp.get(i); tUser.setSessionID("");// ã�Ƽ� �Ҵ�� ���� id�� ""��
		 * ����.(null�� ���� ���, ����ó���� �ȵ�) com.java.DTO.UserList.getList().set(i, tUser);//
		 * �ش� ���� ������ ��ü. } }
		 */
	}
}
