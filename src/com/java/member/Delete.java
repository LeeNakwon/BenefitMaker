package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.User;

public class Delete {
	private boolean flag = false;
	private String description = "회원탈퇴에 실패했습니다.";
	
	// DB에서 회원정보 지우고 리스트는 리프레쉬.
	public void delete(HttpSession session) {		
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getSessionID().equals(session.getId())) {// 세션 id로 해당 유저 정보 가져옴
				System.out.println("찾았다.");
				String id = lTemp.get(i).getID();
				com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
				Connection conn = driver.getConnection();
				String sql = "UPDATE `benefitmaker`.`user` SET `isActive` = '0', 'SessionId' = '' WHERE (`ID` = ?);";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					
					pstmt.executeUpdate();
					System.out.println("삭제 - 업데이트 완료");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = true;
				description = "회원탈퇴에 성공했습니다.";
				
				com.java.DTO.UserList.refresh();// 회원정보 삭제 후 리프레쉬 직접 호출

				break;
			}
		}
	}
	public boolean getresult() {// 플래그 가져오기
		return flag;
	}

	public String getdescription() {
		return description;
	}
}
