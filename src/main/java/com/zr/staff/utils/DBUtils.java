package com.zr.staff.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Administrator
 * 连接数据库的工具类
 */
public class DBUtils {
	public static String driver;
	public static String url;
	public static String username;
	public static String password;

	// 用于存储db.properties文件中数据的集合
	private static Properties properties = new Properties();

	/** 私有化构造方法 */
	private DBUtils() {
	}

	static {
		try {
			// 首先读取db.properties文件
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");

			// 把读取到db.properties文件中的数据加入到Properties集合中
			properties.load(is);
			
			// 把读取到的数据赋值到程序中
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			// 注册驱动
			Class.forName(driver);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 调用该方法就获取到数据库的连接对象
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
