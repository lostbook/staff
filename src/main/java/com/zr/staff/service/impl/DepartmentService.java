package com.zr.staff.service.impl;

import java.util.List;

import com.zr.staff.dao.IDepartmentDao;
import com.zr.staff.dao.impl.DepartmentDao;
import com.zr.staff.pojo.Department;
import com.zr.staff.service.IDepartmentService;

public class DepartmentService implements IDepartmentService {
	
	private IDepartmentDao departmentDao=new DepartmentDao();

	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}

	public void addDepartment(String name, String remark) {
		// TODO Auto-generated method stub
		departmentDao.addDepartment(name,remark);
	}

	public void updateDepartment(int id, String name, String remark) {
		// TODO Auto-generated method stub
		departmentDao.updateDepartment(id,name,remark);
	}

	public List<Department> searchDepartment(String name) {
		// TODO Auto-generated method stub
		return departmentDao.searchDepartment(name);
	}

	public void deleteDepartment(String id) {
		// TODO Auto-generated method stub
		departmentDao.deleteDepartment(id);
	}

}
