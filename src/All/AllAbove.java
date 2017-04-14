package All;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

import PersonMessage.Test3;
import Tic_Tac_Toe.Test3_Tic;
import alarm.Test3_MyAlarm;

public class AllAbove  extends JFrame{
	
	private pTitle p1 = new pTitle();
	
	private pMain p2 = new pMain();
	
	private pTime p3 = new pTime();
	
	public AllAbove(){
		add(p1,BorderLayout.NORTH);						//标题面板
		
		add(p2,BorderLayout.CENTER);					//主面板
		
		add(p3,BorderLayout.SOUTH);						//时间面板
	}
	
	private class pTitle extends JPanel{
		//标题面板
		
		private int move = 232;
		private JLabel jlb = new JLabel("课程设计 -- 381 -- 刘峰");
		private Timer jlbTimer = new Timer(10,new jlbTimerListener());
		
		public pTitle(){
			add(jlb);
			setBorder(new LineBorder(Color.blue,5));
			jlb.setFont(new Font("行楷",Font.PLAIN,20));
			jlbTimer.start();
		}
		
		private class jlbTimerListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(move < - jlb.getWidth()){
					move = 688;
				}
				else{
					move--;
				}
				jlb.setLocation(move,jlb.getY());
			}
		}
	}
	
	private class pMain extends JPanel{					//主面板
		private JButton[] jbt = new JButton[3];
		public pMain(){
			jbt[0] = new JButton(new ImageIcon
				("D:/Important/EXE/PersonMessage/图标/logo.jpg"));	//按钮数组
			jbt[1] = new JButton(new ImageIcon
				("D:/Important/EXE/Tic_Tac_Toe/图标/LOGO.jpg"));
			jbt[2] = new JButton(new ImageIcon
				("D:/Important/EXE/MyAlarm/图标/闹钟.jpg"));
			
			add(jbt[0]);
			add(jbt[1]);
			add(jbt[2]);
			
			jbt[0].setContentAreaFilled(false);
			jbt[0].setBorderPainted(false);
			
			jbt[1].setContentAreaFilled(false);
			jbt[1].setBorderPainted(false);
			
			jbt[2].setContentAreaFilled(false);
			jbt[2].setBorderPainted(false);
			
			jbt[0].addActionListener(new jbtActionListener());
			jbt[1].addActionListener(new jbtActionListener());
			jbt[2].addActionListener(new jbtActionListener());
		}
			
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.drawImage(new ImageIcon
				("D:/Important/EXE/All/图标/背景.jpg").getImage(),15,10,null);

			g.setColor(Color.RED);
			g.setFont(new Font("行楷",Font.PLAIN,20));
			
			g.drawString("PersonMessage",155,80);	//PersonMessage
			jbt[0].setLocation(145,100);
			
			g.drawString("Tic_Tac_Toe",280,280);	//Tic_Tac_Toe
			jbt[1].setLocation(245,300);

			g.drawString("MyAlarm",430,80);			//MyAlarm
			jbt[2].setLocation(400,100);
		}
		
		private class jbtActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == jbt[0]){
					new Test3();
				}
				else if(e.getSource() == jbt[1]){
					new Test3_Tic();
				}
				else if(e.getSource() == jbt[2]){
					try {
						Test3_MyAlarm f = new Test3_MyAlarm();
						f.setTitle("MyAlarm");
						f.setIconImage(new ImageIcon
								("D:/Important/EXE/MyAlarm/图标/闹钟.jpg")
										.getImage());
						f.setSize(400,400);
						f.setLocationRelativeTo(null);
						f.setVisible(true);
						f.setResizable(false);
					}
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	private class pTime extends JPanel{					//时间面板
		private Calendar calendar = new GregorianCalendar();
		private JLabel jlbTime = new JLabel
				(calendar.get(calendar.HOUR_OF_DAY) + ":" +
				calendar.get(calendar.MINUTE) + ":" +
				calendar.get(calendar.SECOND));
		private Timer time = new Timer(1000,new timeListener());
		
		public pTime(){
			setBorder(new LineBorder(Color.blue,5));
			jlbTime.setFont(new Font("行楷",Font.PLAIN,20));
			add(jlbTime);
			time.start();
		}
		
		private class timeListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentTime();
			}
		}
		
		public void setCurrentTime(){
			calendar = new GregorianCalendar();
			jlbTime.setText(calendar.get(calendar.HOUR_OF_DAY) + ":" +
					calendar.get(calendar.MINUTE) + ":" +
					calendar.get(calendar.SECOND));
		}
	}
	
	public static void main(String[] args){
		AllAbove f = new AllAbove();
		f.setSize(688,585);
		f.setTitle("All");
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}