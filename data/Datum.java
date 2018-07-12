package data;

public class Datum {
	private int year;
	private int month;
	private int day;
	
	public Datum(int day, int month, int year) {
		if (day<32 && day>0) {
			this.day =day;
		}else {
			this.day=1;
		}
		if (month<13 && month>0) {
			this.month=month;
		}else {
			this.month=12;
		}
		this.year=year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if (month<13 && month>0) {
			this.month=month;
		}else {
			this.month=12;
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if (day<32 && day>0) {
			this.day =day;
		}else {
			this.day=1;
		}
	}
	
	public String toString(){
	 return ""+day+"."+month+"."+year;
	}
}
