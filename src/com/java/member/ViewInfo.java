package com.java.member;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.User;

public class ViewInfo {
	public User getUser(HttpSession session) {
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getSessionID().equals(session.getId())) {
				return lTemp.get(i);
			}
		}
		return null;
	}
}