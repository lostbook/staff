package com.zr.staff.dao;

import java.util.List;

import com.zr.staff.pojo.Document;

public interface IDocumentDao {

	List<Document> findAll();

	List<Document> searchDocument(String title);

	void addDocument(String title, String remark, String filename);

	void deleteDocument(int id);

	void updateDocument(int id, String title, String remark, String filename);

}
