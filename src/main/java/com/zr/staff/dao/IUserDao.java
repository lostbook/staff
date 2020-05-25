package com.zr.staff.dao;

import java.util.List;

import com.zr.staff.pojo.User;

public interface IUserDao {
	User findUserByLoginnameAndPassword(String loginname,String password);

	List<User> findAll();

	void deleteById(int id);

	List<User> searchUser(int status, String username);

	void editUser(String username, String loginname, int status,int id);

	void addUser(String username, String loginname, String password, int status);

	String updatePasword(String loginname, String newPassword, String oldPassword);

}
