package Tic_Tac_Toe;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Test3_Tic extends JPanel{
	private JFrame f = new JFrame();
	
	private AudioClip audio;
	
	private int xMin = 220;
	private int yMin = 400;
	
	private int xMax = 500;
	private int yMax = 440;
	
	public Test3_Tic(){

		f.add(this);
		f.setTitle("test");
		f.setSize(720,540);
		f.setVisible(true);
		f.setResizable(false);
		f.setIconImage(new ImageIcon
				("D:/Important/EXE/Tic_Tac_Toe/Í¼±ê/LOGO.jpg").getImage());
		f.setLocationRelativeTo(null);
		
		String s = "file:D:/Important/EXE/Tic_Tac_Toe/Music.wav";
		try {
			audio = Applet.newAudioClip(new URL
				(s));
		} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		audio.loop();
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
					//audio.stop();
			}
		});
		
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getX() > xMin && e.getX() < xMax &&
					e.getY() > yMin && e.getY() < yMax){
					
					Tic_Tac_Toe t = new Tic_Tac_Toe();
					audio.stop();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g){
		g.drawImage(new ImageIcon
				("D:/Important/EXE/Tic_Tac_Toe/Í¼±ê/LOGO.png").getImage(),
				0,0,720,510,null);
	}
	
	public static void main(String[] args){
		new Test3_Tic();
	}
}