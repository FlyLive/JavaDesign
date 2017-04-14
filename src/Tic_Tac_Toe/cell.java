package Tic_Tac_Toe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


public class cell extends JPanel{			//每一个方块
	private boolean action;
	private char whose = ' ';
	
	public cell(boolean action){
		this.action = action;
		if(action == true)
			addMouseListener(new mouse());
	}
	
	private class mouse extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(Tic_Tac_Toe.getWhoseTurn() != ' ' && whose == ' '){
				setWhose(Tic_Tac_Toe.getWhoseTurn());
			}
			
			if(Tic_Tac_Toe.won(Tic_Tac_Toe.getWhoseTurn())
				&& Tic_Tac_Toe.getWhoseTurn() != ' '){
				Tic_Tac_Toe.message.setText
				(Tic_Tac_Toe.getWhoseTurn() + "'s won!The Game is "
					+ "Over,Press New Game to continue.");
				Tic_Tac_Toe.setWhoseTurn(' ');
			}
			else if(Tic_Tac_Toe.full()){
				Tic_Tac_Toe.message.setText("Draw!The Game is Over,"
					+ "Press New Game to continue.");
				Tic_Tac_Toe.setWhoseTurn(' ');
			}
			else if(Tic_Tac_Toe.getWhoseTurn() != ' '){
				Tic_Tac_Toe.setWhoseTurn(
					(Tic_Tac_Toe.getWhoseTurn() == 'X') ? 'O' : 'X');
				Tic_Tac_Toe.message.setText
				(Tic_Tac_Toe.getWhoseTurn() + "'s turn.");
			}
		}
	}
	
	public char getWhose() {
		return whose;
	}
	
	public void setWhose(char whose) {
		this.whose = whose;
		if(action)
			paintComponent(this.getGraphics());
		else{
			repaint();
		}
	}
		
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		
		switch(whose){
		case 'X':
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawLine(10,10,getWidth() - 10,getHeight() - 10);
			g.drawLine(10,getHeight() - 10,getWidth() - 10,10);
			break;
		case 'O':
			int xRadius = getWidth() - 20;
			int yRadius = getHeight() - 20;
			
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.RED);
			g.drawOval(10,10,xRadius,yRadius);
			break;
		}
	}
}