package com.zr.staff.dao;

import java.sql.Date;
import java.util.List;

import com.zr.staff.pojo.Employee;

public interface IEmployeeDao {

	List<Employee> findAll();

	void addEmployee(String name, String card_id, int sex, int job_id, String education, String email, String tel,
			String phone, String party, String qq_num, String address, String post_code, Date birthday, String race,
			String speciality, String hobby, String remark, int dept_id);

	void deleteEmployee(int id);

	List<Employee> searchEmployee(int job_id, String name, String card_id, int sex, String phone, int dept_id);

	void updateEmployee(int id, String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id);

}
