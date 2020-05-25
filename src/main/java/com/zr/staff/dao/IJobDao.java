package com.zr.staff.dao;

import java.util.List;

import com.zr.staff.pojo.Job;

public interface IJobDao {

	List<Job> findAll();

	void addJob(String name, String remark);

	void updateJob(String id, String name, String remark);

	void deleteJob(String id);

	List<Job> searchJob(String name);

}
