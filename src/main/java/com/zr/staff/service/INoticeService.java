package com.zr.staff.service;

import java.util.List;

import com.zr.staff.pojo.Notice;

public interface INoticeService {

	List<Notice> findAll();

	void addNotice(String title, String content);

	void deleteNotice(int id);

	void updateNotice(int id, String title, String content);

	List<Notice> findNotice(String title, String content);

}
