package com.java.DTO;


public class User {
	private String ID;
	private String PW;
	private long Capital1st;
	private long CapitalCurrent;
	private String NickName;
	private int RankPer;
	private int RankAmt;
	private String Birthday;
	private String Email;
	private String SessionID;
	private int IsActive;
	private double Per;//지난달 수익률
	private long Amt;//지난달 수익금

	public int getIsActive() {
		return IsActive;
	}

	public void setIsActive(int isActive) {
		IsActive = isActive;
	}

	public double getPer() {
		return Per;
	}
	public void setPer(double p) {
		Per = p;
	}
	
	public long getAmt() {
		return Amt;
	}
	public void setAmt(long a) {
		Amt = a;
	}

	public User(String iD, String pW, long capital1st, long capitalCurrent, String nickName, int rankPer, int rankAmt,
			String birthday, String email, String sessionID, int isActive, double per, long amt) {
		super();
		ID = iD;
		PW = pW;
		Capital1st = capital1st;
		CapitalCurrent = capitalCurrent;
		NickName = nickName;
		RankPer = rankPer;
		RankAmt = rankAmt;
		Birthday = birthday;
		Email = email;
		SessionID = sessionID;
		IsActive = isActive;
		Per = per;
		Amt = amt;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public long getCapital1st() {
		return Capital1st;
	}

	public void setCapital1st(long capital1st) {
		Capital1st = capital1st;
	}

	public long getCapitalCurrent() {
		return CapitalCurrent;
	}

	public void setCapitalCurrent(long capitalCurrent) {
		CapitalCurrent = capitalCurrent;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public int getRankPer() {
		return RankPer;
	}

	public void setRankPer(int rankPer) {
		RankPer = rankPer;
	}

	public int getRankAmt() {
		return RankAmt;
	}

	public void setRankAmt(int rankAmt) {
		RankAmt = rankAmt;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String SessionID) {
		this.SessionID = SessionID;
	}
}
