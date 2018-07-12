package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Drop {
	public static void drop(Connection c) {
		Statement stmt = null;
		try {

			
			stmt = c.createStatement();
			
			String sql = "DROP TABLE IF EXISTS AUTHOR, SERIES, EDITION, GENRE, PUBLISHER, LANGUAGE, BOOKS, REZI";
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Table created successfully");
		
	}
	
	public static void dropdb(String dbname, Postgresql po) {
		Statement stmt = null;
		try {
			
			po.c.close();
			String url = "jdbc:postgresql://" + po.server + ":" + po.port+"/"+""  ;
			po.c = DriverManager.getConnection(url, po.username, po.pw);
			
			stmt = po.c.createStatement();
			
			String sql = "DROP DATABASE IF EXISTS "+ dbname;
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Table created successfully");
		
	}
	
	
	

}
