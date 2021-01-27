package com.mycompany.webapp.dto;

public class Ch14OrderItem {

	private int ono;
	private String pid;
	private int amount;
	
	
	public int getOno() {
		return ono;
	}
	public String getPid() {
		return pid;
	}
	public int getAmount() {
		return amount;
	}
	public void setOno(int ono) {
		this.ono = ono;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
