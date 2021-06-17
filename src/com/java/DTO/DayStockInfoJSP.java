package com.java.DTO;

public class DayStockInfoJSP {
	private String Name, Code, UnD, percent, LivePrice, DiffAmount, Yesterday, high, highLimit, volume, Start,
			Low, LowLimit, TradeAmt;
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getUnD() {
		return UnD;
	}

	public void setUnD(String unD) {
		UnD = unD;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getLivePrice() {
		return LivePrice;
	}

	public void setLivePrice(String livePrice) {
		LivePrice = livePrice;
	}

	public String getDiffAmount() {
		return DiffAmount;
	}

	public void setDiffAmount(String diffAmount) {
		DiffAmount = diffAmount;
	}

	public String getYesterday() {
		return Yesterday;
	}

	public void setYesterday(String yesterday) {
		Yesterday = yesterday;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getStart() {
		return Start;
	}

	public void setStart(String start) {
		Start = start;
	}

	public String getLow() {
		return Low;
	}

	public void setLow(String low) {
		Low = low;
	}

	public String getLowLimit() {
		return LowLimit;
	}

	public void setLowLimit(String lowLimit) {
		LowLimit = lowLimit;
	}

	public String getTradeAmt() {
		return TradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		TradeAmt = tradeAmt;
	}

	public DayStockInfoJSP(String name, String code, String unD, String percent, String livePrice,
			String diffAmount, String yesterday, String high, String highLimit, String volume, String start, String low,
			String lowLimit, String tradeAmt) {
		Name = name;
		Code = code;
		UnD = unD;
		this.percent = percent;
		LivePrice = livePrice;
		DiffAmount = diffAmount;
		Yesterday = yesterday;
		this.high = high;
		this.highLimit = highLimit;
		this.volume = volume;
		Start = start;
		Low = low;
		LowLimit = lowLimit;
		TradeAmt = tradeAmt;
	}


}
