package Fan;

import java.awt.*;

import javax.swing.*;

public class TestFan {
	public static void main(String[] args){
		JFrame f = new Fan();
		f.setTitle("Fan");
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
