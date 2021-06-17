package com.java.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.java.DTO.*;

public class Invest {
	private boolean flag = false;
	private String description = "주문 등록에 실패했습니다.";
	private User user = null;
	private Company comp = null;
	// 보유 주식 수, 투자 가격, 현재 주당 가격, 시세차익
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

		ArrayList<com.java.DTO.Order> oList = com.java.DTO.OrderList.getList();// 보유 주식 수, 투자가격 구하기
		com.java.DTO.Order order = null;

		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getUserID().equals(user.getID())) {// 해당 유저의 주문내역이고
				order = oList.get(i);

				if (order.getCoName().equals(coName)) {// 해당 상장사에 대한 주문내역이면
					String[] s = order.getOrderType().split(",");// 우선 주문 타입을 ','단위로 나눈다(매입인지 매도인지, 기본옵션이 뭔지, 추가옵션이 뭔지)
					if (s[0].equals("sell")) {// 매도 기록이라면,
						amt -= (order.getOrderedVolume() - order.getRemainVolume());// 해당 주문에서 매매된 주식 수만큼 보유 주식 수에서 차감
						val -= (order.getOrderedVolume() - order.getRemainVolume()) * order.getPrice();// 매도 수익만큼 투자가격에서
																										// 차감
					} else if (s[0].equals("buy")) {// 매입 기록이라면,
						amt += (order.getOrderedVolume() - order.getRemainVolume());// 해당 주문에서 매입된 주식 수만큼 보유 주식 수 추가
						val += (order.getOrderedVolume() - order.getRemainVolume()) * order.getPrice();// 매입 가격만큼 투자가격
																										// 추가
					}
				}
			}
		}

		getLiveInfo live = new getLiveInfo();// 현재가격, 시세차익 구하기 

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
