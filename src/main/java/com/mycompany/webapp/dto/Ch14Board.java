package com.mycompany.webapp.dto;

import java.util.Date;

public class Ch14Board {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private String battachsname;
	private String battachoname;
	private String battachtype;
	
	
	public int getBno() {
		return bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public int getBhitcount() {
		return bhitcount;
	}
	public String getBattachsname() {
		return battachsname;
	}
	public String getBattachoname() {
		return battachoname;
	}
	public String getBattachtype() {
		return battachtype;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public void setBhitcount(int bhitcount) {
		this.bhitcount = bhitcount;
	}
	public void setBattachsname(String battachsname) {
		this.battachsname = battachsname;
	}
	public void setBattachoname(String battachoname) {
		this.battachoname = battachoname;
	}
	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}
}
