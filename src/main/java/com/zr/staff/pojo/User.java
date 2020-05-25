package com.zr.staff.pojo;

import java.sql.Timestamp;

public class User {
	private int id;
	private String loginname;
	private String password;
	private int status;
	private Timestamp createdate;
	private String username;
	private String faceurl;
	private String facepath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFaceurl() {
		return faceurl;
	}
	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}
	public String getFacepath() {
		return facepath;
	}
	public void setFacepath(String facepath) {
		this.facepath = facepath;
	}
	public User(int id, String loginname, String password, int status, Timestamp createdate, String username,
			String faceurl, String facepath) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.createdate = createdate;
		this.username = username;
		this.faceurl = faceurl;
		this.facepath = facepath;
	}
	public User(int status, String username) {
		super();
		this.status = status;
		this.username = username;
	}
	public User(String loginname1, String password1) {
		// TODO Auto-generated constructor stub
		super();
		this.loginname=loginname1;
		this.password=password1;
	}

}
