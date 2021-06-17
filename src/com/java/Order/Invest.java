package com.java.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.*;

public class Invest {
	private boolean flag = false;
	private String description = "�ֹ� ��Ͽ� �����߽��ϴ�.";
	private User user = null;
	private Company comp = null;
	// ���� �ֽ� ��, ���� ����, ���� �ִ� ����, �ü�����
	private int amt = 0;
	private int val = 0;
	private int market = 0;
	private int profit = 0;

	public Invest(String coName, HttpSession session) {
		ArrayList<User> uList = com.java.DTO.UserList.getList();
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getSessionID().equals(session.getId())) {
				user = uList.get(i);
			}
		}

		ArrayList<Company> cList = com.java.DTO.CompanyList.getList();

		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getName().equals(coName)) {
				comp = cList.get(i);
			}
		}

		ArrayList<com.java.DTO.Order> oList = com.java.DTO.OrderList.getList();// ���� �ֽ� ��, ���ڰ��� ���ϱ�
		com.java.DTO.Order order = null;

		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getUserID().equals(user.getID())) {// �ش� ������ �ֹ������̰�
				order = oList.get(i);

				if (order.getCoName().equals(coName)) {// �ش� ����翡 ���� �ֹ������̸�
					String[] s = order.getOrderType().split(",");// �켱 �ֹ� Ÿ���� ','������ ������(�������� �ŵ�����, �⺻�ɼ��� ����, �߰��ɼ��� ����)
					if (s[0].equals("sell")) {// �ŵ� ����̶��,
						amt -= (order.getOrderedVolume() - order.getRemainVolume());// �ش� �ֹ����� �Ÿŵ� �ֽ� ����ŭ ���� �ֽ� ������ ����
						val -= (order.getOrderedVolume() - order.getRemainVolume()) * order.getPrice();// �ŵ� ���͸�ŭ ���ڰ��ݿ���
																										// ����
					} else if (s[0].equals("buy")) {// ���� ����̶��,
						amt += (order.getOrderedVolume() - order.getRemainVolume());// �ش� �ֹ����� ���Ե� �ֽ� ����ŭ ���� �ֽ� �� �߰�
						val += (order.getOrderedVolume() - order.getRemainVolume()) * order.getPrice();// ���� ���ݸ�ŭ ���ڰ���
																										// �߰�
					}
				}
			}
		}

		getLiveInfo live = new getLiveInfo();// ���簡��, �ü����� ���ϱ� 

		live.crawl(coName);
		DayStockInfoJSP tInfo = live.getInfo();
		String price = tInfo.getPercent().replaceAll(",", "");
		market = Integer.parseInt(price);
		profit = market * amt;

	}

	public void invest() {

	}

	public int getAmt() {
		return amt;
	}

	public int getVal() {
		return val;
	}

	public int getMarket() {
		return market;
	}

	public int getProfit() {
		return profit;
	}

	public User getUser() {
		return user;
	}

	public Company getCompany() {
		return comp;
	}
}
