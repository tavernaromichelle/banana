package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.postgresql.util.PSQLException;

public class Postgresql {
	public static Connection c = null;
	public static String database;
	public static String server;
	public static String port;
	public static String username;
	public static String pw;
	public static String dbname;
	static boolean new_database;

	private static Boolean getConnect() {

		Boolean complete = false;
		if (database.length() > 0) {

			try {
				Class.forName("org.postgresql.Driver");
				String url = "jdbc:postgresql://" + server + ":" + port + "/" + database;
				c = DriverManager.getConnection(url, username, pw);
				System.out.println("connect to: " + url);

			} catch (Exception e) {
				System.out.println("fail");

			}
		} else {
			System.out.println("keine DB angegeben!");
		}

		return complete;
	}

	public Postgresql(String database, String server, String port, String username, String pw) {
		this.database = database;
		this.server = server;
		this.port = port;
		this.username = username;
		this.pw = pw;
		System.out.println("Create Connection to: "+ server + ":" + port );
	}

	public static void create_database(String name) {
		database = name;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + server + ":" + port+"/"+""  ;
			c = DriverManager.getConnection(url, username, pw);
			stmt = c.createStatement();
			String sql = "CREATE DATABASE " + database + ";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			System.out.println("Database " + database + " created successfully");
			getConnect();
			new_database=true;

		}  catch (PSQLException e) {
			getConnect();
			new_database=false;
			
		}catch (Exception e) {
			System.err.println("Create DB: "+e.getClass().getName() + ": " + e.getMessage());
		}
		
	}
	
	

	public static void createTables() {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS AUTHOR " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " FIRST_NAME           TEXT, " + " LAST_NAME            TEXT     NOT NULL) ";
			stmt.executeUpdate(sql);
			
			//sql = "INSERT INTO AUTHOR(ID,FIRST_NAME, LAST_NAME) VALUES (0,'Anna','Bier')";
			//stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE IF NOT EXISTS GENRE " + "(ID SERIAL PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT NOT NULL) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS SERIES " + "(ID SERIAL PRIMARY KEY NOT NULL,"
					+ " NAME TEXT NOT NULL UNIQUE,"+"CLOSED BOOLEAN DEFAULT FALSE) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS PUBLISHER " + "(ID SERIAL PRIMARY KEY NOT NULL,"
					+ " NAME TEXT NOT NULL) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS EDITION " + "(ID SERIAL PRIMARY KEY NOT NULL,"
					+ " NAME TEXT NOT NULL) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS LANGUAGE " + "(ID SERIAL PRIMARY KEY NOT NULL,"
					+ " NAME TEXT NOT NULL) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS REZI " + "(ID SERIAL PRIMARY KEY NOT NULL) ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS BOOKS " + 
					"(ID SERIAL PRIMARY KEY NOT NULL,"+
					" ISBN TEXT,"+
					" TITLE TEXT NOT NULL,"+
					" SERIES SERIAL,"+
					" PART INT DEFAULT 0,"+
					" PUBDATE TEXT,"+
					" PUBLISHER SERIAL,"+
					" EDITION SERIAL,"+
					" PRICE FLOAT DEFAULT 0.0,"+
					" LANGUAGE SERIAL,"+
					" OWN BOOLEAN DEFAULT FALSE,"+
					" WANT BOOLEAN DEFAULT FALSE,"+
					" READ BOOLEAN DEFAULT FALSE,"+
					" PAGES INT DEFAULT 0,"+
					" FOREIGN KEY (SERIES) REFERENCES SERIES (ID) ON DELETE CASCADE,"+
					" FOREIGN KEY (PUBLISHER) REFERENCES PUBLISHER (ID) ON DELETE CASCADE,"+
					" FOREIGN KEY (EDITION) REFERENCES EDITION (ID) ON DELETE CASCADE,"+
					" FOREIGN KEY (LANGUAGE) REFERENCES LANGUAGE (ID) ON DELETE CASCADE"+
					"  ) ";
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Tables created successfully");
	}

	public String toString() {
		return "";

	}
}