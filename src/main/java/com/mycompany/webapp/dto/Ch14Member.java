package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Ch14Member {

	private String mid;
	private String mname;
	private String mpassword;
	private MultipartFile mphoto;
	private String mphotosname;
	private String mphotooname;
	private String mphototype;
	
	public String getMid() {
		return mid;
	}
	public String getMname() {
		return mname;
	}
	public String getMpassword() {
		return mpassword;
	}
	public MultipartFile getMphoto() {
		return mphoto;
	}
	public String getMphotosname() {
		return mphotosname;
	}
	public String getMphotooname() {
		return mphotooname;
	}
	public String getMphototype() {
		return mphototype;
	}
	public void setMphoto(MultipartFile mphoto) {
		this.mphoto = mphoto;
	}
	public void setMphotosname(String mphotosname) {
		this.mphotosname = mphotosname;
	}
	public void setMphotooname(String mphotooname) {
		this.mphotooname = mphotooname;
	}
	public void setMphototype(String mphototype) {
		this.mphototype = mphototype;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	
}
