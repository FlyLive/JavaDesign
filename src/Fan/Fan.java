package Fan;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fan extends JFrame{
	private fanPanel fan = new fanPanel();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	
	private JButton jbtStart = new JButton("Start");		//p2
	private JButton jbtStop = new JButton("Stop");
	private JButton jbtReverse = new JButton("Reverse");
	
	private ButtonGroup group = new ButtonGroup();		//p3
	private JRadioButton jrbRed,jrbBlue,jrbGray;
	
	private JScrollBar jscbHort =
			new JScrollBar(JScrollBar.HORIZONTAL);		//p4
	
	private int cord = 0;
	private int startValue = 500;
	
	private Timer startTimer =
			new Timer(startValue,new TimerListener());
	private Timer reverseTimer =
			new Timer(startValue,new TimerListener());
	
	public Fan(){
		add(fan,BorderLayout.CENTER);
		drawP1();
		drawP2();
		drawP3();
		fan.setForeground(Color.black);
	}
	
	public void drawP1(){
		p1.setLayout(new GridLayout(1,3));
		p1.add(jbtStart);
		p1.add(jbtStop);
		p1.add(jbtReverse);
		
		add(p1,BorderLayout.NORTH);
		jbtAction();
	}
	
	public void jbtAction(){
		jbtStart.addActionListener(new ButtonListener());
		jbtStop.addActionListener(new ButtonListener());
		jbtReverse.addActionListener(new ButtonListener());
	}
	
	public void drawP2(){
		p2.setLayout(new GridLayout(3,1));
		p2.add(jrbRed = new JRadioButton("Red"));
		p2.add(jrbBlue = new JRadioButton("Blue"));
		p2.add(jrbGray = new JRadioButton("Gray"));
		group.add(jrbRed);
		group.add(jrbBlue);
		group.add(jrbGray);
		
		add(p2,BorderLayout.EAST);
		jrbAction();
	}
	
	public void jrbAction(){
		jrbRed.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				fan.setForeground(Color.red);
				repaint();
			}
		});
		
		jrbBlue.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				fan.setForeground(Color.BLUE);
				repaint();
			}
		});
		
		jrbGray.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				fan.setForeground(Color.GRAY);
				repaint();
			}
		});
	}
	
	public void drawP3(){
		p3.setLayout(new GridLayout(1,1));
		p3.add(jscbHort);
		add(p3,BorderLayout.SOUTH);
		
		jscbHort.addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				double value = jscbHort.getValue();
				double maxValue = jscbHort.getMaximum();
				if(startValue - value * 100 > 0){
					startTimer.setDelay((int)
							(startValue - value * 100));
					reverseTimer.setDelay((int)
							(startValue - value * 100));
				}
			}
		});
	}

	class fanPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;
			int radius = (int)(Math.min(getWidth(),getHeight() * 0.4));
			
			int x = xCenter - radius;
			int y = yCenter - radius;
			

			g.fillArc(x,y,2 * radius,2 * radius,cord,30);
			g.fillArc(x,y,2 * radius,2 * radius,cord + 90,30);
			g.fillArc(x,y,2 * radius,2 * radius,cord + 180,30);
			g.fillArc(x,y,2 * radius,2 * radius,cord + 270,30);
			g.setColor(Color.BLACK);
			g.drawOval(x - 15,y - 15,2 * radius + 30,2 * radius + 30);
		}
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent t){
			if(t.getSource() == startTimer){
				cord--;
				repaint();
			}
			else if(t.getSource() == reverseTimer){
				cord++;
				repaint();
			}
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbtStart){
				reverseTimer.stop();
				startTimer.start();
			}
			else if(e.getSource() == jbtReverse){
				startTimer.stop();
				reverseTimer.start();
			}
			else if(e.getSource() == jbtStop){
				reverseTimer.stop();
				startTimer.stop();
			}
		}
	}
}