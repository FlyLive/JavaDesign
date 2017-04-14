package alarm;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.swing.*;

public class Watch extends JPanel{
	private int hour;
	private int minute;
	private int second;
	private String address;
	private Calendar calendar;
	private AudioClip audio;
	public static final String[] dayOfWeek ={"星期天","星期一","星期二",
		"星期三","星期四","星期五","星期六"};
	
	public Watch(){				//构造当前时间
		setCurrentTime();
	}
	
	public Watch(int hour,int minute,int second){		//自定义
		calendar = new GregorianCalendar();
		calendar = new GregorianCalendar(calendar.get(calendar.YEAR),
				calendar.get(calendar.MONTH),
				calendar.get(calendar.DAY_OF_MONTH),hour,minute,second);
	}
	
	public int getHour(){				//get、set方法
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
		setCurrentTime(hour,minute,second);
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
		setCurrentTime(hour,minute,second);
	}

	public void setHour(int hour) {
		this.hour = hour;
		setCurrentTime(hour,minute,second);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	protected void paintComponent(Graphics g){			//画钟表
		super.paintComponent(g);
		
		int clockRadius = (int)(Math.min(getWidth(),getHeight() * 0.8 * 0.5));
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		
		g.drawImage(new ImageIcon("D:/Important/EXE/Watch/图标/表盘.jpg").getImage(),30,0,null);
		
		
		//外圆
		g.setColor(Color.BLACK);
		g.fillOval(xCenter - clockRadius - 10,yCenter - clockRadius - 10
				,2 * (clockRadius + 10),2 * (clockRadius + 10));
		g.setColor(Color.BLACK);
		//内圆
		g.setColor(Color.WHITE);
		g.fillOval(xCenter - clockRadius,yCenter - clockRadius
			,2 * clockRadius,2 * clockRadius);
		g.setColor(Color.BLACK);
		g.drawOval(xCenter - clockRadius,yCenter - clockRadius
			,2 * clockRadius,2 * clockRadius);
		
		g.drawImage(new ImageIcon
				("D:/Important/EXE/Watch/图标/LOGO.jpg").getImage(),
				xCenter - 65,yCenter - clockRadius / 3 * 2,null);
		//时区
		
		g.setFont(new Font("楷体",Font.PLAIN,30));
		g.setColor(Color.RED);
		g.drawString(address,xCenter - 30,yCenter - 30);
		
		//年月日
		g.setFont(new Font("楷体",Font.PLAIN,20));
		g.drawString(calendar.get(calendar.YEAR) + "." + 
				(calendar.get(calendar.MONTH) + 1) + "." +
				calendar.get(calendar.DATE),
				xCenter - 50,yCenter + clockRadius / 3);
		g.drawString(dayOfWeek[calendar.get(calendar.DAY_OF_WEEK) - 1],
				xCenter - 30,yCenter + clockRadius / 3 * 2);
		
		//写数字
		g.setFont(new Font("新宋体",Font.PLAIN,22));
		g.setColor(Color.BLACK);
		g.drawString("12",xCenter - 10,yCenter - clockRadius + 18);
		g.drawString("9",xCenter - clockRadius + 3,yCenter + 6);
		g.drawString("3",xCenter + clockRadius - 10,yCenter + 6);
		g.drawString("6",xCenter - 6,yCenter + clockRadius - 3);
		
		//打整时点
		g.setColor(Color.black);
		g.fillOval(xCenter  - clockRadius / 2,
			(int)(yCenter - clockRadius * Math.sqrt(3) / 2),
			xCenter / 30,xCenter / 30);					//11点
		g.fillOval((int)(xCenter  - clockRadius * Math.sqrt(3) / 2),
			yCenter - clockRadius / 2,
			xCenter / 30,xCenter / 30);					//10点
		
		g.fillOval((int)(xCenter  - clockRadius * Math.sqrt(3) / 2),
			yCenter + clockRadius / 2 - yCenter / 30 - yCenter / 30,
			xCenter / 30,xCenter / 30);					//8点
		g.fillOval(xCenter  - clockRadius / 2,
			(int)(yCenter + clockRadius * Math.sqrt(3) / 2)
			- yCenter / 30 - yCenter / 35,
			xCenter / 30,xCenter / 30);					//7点
		
		g.fillOval(xCenter  + clockRadius / 2 - xCenter / 30,
			(int)(yCenter + clockRadius * Math.sqrt(3) / 2) - yCenter / 17,
			xCenter / 30,xCenter / 30);					//5点
		g.fillOval((int)
			(xCenter  + clockRadius * Math.sqrt(3) / 2) - xCenter / 25,
			yCenter + clockRadius / 2 - yCenter / 25,
			xCenter / 30,xCenter / 30);					//4点
		
		g.fillOval((int)
			(xCenter  + clockRadius * Math.sqrt(3) / 2) - xCenter / 30,
			yCenter - clockRadius / 2,
			xCenter / 30,xCenter / 30);					//2点
		g.fillOval(xCenter  + clockRadius / 2 - xCenter / 50,
			(int)(yCenter - clockRadius * Math.sqrt(3) / 2) + yCenter / 60,
			xCenter / 30,xCenter / 30);					//1点
		
		//秒针
		((Graphics2D)g).setStroke(new BasicStroke(3));
		int sLength = (int)(clockRadius * 0.8);
		int xSecond = (int)
				(xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
		int ySecond = (int)
				(yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
		g.setColor(Color.red);
		g.drawLine(xCenter,yCenter,xSecond,ySecond);
		
		//分针
		int mLength = (int)(clockRadius * 0.65);
		int xMinute = (int)
				(xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
		int yMinute = (int)
				(yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
		g.setColor(Color.blue);
		g.drawLine(xCenter,yCenter,xMinute,yMinute);
		
		//时针
		int hLength = (int)(clockRadius * 0.4);
		int xHour = (int)(xCenter + hLength * 
				Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		int yHour = (int)(yCenter - hLength * 
				Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		g.drawLine(xCenter,yCenter,xHour,yHour);
		
		//定点
		g.setColor(Color.lightGray);
		g.fillOval(xCenter - xCenter / 30,yCenter - xCenter / 30
				,xCenter / 15,xCenter / 15);
	}
	
	public void setCurrentTime(){
		calendar = new GregorianCalendar();
		
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		if((hour == 5 || hour == 11|| hour == 17|| hour == 23)
				&& minute == 59 && second == 58){
			String s = "file:D:/Important/EXE/Watch/大本钟.wav";
			try {
				audio = Applet.newAudioClip(new URL(s));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			audio.play();
		}
		repaint();
	}
	
	public void setCurrentTime(int hour,int minute,int second){
		calendar = new GregorianCalendar(calendar.get(calendar.YEAR),
				calendar.get(calendar.MONTH),
				calendar.get(calendar.DAY_OF_MONTH),hour,minute,second);
		
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		
		if((hour == 5 || hour == 11|| hour == 17|| hour == 23)
				&& minute == 59 && second == 58){
			String s = "file:D:/Important/EXE/Watch/大本钟.wav";
			try {
				audio = Applet.newAudioClip(new URL(s));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			audio.play();
		}
		repaint();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(200,200);
	}
}