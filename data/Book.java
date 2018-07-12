package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book {
	private int ID;
	private String isbn="";
	private ArrayList<Author> author= new ArrayList<Author>();
	private String title="";
	private String series="";
	private Datum pubdate=new Datum(0,0,0);
	private Date creation=new Date();
	
	public Book() {
		
		Date today = new Date();
		System.out.println(today.toString());
		
		Datum a=new Datum(12, 6, 2018);
		System.out.println(a.toString());
	}
}


