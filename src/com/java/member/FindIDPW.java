package com.java.member;

import java.util.ArrayList;

import com.java.DTO.User;

public class FindIDPW {// ���̵� �Ǵ� ��й�ȣ ã��
	public String findID(String email) {// Ÿ���� ���̵����� ��й�ȣ����.
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for (int i = 0; i < lTemp.size(); i++) {
			if (lTemp.get(i).getBirthday().equals(email)) {// �̸��Ϸ� ã��
				return "ȸ������ ID�� " + lTemp.get(i).getPW() + "�Դϴ�.";
			}
		}

		return "�̸����� �ٸ��ϴ�.";
	}

	public String findPW(String birthDay, String email) {// pw ã��
		ArrayList<User> lTemp = com.java.DTO.UserList.getList();
		for(User u : lTemp) {
			if((u.getBirthday().equals(birthDay) == true) && (u.getEmail().equals(email))) {
				return "ȸ������ PW�� " + u.getPW() + "�Դϴ�.";
			}
		}

		return "���� �Ǵ� �̸����� �ٸ��ϴ�.";
	}
}
