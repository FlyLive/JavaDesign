package alarm;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

public class Test3_MyAlarm extends JFrame{
	private alarm a = new alarm();
	private Watch p1 = new Watch(0,0,0);
	private JPanel p2 = new JPanel();
	
	private JPanel list = new JPanel();
	
	private	JTextField jtfHour = new JTextField("0",3);
	private	JTextField jtfMinute = new JTextField("0",3);
	private	JTextField jtfSecond = new JTextField("0",3);
	private JButton jbtTry = new JButton("♬");
	private JButton jbtStart = new JButton("▶");
	
	private JComboBox musicName = new JComboBox(a.getmName());
	
	private JLabel jlTime = new JLabel
			("剩余时间:  " + p1.getHour() + ":" + p1.getMinute() + ":" +
	p1.getSecond());
	
	private Timer timer = new Timer(1000,new TimerListener());
	
	public Test3_MyAlarm() throws IOException{
		jlTime.setFont(new Font("楷体",Font.PLAIN,20));
		p1.setAddress("重庆");
		
		list.add(jbtStart);
		list.add(musicName);
		list.add(jbtTry);
		
		add(list,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		
		p2Add();
		
		jtfHour.addActionListener(new JTFListener());
		jtfMinute.addActionListener(new JTFListener());
		jtfSecond.addActionListener(new JTFListener());
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				a.getAudio().stop();
			}
		});
		
		musicName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				int n = musicName.getSelectedIndex();
				a.getAudio().stop();
				a.setMusicIndex(n);
				a.setName(a.getmName()[n]);
			}
		});
		
		jbtTry.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbtTry.getText() == "♬"){
					jbtTry.setLabel("||");
					a.getAudio().play();
				}
				else if(jbtTry.getText() == "||"){
					jbtTry.setLabel("♬");
					a.getAudio().stop();
				}
			}
		});
		
		jbtStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbtStart.getText() == "▶"){
					jbtStart.setLabel("||");
					System.out.println(new GregorianCalendar().getTime());
					timer.start();
					p2.removeAll();
					p2.add(jlTime);
				}
				else if(jbtStart.getText() == "||"){
					jbtStart.setLabel("▶");
					timer.stop();
					a.getAudio().stop();
					p2.removeAll();
					p2Add();
				}
			}
		});
	}
	
	public void p2Add(){
		p2.add(new JLabel("Hour"));
		p2.add(jtfHour);
		p2.add(new JLabel("Minute"));
		p2.add(jtfMinute);
		p2.add(new JLabel("Second"));
		p2.add(jtfSecond);
		add(p2,BorderLayout.SOUTH);
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if((p1.getHour() * 360 +
				p1.getMinute() * 60 + p1.getSecond()) > 0){
				if(p1.getSecond() == 0 &&( p1.getMinute() > 0
						|| p1.getHour() > 0)){
					if(p1.getMinute() > 0){
						p1.setMinute(p1.getMinute() - 1);
					}
					else if(p1.getMinute() == 0 && p1.getHour() > 0){
						p1.setMinute(59);
						p1.setHour(p1.getHour() - 1);
					}
					p1.setSecond(60);
				}
				p1.setSecond(p1.getSecond() - 1);
			}
			
			if((p1.getHour() * 360 +
				p1.getMinute() * 60 + p1.getSecond()) == 0){
				a.getAudio().loop();
				timer.stop();
				System.out.println(new GregorianCalendar().getTime());
			}
			
			p1.setCurrentTime(p1.getHour(),p1.getMinute(),p1.getSecond());
			
			jlTime.setText
			("剩余时间:  " + p1.getHour() + ":" + p1.getMinute() + ":" +
			p1.getSecond());
		}
	}
	
	private  class JTFListener implements ActionListener{
		public void actionPerformed(ActionEvent e){			//文本域设置时钟
			int hour = p1.getHour();
			int minute = p1.getMinute();
			int second = p1.getSecond();
			
			try{
				hour = Integer.parseInt(jtfHour.getText());

			}
			catch(NumberFormatException e1){
				jtfHour.setText("0");
				hour = 0;
			}
			
			try{
				minute = Integer.parseInt(jtfMinute.getText());
			}
			catch(NumberFormatException e1){
				jtfMinute.setText("0");
				minute = 0;
			}
			
			try{
				second = Integer.parseInt(jtfSecond.getText());
			}
			catch(NumberFormatException e1){
				jtfSecond.setText("0");
				second = 0;
			}
			
			p1.setHour(hour);
			p1.setMinute(minute);
			p1.setSecond(second);
		}
	}
	public static void main(String[] args) throws IOException{
		Test3_MyAlarm f = new Test3_MyAlarm();
		f.setTitle("MyAlarm");
		f.setIconImage(new ImageIcon
				("D:/Important/EXE/MyAlarm/图标/闹钟.jpg").getImage());
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}
}