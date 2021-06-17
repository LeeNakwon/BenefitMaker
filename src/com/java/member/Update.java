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
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// 세션 id로 해당 유저 정보 가져옴
				return lTemp.get(i);
			}
		}
		return null;//없으면 null 반환
	}
	
	public void update(String check, String id, String pw, String nickName, String birth, String email) {
		if(!isValid(check)) {//비밀번호 확인 실패 시 중단
			flag = false;
			return;
		}
		else if(!isValid(id, pw, nickName, email)) {//정보 중복 시 중단
			flag = false;
			return;
		}
		
		System.out.println("DB 업데이트 중.");
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
		
		com.java.DTO.UserList.refresh();//회원정보 수정 후 리프레쉬 직접 호출
		flag = true;
	}
	
	public boolean isValid(String pw) {//비밀번호 확인
		
		User user = findUser(session);
		
		if(user.getPW().equals(pw)) {
			return true;
		}
		
		description = "PW가 다릅니다. 다시 입력해 주세요.";
		return false;
	}
	
	public boolean isValid(String id, String pw, String nickName, String email) {//정보 중복 확인
		
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		
		for (int i = 0; i < lTemp.size(); i++) {
			if (!lTemp.get(i).getSessionID().equals(session.getId())) {//세션 ID가 같지 않은 회원 정보일 때(현재 회원 정보가 아닌 것에 대해)
				if(lTemp.get(i).getID().equals(id)) {//동일 ID가 존재한다면
					description = "동일한 ID가 존재합니다. 다시 입력해 주세요.";
					return false;
				}
				else if(lTemp.get(i).getPW().equals(pw)) {//동일 PW가 존재한다면
					description = "동일한 PW가 존재합니다. 다시 입력해 주세요.";
					return false;
				}
				else if(lTemp.get(i).getNickName().equals(nickName)) {//동일 닉네임이 존재한다면
					description = "동일한 닉네임이 존재합니다. 다시 입력해 주세요.";
					return false;
				}
				else if(lTemp.get(i).getEmail().equals(email)) {//동일 이메일이 존재한다면
					description = "동일한 이메일이 존재합니다. 다시 입력해 주세요.";
					return false;
				}
			}
		}
		return true;//중복되는 정보가 없다면 true
	}
	
	public boolean getresult() {// 플래그 가져오기
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
