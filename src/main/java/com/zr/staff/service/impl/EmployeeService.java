package com.zr.staff.service.impl;

import java.sql.Date;
import java.util.List;

import com.zr.staff.dao.IDepartmentDao;
import com.zr.staff.dao.IEmployeeDao;
import com.zr.staff.dao.IJobDao;
import com.zr.staff.dao.impl.DepartmentDao;
import com.zr.staff.dao.impl.EmployeeDao;
import com.zr.staff.dao.impl.JobDao;
import com.zr.staff.pojo.Department;
import com.zr.staff.pojo.Employee;
import com.zr.staff.pojo.Job;
import com.zr.staff.service.IEmployeeService;

public class EmployeeService implements IEmployeeService{

	IEmployeeDao employeeDao=new EmployeeDao();
	IJobDao jobDao=new JobDao();
	IDepartmentDao departmentDao=new DepartmentDao();
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}
	public List<Job> findJob() {
		// TODO Auto-generated method stub
		return jobDao.findAll();
	}
	public List<Department> findDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}
	public void addEmployee(String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		employeeDao.addEmployee(name, card_id, sex, job_id, education, email, tel, phone, party, qq_num, address, post_code, birthday, race, speciality, hobby, remark, dept_id);
	}
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeDao.deleteEmployee(id);
	}
	public List<Employee> searchEmployee(int job_id, String name, String card_id, int sex, String phone, int dept_id) {
		// TODO Auto-generated method stub
		return employeeDao.searchEmployee(job_id,name,card_id,sex,phone,dept_id);
	}
	public void updateEmployee(int id, String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		employeeDao.updateEmployee(id,name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id);
	}

}
