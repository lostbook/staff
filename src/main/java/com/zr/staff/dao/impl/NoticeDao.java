package com.zr.staff.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zr.staff.dao.INoticeDao;
import com.zr.staff.pojo.Notice;
import com.zr.staff.utils.DBUtils;

public class NoticeDao implements INoticeDao{

	public List<Notice> findAll() {
		// TODO Auto-generated method stub
		List<Notice> noticeList=new ArrayList<Notice>();
		String sql="select * from notice_inf";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String title=resultSet.getString("title");
				String content=resultSet.getString("content");
				Timestamp create_date=resultSet.getTimestamp("create_date");
				int user_id=resultSet.getInt("user_id");
				Notice notice=new Notice(id,title,content,create_date,user_id);
				noticeList.add(notice);
			}
			return noticeList;
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

	public void addNotice(String title, String content) {
		// TODO Auto-generated method stub
		String sql="insert notice_inf (title,content) values (?,?)";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void deleteNotice(int id) {
		// TODO Auto-generated method stub
		String sql="delete from notice_inf where id="+id+"";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateNotice(int id, String title, String content) {
		// TODO Auto-generated method stub
		String sql="update notice_inf set title=?, content=? where id=?";
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, id);
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

	public List<Notice> findNotice(String title, String content) {
		// TODO Auto-generated method stub
		List<Notice> noticeList=new ArrayList<Notice>();
		String sql="";
		if(title==""&&content=="") {
			sql="select * from notice_inf";
		}
		else if(title==""&&content!="") {
			sql="select * from notice_inf where content like '%"+content+"%'";
		}
		else if(title!=""&&content=="") {
			sql="select * from notice_inf where title like '%"+title+"%'";
		}
		else {
			sql="select * from notice_inf where title like '%"+title+"%' and content like '%"+content+"%'";
		}
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id1=resultSet.getInt("id");
				String title1=resultSet.getString("title");
				String content1=resultSet.getString("content");
				Timestamp create_date1=resultSet.getTimestamp("create_date");
				int user_id1=resultSet.getInt("user_id");
				Notice notice=new Notice(id1,title1,content1,create_date1,user_id1);
				noticeList.add(notice);
			}
			return noticeList;
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

}
