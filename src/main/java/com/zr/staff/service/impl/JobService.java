package com.zr.staff.service.impl;

import java.util.List;

import com.zr.staff.dao.IJobDao;
import com.zr.staff.dao.impl.JobDao;
import com.zr.staff.pojo.Job;
import com.zr.staff.service.IJobService;

public class JobService implements IJobService{

	IJobDao jobDao=new JobDao();
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobDao.findAll();
	}
	public void addJob(String name, String remark) {
		// TODO Auto-generated method stub
		jobDao.addJob(name,remark);
	}
	public void updateJob(String id, String name, String remark) {
		// TODO Auto-generated method stub
		jobDao.updateJob(id,name,remark);
	}
	public void deleteJob(String id) {
		// TODO Auto-generated method stub
		jobDao.deleteJob(id);
	}
	public List<Job> searchJob(String name) {
		// TODO Auto-generated method stub
		return jobDao.searchJob(name);
	}

}
