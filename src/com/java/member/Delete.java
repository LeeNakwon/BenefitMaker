package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.User;

public class Delete {
	private boolean flag = false;
	private String description = "ȸ��Ż�� �����߽��ϴ�.";
	
	// DB���� ȸ������ ����� ����Ʈ�� ��������.
	public void delete(HttpSession session) {		
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// ���� id�� �ش� ���� ���� ������
				System.out.println("ã�Ҵ�.");
				String id = lTemp.get(i).getID();
				com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
				Connection conn = driver.getConnection();
				String sql = "UPDATE `benefitmaker`.`user` SET `isActive` = '0', 'SessionId' = '' WHERE (`ID` = ?);";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					
					pstmt.executeUpdate();
					System.out.println("���� - ������Ʈ �Ϸ�");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = true;
				description = "ȸ��Ż�� �����߽��ϴ�.";
				
				com.java.DTO.UserList.refresh();// ȸ������ ���� �� �������� ���� ȣ��

				break;
			}
		}
	}
	public boolean getresult() {// �÷��� ��������
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
