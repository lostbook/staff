package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.JDBC4PreparedStatement;
import com.zr.staff.dao.IEmployeeDao;
import com.zr.staff.pojo.Employee;
import com.zr.staff.utils.DBUtils;

public class EmployeeDao implements IEmployeeDao{

	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> employeeList=new ArrayList<Employee>();
		String sql="select id,dept_id,job_id,name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,race,education,"
				+ "speciality,hobby,remark,create_date from employee_inf";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);		
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				int dept_id=resultSet.getInt("dept_id");
				int job_id=resultSet.getInt("job_id");
				String name=resultSet.getString("name");
				String card_id=resultSet.getString("card_id");
				String address=resultSet.getString("address");
				String post_code=resultSet.getString("post_code");
				String tel=resultSet.getString("tel");
				String phone=resultSet.getString("phone");
				String qq_num=resultSet.getString("qq_num");
				String email=resultSet.getString("email");
				int sex=resultSet.getInt("sex");
				String party=resultSet.getString("party");
				Date birthday=resultSet.getDate("birthday");
				String race=resultSet.getString("race");
				String education=resultSet.getString("education");
				String speciality=resultSet.getString("speciality");
				String hobby=resultSet.getString("hobby");
				String remark=resultSet.getString("remark");
				Timestamp create_date=resultSet.getTimestamp("create_date");
				Employee employee=new Employee(id,dept_id,job_id,name,card_id,address,post_code,tel,phone,qq_num,email,
						sex,party,birthday,race,education,speciality,hobby,remark,create_date);
				employeeList.add(employee);
			}
			return employeeList;
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

	public void addEmployee(String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code,
			Date birthday, String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		String sql="insert employee_inf (name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2,card_id);
			preparedStatement.setInt(3,sex);
			preparedStatement.setInt(4,job_id);
			preparedStatement.setString(5,education);
			preparedStatement.setString(6,email);
			preparedStatement.setString(7,tel);
			preparedStatement.setString(8,phone);
			preparedStatement.setString(9,party);
			preparedStatement.setString(10,qq_num);
			preparedStatement.setString(11,address);
			preparedStatement.setString(12,post_code);
			preparedStatement.setDate(13, birthday);
			preparedStatement.setString(14,race);
			preparedStatement.setString(15,speciality);
			preparedStatement.setString(16,hobby);
			preparedStatement.setString(17,remark);
			preparedStatement.setInt(18,dept_id);
			String rsq = ((JDBC4PreparedStatement)preparedStatement).asSql();
			System.out.println(rsq);
			preparedStatement.executeUpdate();
			System.out.println(birthday);
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

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		String sql="delete from employee_inf where id="+id+"";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
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

	public List<Employee> searchEmployee(int job_id, String name, String card_id2, int sex, String phone, int dept_id) {
		// TODO Auto-generated method stub
		List<Employee> employeeList=new ArrayList<Employee>();
		String sql="";
		String card_id="";
		if(card_id2==null) {
			card_id="";
		}
		else {
			card_id=card_id2;
		}
		if(job_id!=0&&sex!=0&&dept_id!=0) {
			sql="select * from employee_inf where cast(job_id as char) like '%"+job_id+"%' "
					+ "and cast(sex as char) like '%"+sex+"%' "
							+ "and cast(dept_id as char) like '%"+dept_id+"%'";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id==0&&sex!=0&&dept_id!=0) {
			sql="select * from employee_inf where "
					+ "cast(sex as char) like '%"+sex+"%' "
							+ "and cast(dept_id as char) like '%"+dept_id+"%'";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id!=0&&sex==0&&dept_id!=0) {
			sql="select * from employee_inf where cast(job_id as char) like '%"+job_id+"%' "
							+ "and cast(dept_id as char) like '%"+dept_id+"%'";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id!=0&&sex!=0&&dept_id==0) {
			sql="select * from employee_inf where cast(job_id as char) like '%"+job_id+"%' "
					+ "and cast(sex as char) like '%"+sex+"%' ";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id==0&&sex==0&&dept_id!=0) {
			sql="select * from employee_inf where "
							+ "cast(dept_id as char) like '%"+dept_id+"%'";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id==0&&sex!=0&&dept_id==0) {
			sql="select * from employee_inf where "
					+ "cast(sex as char) like '%"+sex+"%' ";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id!=0&&sex==0&&dept_id==0) {
			sql="select * from employee_inf where cast(job_id as char) like '%"+job_id+"%' ";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else if(job_id==0&&sex==0&&dept_id==0){
			sql="select * from employee_inf where 1=1 ";
			if(name!="") {
				sql+="and name like '%"+name+"%'";
			}
			if(card_id!="") {
				sql+="and card_id like '%"+card_id+"%'";
			}
			if(phone!="") {
				sql+="and phone like '%"+phone+"%'";
			}
		}
		else {
			sql="select * from employee_inf";
		}
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			String rsq = ((JDBC4PreparedStatement)preparedStatement).asSql();
			System.out.println(rsq);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id1=resultSet.getInt("id");
				int dept_id1=resultSet.getInt("dept_id");
				int job_id1=resultSet.getInt("job_id");
				String name1=resultSet.getString("name");
				String card_id1=resultSet.getString("card_id");
				String address1=resultSet.getString("address");
				String post_code1=resultSet.getString("post_code");
				String tel1=resultSet.getString("tel");
				String phone1=resultSet.getString("phone");
				String qq_num1=resultSet.getString("qq_num");
				String email1=resultSet.getString("email");
				int sex1=resultSet.getInt("sex");
				String party1=resultSet.getString("party");
				Date birthday1=resultSet.getDate("birthday");
				String race1=resultSet.getString("race");
				String education1=resultSet.getString("education");
				String speciality1=resultSet.getString("speciality");
				String hobby1=resultSet.getString("hobby");
				String remark1=resultSet.getString("remark");
				Timestamp create_date1=resultSet.getTimestamp("create_date");
				Employee employee=new Employee(id1,dept_id1,job_id1,name1,card_id1,address1,post_code1,tel1,phone1,qq_num1,email1,
						sex1,party1,birthday1,race1,education1,speciality1,hobby1,remark1,create_date1);
				employeeList.add(employee);
			}
			return employeeList;
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

	public void updateEmployee(int id, String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		String sql="update employee_inf set name=?,card_id=?,sex=?,job_id=?,education=?,email=?,"
				+ "tel=?,phone=?,party=?,qq_num=?,address=?,post_code=?,birthday=?,race=?,"
				+ "speciality=?,hobby=?,remark=?,dept_id=? where id=?";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2,card_id);
			preparedStatement.setInt(3,sex);
			preparedStatement.setInt(4,job_id);
			preparedStatement.setString(5,education);
			preparedStatement.setString(6,email);
			preparedStatement.setString(7,tel);
			preparedStatement.setString(8,phone);
			preparedStatement.setString(9,party);
			preparedStatement.setString(10,qq_num);
			preparedStatement.setString(11,address);
			preparedStatement.setString(12,post_code);
			preparedStatement.setDate(13, birthday);
			preparedStatement.setString(14,race);
			preparedStatement.setString(15,speciality);
			preparedStatement.setString(16,hobby);
			preparedStatement.setString(17,remark);
			preparedStatement.setInt(18,dept_id);
			preparedStatement.setInt(19,id);
			String rsq = ((JDBC4PreparedStatement)preparedStatement).asSql();
			System.out.println(rsq);
			preparedStatement.executeUpdate();
			System.out.println(birthday);
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
}
