package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.staff.dao.IJobDao;
import com.zr.staff.pojo.Department;
import com.zr.staff.pojo.Job;
import com.zr.staff.utils.DBUtils;

public class JobDao implements IJobDao{

	public List<Job> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from job_inf";
		List<Job> jobList=new ArrayList<Job>();
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String remark=resultSet.getString("remark");
				Job job=new Job(id,name,remark);
				jobList.add(job);
			}
			return jobList;
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

	public void addJob(String name, String remark) {
		// TODO Auto-generated method stub
		String sql="insert job_inf (name,remark) values (?,?)";
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

	public void updateJob(String id1, String name, String remark) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		String sql="update job_inf set name=?, remark=? where id=?";
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

	public void deleteJob(String id1) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		String sql="delete from job_inf where id="+id+"";
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

	public List<Job> searchJob(String name1) {
		// TODO Auto-generated method stub
		String sql="";
		if(name1=="") {
			sql="select * from job_inf";
		}
		else {
			sql="select * from job_inf where name='"+name1+"'";
		}
		List<Job> jobList=new ArrayList<Job>();
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);		
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String remark=resultSet.getString("remark");
				Job job=new Job(id,name,remark);
				jobList.add(job);
			}
			return jobList;
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

}
