package com.zr.staff.service;

import java.sql.Date;
import java.util.List;

import com.zr.staff.pojo.Department;
import com.zr.staff.pojo.Employee;
import com.zr.staff.pojo.Job;

public interface IEmployeeService {

	List<Employee> findAll();

	List<Job> findJob();

	List<Department> findDepartment();

	void addEmployee(String name, String card_id, int sex, int job_id, String education, String email, String tel,
			String phone, String party, String qq_num, String address, String post_code, Date birthday, String race,
			String speciality, String hobby, String remark, int dept_id);

	void deleteEmployee(int id);

	List<Employee> searchEmployee(int job_id, String name, String card_id, int sex, String phone, int dept_id);

	void updateEmployee(int id, String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id);

}
