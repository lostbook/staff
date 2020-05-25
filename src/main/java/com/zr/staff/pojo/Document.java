package com.zr.staff.pojo;

import java.sql.Timestamp;

public class Document {
	private int id;
	private String title;
	private String filename;
	private String remark;
	private Timestamp create_date;
	private int user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Document(int id, String title, String filename, String remark, Timestamp create_date, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.remark = remark;
		this.create_date = create_date;
		this.user_id = user_id;
	}
	
}
