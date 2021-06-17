package com.java.member;

import java.util.ArrayList;

import com.java.DTO.User;

public class FindIDPW {// 아이디 또는 비밀번호 찾기
	public String findID(String email) {// 타입은 아이디인지 비밀번호인지.
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getBirthday().equals(email)) {// 이메일로 찾기
				return "회원님의 ID는 " + lTemp.get(i).getPW() + "입니다.";
			}
		}

		return "이메일이 다릅니다.";
	}

	public String findPW(String birthDay, String email) {// pw 찾기
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for(User u : lTemp) {
			if((u.getBirthday().equals(birthDay) == true) && (u.getEmail().equals(email))) {
				return "회원님의 PW는 " + u.getPW() + "입니다.";
			}
		}

		return "생일 또는 이메일이 다릅니다.";
	}
}
