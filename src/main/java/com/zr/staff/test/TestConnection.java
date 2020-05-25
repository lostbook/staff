package com.zr.staff.test;

import java.sql.Connection;

import com.zr.staff.utils.DBUtils;

public class TestConnection {
	public static void main(String[] args) {	
		Connection connection = DBUtils.getConnection();
		System.out.println(connection);
	}
}
