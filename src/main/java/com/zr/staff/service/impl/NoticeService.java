package com.zr.staff.service.impl;

import java.util.List;

import com.zr.staff.dao.INoticeDao;
import com.zr.staff.dao.impl.NoticeDao;
import com.zr.staff.pojo.Notice;
import com.zr.staff.service.INoticeService;

public class NoticeService implements INoticeService{

	INoticeDao noticeDao=new NoticeDao();
	public List<Notice> findAll() {
		// TODO Auto-generated method stub
		return noticeDao.findAll();
	}
	public void addNotice(String title, String content) {
		// TODO Auto-generated method stub
		noticeDao.addNotice(title,content);
	}
	public void deleteNotice(int id) {
		// TODO Auto-generated method stub
		noticeDao.deleteNotice(id);
	}
	public void updateNotice(int id, String title, String content) {
		// TODO Auto-generated method stub
		noticeDao.updateNotice(id,title,content);
	}
	public List<Notice> findNotice(String title, String content) {
		// TODO Auto-generated method stub
		return noticeDao.findNotice(title,content);
	}

}
