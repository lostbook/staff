package com.zr.staff.service.impl;

import java.util.List;

import com.zr.staff.dao.IDocumentDao;
import com.zr.staff.dao.impl.DocumentDao;
import com.zr.staff.pojo.Document;
import com.zr.staff.service.IDocumentService;

public class DocumentService implements IDocumentService{

	IDocumentDao documentDao=new DocumentDao();
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return documentDao.findAll();
	}
	public List<Document> searchDocument(String title) {
		// TODO Auto-generated method stub
		return documentDao.searchDocument(title);
	}
	public void addDocument(String title, String remark, String filename) {
		// TODO Auto-generated method stub
		documentDao.addDocument(title,remark,filename);
	}
	public void deleteDocument(int id) {
		// TODO Auto-generated method stub
		documentDao.deleteDocument(id);
	}
	public void updateDocument(int id, String title, String remark, String filename) {
		// TODO Auto-generated method stub
		documentDao.updateDocument(id,title,remark,filename);
	}

}
