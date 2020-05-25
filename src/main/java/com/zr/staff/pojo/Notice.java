package com.zr.staff.pojo;

import java.sql.Timestamp;

public class Notice {
	private int id;
	private String title;
	private String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Notice(int id, String title, String content, Timestamp create_date, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.create_date = create_date;
		this.user_id = user_id;
	}
}
