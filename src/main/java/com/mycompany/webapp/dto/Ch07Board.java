package com.mycompany.webapp.dto;

import java.util.Date;

public class Ch07Board {

	private int no;
	private String title;
	private String content;
	private String writer;
	private Date date;
	
	
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public Date getDate() {
		return date;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
