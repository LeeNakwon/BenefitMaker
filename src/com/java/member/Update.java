package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.User;

public class Update {
	private boolean flag = false;
	private String description = "";
	private HttpSession session = null;
	
	public Update(HttpSession session) {
		this.session = session;
	}
	
	public User findUser(HttpSession session) {
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// ���� id�� �ش� ���� ���� ������
				return lTemp.get(i);
			}
		}
		return null;//������ null ��ȯ
	}
	
	public void update(String check, String id, String pw, String nickName, String birth, String email) {
		if(!isValid(check)) {//��й�ȣ Ȯ�� ���� �� �ߴ�
			flag = false;
			return;
		}
		else if(!isValid(id, pw, nickName, email)) {//���� �ߺ� �� �ߴ�
			flag = false;
			return;
		}
		
		System.out.println("DB ������Ʈ ��.");
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		String sql = "update User set ID=?,PW=?,NickName=?,BirthDay=?,Email=? where SessionId=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, nickName);
			pstmt.setString(4, birth);
			pstmt.setString(5, email);
			pstmt.setString(6, session.getId());
			
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
		flag = true;
	}
	
	public boolean isValid(String pw) {//��й�ȣ Ȯ��
		
		User user = findUser(session);
		
		if(user.getPW().equals(pw)) {
			return true;
		}
		
		description = "PW�� �ٸ��ϴ�. �ٽ� �Է��� �ּ���.";
		return false;
	}
	
	public boolean isValid(String id, String pw, String nickName, String email) {//���� �ߺ� Ȯ��
		
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		
		for (int i = 0; i < lTemp.size(); i++) {
			if (!lTemp.get(i).getSessionID().equals(session.getId())) {//���� ID�� ���� ���� ȸ�� ������ ��(���� ȸ�� ������ �ƴ� �Ϳ� ����)
				if(lTemp.get(i).getID().equals(id)) {//���� ID�� �����Ѵٸ�
					description = "������ ID�� �����մϴ�. �ٽ� �Է��� �ּ���.";
					return false;
				}
				else if(lTemp.get(i).getPW().equals(pw)) {//���� PW�� �����Ѵٸ�
					description = "������ PW�� �����մϴ�. �ٽ� �Է��� �ּ���.";
					return false;
				}
				else if(lTemp.get(i).getNickName().equals(nickName)) {//���� �г����� �����Ѵٸ�
					description = "������ �г����� �����մϴ�. �ٽ� �Է��� �ּ���.";
					return false;
				}
				else if(lTemp.get(i).getEmail().equals(email)) {//���� �̸����� �����Ѵٸ�
					description = "������ �̸����� �����մϴ�. �ٽ� �Է��� �ּ���.";
					return false;
				}
			}
		}
		return true;//�ߺ��Ǵ� ������ ���ٸ� true
	}
	
	public boolean getresult() {// �÷��� ��������
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
