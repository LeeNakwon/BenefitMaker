package com.java.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.java.DTO.User;

public class CalculateRank extends Thread{
	private ArrayList<User> list = com.java.DTO.UserList.getList();
	
	@Override
	public void run() {
		while(true) {	
			
			Date time = new Date();
			
			if(time.getDate() == 1) {
				System.out.println("수익금, 수익률 순위 계산 시작.");
				calculateRank();
				System.out.println("수익금, 수익률 순위 계산 완료.");
			}
			
			long day = 86400000;
			
			try {
				Thread.sleep(day);//하루 쉼.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void calculateRank() {
		calculatePerAmt();//회원들의 수익금, 수익률 계산.
		
		User user1st;
		
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//유효한 회원정보에 대해서만 계산한다.
				user1st = list.get(i);
				//일단 회원 하나를 1등으로 둔다.
				user1st.setRankPer(1);
				user1st.setRankAmt(1);
				
				for(int j = 0;j<list.size();j++) {
					if(list.get(j).getIsActive() == 1) {//유효한 회원정보에 대해서만 계산한다.
						if(user1st.getPer() < list.get(j).getPer()) {//현재 최고 순위로 지정된 회원보다 수익률이 높으면
							user1st.setRankPer(user1st.getRankPer()+1);//해당 회원의 수익률 순위를 내림.
						}
						if(user1st.getAmt() < list.get(j).getAmt()) {//현재 최고 순위로 지정된 회원보다 수익금이 높으면
							user1st.setRankAmt(user1st.getRankAmt()+1);//해당 회원의 수익금 순위를 내림.
						}
					}
				}
			}
		}
		
		updateRank();//모든 계산이 끝나면 DB에서 회원정보 수정(업데이트) 수행.
	}
	
	public void calculatePerAmt() {//회원들의 수익금, 수익률을 계산한다.
		long cap1st;
		long capCur;
		double per;
		long amt;
		
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//유효한 회원정보에 대해서만 계산한다.
				cap1st = list.get(i).getCapital1st();
				capCur = list.get(i).getCapitalCurrent();
				
				per = (double)capCur/(double)cap1st -1;
				amt = capCur - cap1st;
				
				list.get(i).setPer(per);
				list.get(i).setAmt(amt);
				list.get(i).setCapital1st(capCur);//계산이 완료되면 초기 자본금도 현재 자본금으로 둔다.
			}
		}
	}
	
	public void updateRank() {
		System.out.println("DB 업데이트 중.");
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		String sql = "update User set Capital1st=?,RankPer=?,RankAmt=?,Per=?,Amt=? where ID=?";
		PreparedStatement pstmt;
		User user;
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//유효한 회원정보에 대해서
				try {
					user = list.get(i);
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setLong(1, user.getCapital1st());
					pstmt.setInt(2, user.getRankPer());
					pstmt.setInt(3, user.getRankAmt());
					pstmt.setDouble(4, user.getPer());
					pstmt.setLong(5, user.getAmt());
					pstmt.setString(6, user.getID());
					
					pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		
		System.out.println("DB 업데이트 완료.");
	}
}
