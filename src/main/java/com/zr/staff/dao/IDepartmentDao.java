package com.zr.staff.dao;

import java.util.List;

import com.zr.staff.pojo.Department;

public interface IDepartmentDao {

	List<Department> findAll();

	void addDepartment(String name, String remark);

	void updateDepartment(int id, String name, String remark);

	List<Department> searchDepartment(String name);

	void deleteDepartment(String id);

}
