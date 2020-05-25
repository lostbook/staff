package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zr.staff.dao.IDocumentDao;
import com.zr.staff.pojo.Document;
import com.zr.staff.utils.DBUtils;

public class DocumentDao implements IDocumentDao{

	public List<Document> findAll() {
		// TODO Auto-generated method stub
		List<Document> documentList=new ArrayList<Document>();
		String sql="select * from document_inf";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String title=resultSet.getString("title");
				String filename=resultSet.getString("filename");
				String remark=resultSet.getString("remark");
				Timestamp create_date=resultSet.getTimestamp("create_date");
				int user_id=resultSet.getInt("user_id");
				Document document=new Document(id,title,filename,remark,create_date,user_id);
				documentList.add(document);
			}
			return documentList;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Document> searchDocument(String title) {
		// TODO Auto-generated method stub
		List<Document> documentList=new ArrayList<Document>();
		String sql="select * from document_inf where title like '%"+title+"%'";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String title1=resultSet.getString("title");
				String filename=resultSet.getString("filename");
				String remark=resultSet.getString("remark");
				Timestamp create_date=resultSet.getTimestamp("create_date");
				int user_id=resultSet.getInt("user_id");
				Document document=new Document(id,title1,filename,remark,create_date,user_id);
				documentList.add(document);
			}
			return documentList;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void addDocument(String title, String remark, String filename) {
		// TODO Auto-generated method stub
		String sql="insert document_inf (title,remark,filename) values (?,?,?)";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, remark);
			preparedStatement.setString(3, filename);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteDocument(int id) {
		// TODO Auto-generated method stub
		String sql="delete from document_inf where id='"+id+"'";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateDocument(int id, String title, String remark, String filename) {
		// TODO Auto-generated method stub
		String sql="update document_inf set title=?, remark=?, filename=? where id=?";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, remark);
			preparedStatement.setString(3, filename);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
