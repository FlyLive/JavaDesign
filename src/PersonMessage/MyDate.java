package PersonMessage;

import java.io.Serializable;
import java.util.*;

public class MyDate implements Serializable{
	private int year;
	private int month;
	private int day;
	
	public MyDate(){
		Calendar cal = new GregorianCalendar();
		setYear(cal.get(Calendar.YEAR));
		setMonth(cal.get(Calendar.MONTH));
		setDay(cal.get(Calendar.DATE));
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
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	@Override
	public String toString(){
		return getYear() + " Äê " + (getMonth() + 1) + " ÔÂ " + getDay() + " ÈÕ";
	}
}