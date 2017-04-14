package Section_5;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class CrossRiverPanel extends JPanel {
	private List<State> result;
	private int offset = 10;

	private int manPositionX = 50;
	private int wolfPositionX = 50;
	private int sheepPositionX = 50;
	private int grassPositionX = 50;

	private int manPositionY = 125;
	private int wolfPositionY = 225;
	private int sheepPositionY = 325;
	private int grassPositionY = 425;

	private int boatPosition = 200;

	private boolean isManMoved;
	private boolean isWolfMoved;
	private boolean isSheepMoved;
	private boolean isGrassMoved;

	private boolean isHeadIntoNorth = true;

	private Image bufferImage;

	public CrossRiverPanel() {
	}
	//���ƽ��
	public void drawResult(List<State> list) {
		result = list;
		manPositionX = 50;
		wolfPositionX = 50;
		sheepPositionX = 50;
		grassPositionX = 50;
		boatPosition = 200;
		isHeadIntoNorth = true;
		drawResult();
	}
	//���ƽ��
	private void drawResult() {
		for (int i = 0; i < result.size() - 1; i++) {
			State originState = result.get(i);
			State changeState = result.get(i + 1);
			isManMoved = originState.getStateOfFarmer() != changeState
					.getStateOfFarmer();
			isWolfMoved = originState.getStateOfWolf() != changeState
					.getStateOfWolf();
			isSheepMoved = originState.getStateOfSheep() != changeState
					.getStateOfSheep();
			isGrassMoved = originState.getStateOfGrass() != changeState
					.getStateOfGrass();
			
			for (int j = 0; j < 50; j++) {
				//��
				if (isManMoved){
					if (isHeadIntoNorth){
						manPositionX += offset;
					}
					else{
						manPositionX -= offset;
					}
					if (j >= 15 && j <= 35) {
						manPositionY = 175;
					}
				}
				else{
					manPositionY = 125;
				}
				//��
				if (isWolfMoved) {
					if (isHeadIntoNorth) {
						
						wolfPositionX += offset;
					}
					else {
						wolfPositionX -= offset;
					}
					if (j >= 15 && j <= 35) {
						wolfPositionY = 400;
					}
				}
				else{
					wolfPositionY = 225;
				}
				//��
				if (isSheepMoved) {
					if (isHeadIntoNorth) {
						sheepPositionX += offset;
					}
					else {
						sheepPositionX -= offset;
					}
					if (j >= 15 && j <= 35) {
						sheepPositionY = 400;
					}
				}
				else{
					sheepPositionY = 325;
				}
				//��
				if (isGrassMoved) {
					if (isHeadIntoNorth) {
						grassPositionX += offset;
					}
					else {
						grassPositionX -= offset;
					}
					if (j >= 15 && j <= 35){
						grassPositionY = 400;
					}
				}
				else{
					grassPositionY = 425;
				}
				//��
				if (isHeadIntoNorth) {
					if (j >= 15 && j <= 35) {
						boatPosition += offset;
					}
				}
				else{
					if (j >= 15 && j <= 35) {
					boatPosition -= offset;
					}
				}

				update(getGraphics());

				try {
					Thread.sleep(50);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isHeadIntoNorth = !isHeadIntoNorth;
		}
	}
	//ˢ��ҳ��	
	public void update(Graphics g) {
		if (bufferImage == null)
			bufferImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics g2 = bufferImage.getGraphics();
		if (g2 == null)
			paint(g);
		else
			paint(g2);
		g2.dispose();
		g.drawImage(bufferImage, 0, 0, null);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("img/Section5/��ˮ.jpg").getImage(), 150, 0,
				300, 550, null);
		g.drawImage(new ImageIcon("img/Section5/��.png").getImage(),
				boatPosition - 50, 100, 100, 400, null);
		g.drawImage(new ImageIcon("img/Section5/�ϰ�.jpg").getImage(), 0, 0, 150,
				550, null);
		g.drawImage(new ImageIcon("img/Section5/����.jpg").getImage(), 450, 0,
				150, 550, null);

		g.drawString("��", 300, 25);
		g.drawString("�ϰ�", 50, 25);
		g.drawString("����", 500, 25);

		g.drawString("ũ��", manPositionX, manPositionY);
		g.drawString("��", wolfPositionX, wolfPositionY);
		g.drawString("��", sheepPositionX, sheepPositionY);
		g.drawString("��", grassPositionX, grassPositionY);

		g.drawImage(new ImageIcon("img/Section5/ũ��.png").getImage(),
				manPositionX - 25, manPositionY, 50, 50, null);
		g.drawImage(new ImageIcon("img/Section5/��.png").getImage(),
				wolfPositionX - 25, wolfPositionY, 50, 50, null);
		g.drawImage(new ImageIcon("img/Section5/��.png").getImage(),
				sheepPositionX - 25, sheepPositionY, 50, 50, null);
		g.drawImage(new ImageIcon("img/Section5/��.png").getImage(),
				grassPositionX - 25, grassPositionY, 50, 50, null);
	}
}