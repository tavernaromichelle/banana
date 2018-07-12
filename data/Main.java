package data;

import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import db.Drop;
import db.Postgresql;

public class Main {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception {
		Book book=new Book();
		Postgresql po=new Postgresql("", "localhost", "5432", "postgres", "paulchen321a" );
		po.create_database("test");
		po.createTables();
		Drop.drop(po.c);
		Drop.dropdb("test", po);
		
	}
}


