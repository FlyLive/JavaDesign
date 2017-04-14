package alarm;

import java.util.*;

public class Time {
	private int hour;
	private int minute;
	private int second;
	
	public Time(){
		setCurrentTime();
	}
	
	public Time(long elapseTime){
		Calendar c = new GregorianCalendar(2002,0,1);
		c.add(c.SECOND,(int)elapseTime);
		System.out.println(c.getTime());
	}
	
	public Time(int hour, int minute,int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void setTime(long elapseTime){
		Calendar c = new GregorianCalendar(2002,0,1);
		c.add(c.SECOND,(int)elapseTime);
		System.out.println(c.getTime());
	}
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public void setCurrentTime(){
		Calendar calendar = new GregorianCalendar();
		
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}
	
	@Override
	public String toString(){
		return "时间：" + hour + ":" + minute + ":" + second + " " + " ";
	}
}