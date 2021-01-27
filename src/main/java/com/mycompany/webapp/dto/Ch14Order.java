package com.mycompany.webapp.dto;

import java.util.List;

public class Ch14Order {

	private int ono;
	private String mid;
	private String address;
	
	public int getOno() {
		return ono;
	}
	public String getMid() {
		return mid;
	}
	public String getAddress() {
		return address;
	}
	public void setOno(int ono) {
		this.ono = ono;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
