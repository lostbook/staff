package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zr.staff.dao.IUserDao;
import com.zr.staff.pojo.User;
import com.zr.staff.utils.DBUtils;

public class UserDao implements IUserDao{
	public User findUserByLoginnameAndPassword(String loginname,String password) {
		String sql="select * from user_inf where loginname=? and password=?";
		// 1, 获取到链接数据库的对象
		Connection connection=DBUtils.getConnection();
		try {

			// 2, 获取到执行sql语句的预编译对象
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// 3, 给sql中的占位符[?] 赋值
			prepareStatement.setString(1, loginname);
			prepareStatement.setString(2, password);

			// 4, 执行sql语句, 得到结果集
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, 如果有值, 则封装, 然后, 返回
			while (resultSet.next()) {
				// 获取数据库中对应的数据值
				String loginname1 = resultSet.getString("loginname");
				String password1=resultSet.getString("password");

				// 把数据更新到对象中
				User user=new User(loginname1,password1);

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 断开链接, 释放资源
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<User> findAll() {

		String sql = "select * from user_inf";

		List<User> list = new ArrayList<User>();

		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String loginname=resultSet.getString("loginname");
				String password=resultSet.getString("password");
				int status=resultSet.getInt("status");
				Timestamp createdate=resultSet.getTimestamp("createdate");
				String username=resultSet.getString("username");
				String faceurl=resultSet.getString("faceurl");
				String facepath=resultSet.getString("facepath");

				User user = new User(id, loginname, password, status, createdate, username, faceurl, facepath);

				list.add(user);
			}

			return list;

		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	
	public void deleteById(int id) {
		String sql = "delete from user_inf where id=?";

		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<User> searchUser(int status1,String username1) {
		// TODO Auto-generated method stub
		String sql;
		if(username1=="") {
			if(status1==1||status1==2)
			    sql="select * from user_inf where status=?";
			else
				sql="select * from user_inf";
		}
		else if(status1==1||status1==2) {
			sql="select * from user_inf where status=? and username=?";
		}
		//status1值为3;
		else {
			sql="select * from user_inf where username=?";
		}
		List<User> listUser = new ArrayList<User>();
		// 1, 获取到链接数据库的对象
		Connection connection=DBUtils.getConnection();
		try {

			// 2, 获取到执行sql语句的预编译对象
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// 3, 给sql中的占位符[?] 赋值
			if(username1=="") {
				if(status1==1||status1==2) {
					prepareStatement.setInt(1,status1);
				}
			}
			else if(status1==1||status1==2) {
				prepareStatement.setInt(1, status1);
				prepareStatement.setString(2, username1);
			}
			else {
				prepareStatement.setString(1, username1);
			}

			// 4, 执行sql语句, 得到结果集
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, 如果有值, 则封装, 然后, 返回
			while (resultSet.next()) {
				// 获取数据库中对应的数据值
				
				int id = resultSet.getInt("id");
				String loginname=resultSet.getString("loginname");
				String password=resultSet.getString("password");
				int status=resultSet.getInt("status");
				Timestamp createdate=resultSet.getTimestamp("createdate");
				String username=resultSet.getString("username");
				String faceurl=resultSet.getString("faceurl");
				String facepath=resultSet.getString("facepath");

				// 把数据更新到对象中
				User user1 = new User(id, loginname, password, status, createdate, username, faceurl, facepath);

				listUser.add(user1);
			}
			
			return listUser;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 断开链接, 释放资源
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void editUser(String username, String loginname, int status,int id) {
		// TODO Auto-generated method stub
		String sql="update user_inf set username=?,loginname=?,status=? where id=?";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, loginname);
			preparedStatement.setInt(3, status);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				// 断开链接, 释放资源
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public void addUser(String username, String loginname, String password, int status) {
		// TODO Auto-generated method stub
		String sql="insert into user_inf(username,loginname,password,status) values(?,?,?,?)";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, loginname);
			preparedstatement.setString(3, password);
			preparedstatement.setInt(4, status);
			preparedstatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public String updatePasword(String loginname, String newPassword,String oldPassword) {
		// TODO Auto-generated method stub
		String flag="false";
		String sql1="select password from user_inf where loginname='"+loginname+"'";
		String sql="update user_inf set password=? where loginname=?";
		Connection connection=DBUtils.getConnection();
		
		String oldPassword1="";
		try {
			PreparedStatement preparedstatement1 = connection.prepareStatement(sql1);
			ResultSet resultSet1 = preparedstatement1.executeQuery();
			while (resultSet1.next()) {
				oldPassword1 = resultSet1.getString("password");
			} 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(oldPassword.equals(oldPassword1)) {
			try {
				PreparedStatement preparedstatement=connection.prepareStatement(sql);
				preparedstatement.setString(1, newPassword);
				preparedstatement.setString(2,loginname);
				preparedstatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			flag="true";

		}

		
return flag;
	}

}
