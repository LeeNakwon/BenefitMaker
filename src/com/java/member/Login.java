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

	public void IsValid() {// id, pw, ���� id �� ������ �˻�
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		System.out.println(lTemp.size());
		for (int i = 0; i < lTemp.size(); i++) {
			if ((lTemp.get(i).getID().equals(ID)) && (lTemp.get(i).getPW().equals(PW))) {// id,pw�� ����
				// ����id �� ""�̸� ����
				if (lTemp.get(i).getSessionID().equals("")) {
					toLogin = i;
					tUser = lTemp.get(i);
					this.flag = true;
					return;
				} else {// ���� id�� �ٸ��� �α��� �ź�
					this.flag = false;
					this.description = "�ٸ� ������ �α��� �Ǿ� �ֽ��ϴ�.";
					return;
				}
			}
		}
		this.flag = false;
		this.description = "���̵� �Ǵ� ��й�ȣ�� �ٸ��ϴ�.";// id, pw�� �޶� �α��� �ź�
	}

	public void login() {// ���� ���̵� ����
		if (flag == true) {//�ش� ������ �����ϸ�, �ٸ� ������ �α��� ���� �ʾ��� ���(���� ID�� ���� ���)

			com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
			Connection conn = driver.getConnection();
			String sql = "update User set SessionId=? where Id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, session.getId());
				pstmt.setString(2, ID);

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

			com.java.DTO.UserList.refresh();//�α��� �� �������� ���� ȣ��
		}
	}
	
	public boolean getresult() {// �÷��� ��������
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
