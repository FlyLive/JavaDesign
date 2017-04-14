package Section_6;

import java.awt.*;

import javax.swing.*;

public class MazeCell extends JPanel {
	private int value;

	public MazeCell(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		paintComponent(getGraphics());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("img/Section6/����.jpg").getImage(), 0, 0,
				this.getWidth(), this.getHeight(), null);
		ImageIcon image = null;
		switch (value) {
		case 0:
			image = new ImageIcon("img/Section6/��.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 1:
			image = new ImageIcon("img/Section6/��.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 2:
			image = new ImageIcon("img/Section6/��.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 3:
			image = new ImageIcon("img/Section6/��.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 4:
			image = new ImageIcon("img/Section6/��.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 5:
			image = new ImageIcon("img/Section6/���.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		case 6:
			image = new ImageIcon("img/Section6/�յ�.png");
			g.drawImage(image.getImage(), 0, 0, null);
			break;
		default:
			break;
		}
	}
}
