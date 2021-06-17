package com.java.DTO;

public class Bookmark {
	private String UserID;
	private String CoName;
	private String SessionID;
	
	public Bookmark(String ID, String CoName, String SessionID) {
		this.UserID = ID;
		this.CoName = CoName;
		this.SessionID = SessionID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public String getCoName() {
		return CoName;
	}
	public void setCoName(String CoName) {
		this.CoName = CoName;
	}
	public Object getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		this.SessionID = sessionID;
	}
}
