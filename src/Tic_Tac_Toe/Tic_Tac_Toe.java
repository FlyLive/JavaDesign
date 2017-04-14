package Tic_Tac_Toe;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;

public class Tic_Tac_Toe extends JPanel {
	private JFrame t = new JFrame();

	private static char whoseTurn = 'X';

	private p2Panel p2 = new p2Panel();
	private static cell[][] cell = new cell[3][3];

	private ImageIcon ImgStart = new ImageIcon(
			"D:/Important/EXE/Tic_Tac_Toe/图标" + "/Start.jpg");

	private ImageIcon ImgStop = new ImageIcon("D:/Important/EXE/Tic_Tac_Toe/图标"
			+ "/Stop.jpg");

	private JButton begain = new JButton(new ImageIcon(
			"D:/Important/EXE/Tic_Tac_Toe/图标" + "/Play.jpg"));
	private JButton jbtChangeL = new JButton(new ImageIcon(
			"D:/Important/EXE/Tic_Tac_Toe/图标" + "/Left.jpg"));
	private JButton jbtChangeR = new JButton(new ImageIcon(
			"D:/Important/EXE/Tic_Tac_Toe/图标" + "/Right.jpg"));
	private JButton jbtStart = new JButton(ImgStop);

	private String[] s = new String[3];
	private int index;

	private AudioClip audio;
	static JLabel message = new JLabel("X's turn,Restart the game.");

	public Tic_Tac_Toe() {

		t.setTitle("Tic_Tac_Toe");
		t.setSize(1000, 520);
		t.setIconImage(new ImageIcon("D:/Important/EXE/Tic_Tac_Toe/图标/LOGO.jpg")
				.getImage());
		t.setLocationRelativeTo(null);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setVisible(true);
		t.setResizable(false);
		t.add(this);

		add(begain);
		add(jbtChangeL);
		add(jbtChangeR);
		add(jbtStart);

		index = 0;
		s[0] = "D:/Important/EXE/Tic_Tac_Toe/图标/背景.jpg";
		s[1] = "D:/Important/EXE/Tic_Tac_Toe/图标/背景1.jpg";
		s[2] = "D:/Important/EXE/Tic_Tac_Toe/图标/背景2.jpg";

		begain.addActionListener(new jbtActionListener());
		jbtChangeL.addActionListener(new jbtActionListener());
		jbtChangeR.addActionListener(new jbtActionListener());
		jbtStart.addActionListener(new jbtActionListener());

		begain.setBorderPainted(false);
		jbtChangeL.setBorderPainted(false);
		jbtChangeR.setBorderPainted(false);
		jbtStart.setBorderPainted(false);

		begain.setContentAreaFilled(false);
		jbtChangeL.setContentAreaFilled(false);
		jbtChangeR.setContentAreaFilled(false);
		jbtStart.setContentAreaFilled(false);

		draw();
		add(message, BorderLayout.SOUTH);

		String s = "file:D:/Important/EXE/Tic_Tac_Toe/Music.wav";
		try {
			audio = Applet.newAudioClip(new URL(s));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		audio.loop();

		t.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				audio.stop();
			}
		});
		message.setFont(new Font("楷体", Font.PLAIN, 20));
	}

	private class jbtActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == begain) {
				for (int i = 0; i < cell.length; i++) {
					for (int j = 0; j < cell[i].length; j++) {
						p2.remove(cell[i][j]);
					}
				}
				whoseTurn = 'X';
				message.setText("X's turn,Restart the game.");
				draw();
				p2.repaint();
			} else if (e.getSource() == jbtChangeL) {

				if (index == (s.length - 1)) {
					index = 0;
				} else {
					index++;
				}
				p2.repaint();
			} else if (e.getSource() == jbtChangeR) {

				if (index == 0) {
					index = s.length - 1;
				} else {
					index--;
				}
				p2.repaint();
			} else if (e.getSource() == jbtStart) {
				if (jbtStart.getIcon() == ImgStart) {
					jbtStart.setIcon(ImgStop);
					audio.loop();
				} else if (jbtStart.getIcon() == ImgStop) {
					jbtStart.setIcon(ImgStart);
					audio.stop();
				}
			}

		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(new ImageIcon("D:/important/EXE/Tic_Tac_Toe/图标/Game.jpg")
				.getImage(), 0, 0, null);
		p2.setBounds(470, 50, 400, 400);

		begain.setLocation(870, 100);
		jbtChangeL.setLocation(870, 230);
		jbtChangeR.setLocation(930, 230);
		jbtStart.setLocation(900, 360);

		message.setLocation(470, 20);
	}

	public static char getWhoseTurn() {
		return whoseTurn;
	}

	public static void setWhoseTurn(char newwhoseTurn) {
		whoseTurn = newwhoseTurn;
	}

	public void draw() {
		p2.setLayout(new GridLayout(3, 3, 0, 0));
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				p2.add(cell[i][j] = new cell(true));
			}
		}
		add(p2);
	}

	public static boolean full() {
		for (int i = 0; i < cell.length; i++)
			for (int j = 0; j < cell[i].length; j++) {
				if (cell[i][j].getWhose() == ' ')
					return false;
			}
		return true;
	}

	private class p2Panel extends JPanel { // 画矩阵
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon(s[index]).getImage(), 0, 0, null);
			g.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3);
			g.drawLine(0, getHeight() / 3 * 2, getWidth(), getHeight() / 3 * 2);
			g.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight());
			g.drawLine(getWidth() / 3 * 2, 0, getWidth() / 3 * 2, getHeight());
		}
	}

	public static boolean won(char c) { // 判断输赢
		for (int i = 0; i < 3; i++) {
			if (cell[i][0].getWhose() == c && cell[i][1].getWhose() == c
					&& cell[i][2].getWhose() == c) {
				return true;
			} else if (cell[0][i].getWhose() == c && cell[1][i].getWhose() == c
					&& cell[2][i].getWhose() == c) {
				return true;
			}
		}

		if (cell[0][0].getWhose() == c && cell[1][1].getWhose() == c
				&& cell[2][2].getWhose() == c) {
			return true;
		}

		if (cell[0][2].getWhose() == c && cell[1][1].getWhose() == c
				&& cell[2][0].getWhose() == c) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {

		Tic_Tac_Toe t = new Tic_Tac_Toe();
	}
}