package com.zr.staff.service.impl;

import java.util.List;

import com.zr.staff.dao.IUserDao;
import com.zr.staff.dao.impl.UserDao;
import com.zr.staff.pojo.User;
import com.zr.staff.service.IUserService;

public class UserService implements IUserService{
	
	private IUserDao userDao=new UserDao();
	
	public User findUserByLoginnameAndPassword(String loginname,String password) {
		return userDao.findUserByLoginnameAndPassword(loginname, password);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	
	public void deleteById(int id) {
		userDao.deleteById(id);
	}

	public List<User> searchUser(int status,String username) {
		// TODO Auto-generated method stub
		return userDao.searchUser(status,username);
	}

	public void editUser(String username, String loginname, int status,int id) {
		// TODO Auto-generated method stub
		userDao.editUser(username,loginname,status,id);
	}

	public void addUser(String username, String loginname, String password, int status) {
		// TODO Auto-generated method stub
		userDao.addUser(username,loginname,password,status);
	}

	public String updatePassword(String loginname, String newPassword,String oldPassword) {
		return userDao.updatePasword(loginname,newPassword,oldPassword);
	}
}

