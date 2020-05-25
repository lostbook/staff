package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.staff.dao.IDepartmentDao;
import com.zr.staff.pojo.Department;
import com.zr.staff.utils.DBUtils;

public class DepartmentDao implements IDepartmentDao{

	public List<Department> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from dept_inf";
		List<Department> departmentList=new ArrayList<Department>();
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String remark=resultSet.getString("remark");
				Department department=new Department(id,name,remark);
				departmentList.add(department);
			}
			return departmentList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void addDepartment(String name, String remark) {
		// TODO Auto-generated method stub
		String sql="insert dept_inf (name,remark) values (?,?)";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, remark);
			preparedStatement.executeUpdate();
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
	}

	public void updateDepartment(int id, String name, String remark) {
		// TODO Auto-generated method stub
		String sql="update dept_inf set name=?, remark=? where id=?";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2,remark);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
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
	}

	public List<Department> searchDepartment(String name1) {
		// TODO Auto-generated method stub
		String sql="";
		if(name1=="") {
			sql="select * from dept_inf";
		}
		else {
			sql="select * from dept_inf where name='"+name1+"'";
		}
		List<Department> departmentList=new ArrayList<Department>();
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);		
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String remark=resultSet.getString("remark");
				Department department=new Department(id,name,remark);
				departmentList.add(department);
			}
			return departmentList;
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
		
		return null;
	}

	public void deleteDepartment(String id) {
		// TODO Auto-generated method stub
		String sql="delete from dept_inf where id="+id+"";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
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
		
	}

}
