package alarm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Test2_Watch extends JFrame{
	private Watch p1 = new Watch();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	
	private	JTextField jtfHour = new JTextField("0",3);
	private	JTextField jtfMinute = new JTextField("0",3);
	private	JTextField jtfSecond = new JTextField("0",3);
	private JButton jbtRenew = new JButton("Renew");
	private JLabel jlTime = new JLabel
			(p1.getHour() + ":" + p1.getMinute() + ":" + p1.getSecond());
	
	public Test2_Watch(){
		jlTime.setFont(new Font("楷体",Font.PLAIN,20));
		p1.setAddress("重庆");
		p3.add(jlTime);
		add(p3,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		
		p2.add(new JLabel("Hour"));
		p2.add(jtfHour);
		p2.add(new JLabel("Minute"));
		p2.add(jtfMinute);
		p2.add(new JLabel("Second"));
		p2.add(jtfSecond);
		add(p2,BorderLayout.SOUTH);
		p2.add(jbtRenew);
		
		jtfHour.addActionListener(new JTFListener());
		jtfMinute.addActionListener(new JTFListener());
		jtfSecond.addActionListener(new JTFListener());
		
		Timer timer = new Timer(1000,new TimerListener());
		timer.start();
		
		jbtRenew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p1.setCurrentTime();
				jlTime.setText
				(p1.getHour() + ":" + p1.getMinute() + ":" + p1.getSecond());
			}
		});
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			p1.setSecond(p1.getSecond() + 1);
			p1.setMinute(p1.getMinute() + p1.getSecond() / 60);
			p1.setHour(p1.getHour() + p1.getMinute() / 60);
			
			p1.setSecond(p1.getSecond() == 60 ? 0:p1.getSecond());
			p1.setMinute(p1.getMinute() == 60 ? 0:p1.getMinute());
			p1.setHour(p1.getHour() == 24 ? 0:p1.getHour());
			
			p1.setCurrentTime(p1.getHour(),p1.getMinute(),p1.getSecond());
			
			jlTime.setText
			(p1.getHour() + ":" + p1.getMinute() + ":" + p1.getSecond());
		}
	}
	
	private class JTFListener implements ActionListener{
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
	
	public static void main(String[] args){
		Test2_Watch f = new Test2_Watch();
		f.setTitle("StillClock");
		f.setIconImage(new ImageIcon("D:/Important/EXE/Watch/图标/Watch.png").getImage());
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}
}
