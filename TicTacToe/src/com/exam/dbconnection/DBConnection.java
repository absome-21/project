package com.exam.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DBConnection {
	private static String dbhost = "jdbc:mysql://localhost:3306/tictactoe";
	private static String username = "root";
	private static String password = "P@ssw0rd";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try  {	
			System.out.println("dsadsa");
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
}
