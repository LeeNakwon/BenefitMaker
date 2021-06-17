package com.java.DTO;

public class Company {
	private String Code,Name;
	
	public Company(String Code, String Name) {
		this.Code = Code;
		this.Name = Name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
