package com.java.DTO;

import java.util.Date;

public class DayStockInfo {
	private String Code;
	private Date Date;
	private int Start, Close, AdjClose, Volume, High, Low;

	public DayStockInfo(String code, java.util.Date date, int start, int close, int adjClose, int volume, int high,
			int low) {
		Code = code;
		Date = date;
		Start = start;
		Close = close;
		AdjClose = adjClose;
		Volume = volume;
		High = high;
		Low = low;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public int getStart() {
		return Start;
	}

	public void setStart(int start) {
		Start = start;
	}

	public int getClose() {
		return Close;
	}

	public void setClose(int close) {
		Close = close;
	}

	public int getAdjClose() {
		return AdjClose;
	}

	public void setAdjClose(int adjClose) {
		AdjClose = adjClose;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int volume) {
		Volume = volume;
	}

	public int getHigh() {
		return High;
	}

	public void setHigh(int high) {
		High = high;
	}

	public int getLow() {
		return Low;
	}

	public void setLow(int low) {
		Low = low;
	}
}
