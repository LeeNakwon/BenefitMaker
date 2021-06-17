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
				System.out.println("���ͱ�, ���ͷ� ���� ��� ����.");
				calculateRank();
				System.out.println("���ͱ�, ���ͷ� ���� ��� �Ϸ�.");
			}
			
			long day = 86400000;
			
			try {
				Thread.sleep(day);//�Ϸ� ��.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void calculateRank() {
		calculatePerAmt();//ȸ������ ���ͱ�, ���ͷ� ���.
		
		User user1st;
		
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//��ȿ�� ȸ�������� ���ؼ��� ����Ѵ�.
				user1st = list.get(i);
				//�ϴ� ȸ�� �ϳ��� 1������ �д�.
				user1st.setRankPer(1);
				user1st.setRankAmt(1);
				
				for(int j = 0;j<list.size();j++) {
					if(list.get(j).getIsActive() == 1) {//��ȿ�� ȸ�������� ���ؼ��� ����Ѵ�.
						if(user1st.getPer() < list.get(j).getPer()) {//���� �ְ� ������ ������ ȸ������ ���ͷ��� ������
							user1st.setRankPer(user1st.getRankPer()+1);//�ش� ȸ���� ���ͷ� ������ ����.
						}
						if(user1st.getAmt() < list.get(j).getAmt()) {//���� �ְ� ������ ������ ȸ������ ���ͱ��� ������
							user1st.setRankAmt(user1st.getRankAmt()+1);//�ش� ȸ���� ���ͱ� ������ ����.
						}
					}
				}
			}
		}
		
		updateRank();//��� ����� ������ DB���� ȸ������ ����(������Ʈ) ����.
	}
	
	public void calculatePerAmt() {//ȸ������ ���ͱ�, ���ͷ��� ����Ѵ�.
		long cap1st;
		long capCur;
		double per;
		long amt;
		
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//��ȿ�� ȸ�������� ���ؼ��� ����Ѵ�.
				cap1st = list.get(i).getCapital1st();
				capCur = list.get(i).getCapitalCurrent();
				
				per = (double)capCur/(double)cap1st -1;
				amt = capCur - cap1st;
				
				list.get(i).setPer(per);
				list.get(i).setAmt(amt);
				list.get(i).setCapital1st(capCur);//����� �Ϸ�Ǹ� �ʱ� �ں��ݵ� ���� �ں������� �д�.
			}
		}
	}
	
	public void updateRank() {
		System.out.println("DB ������Ʈ ��.");
		com.java.DAO.DBConnecter driver = new com.java.DAO.DBConnecter();
		Connection conn = driver.getConnection();
		String sql = "update User set Capital1st=?,RankPer=?,RankAmt=?,Per=?,Amt=? where ID=?";
		PreparedStatement pstmt;
		User user;
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getIsActive() == 1) {//��ȿ�� ȸ�������� ���ؼ�
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
		
		System.out.println("DB ������Ʈ �Ϸ�.");
	}
}
