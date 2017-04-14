package Section_6;

import javax.swing.*;

import java.awt.*;

public class MazePanel extends JPanel {
	private MazeCell[][] mazeCell;
	private int[][] maze;
	private StackOfLinkedList<ResultNode> results;

	public int[][] getMaze() {
		return maze;
	}
	//设置迷宫
	public void setMaze(int[][] maze) {
		this.maze = maze;
		int row = maze.length;
		int col = maze[0].length;
		
		this.setLayout(new GridLayout(row, col, 0, 0));
		this.mazeCell = new MazeCell[row][col];
		this.removeAll();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mazeCell[i][j] = new MazeCell(maze[i][j]);
				this.add(mazeCell[i][j]);
				
				if (mazeCell[i][j].getValue() == 0) {
					mazeCell[i][j].setValue(-1);
				} else if (mazeCell[i][j].getValue() == 1) {
					mazeCell[i][j].setValue(4);
				}
			}
		}
		mazeCell[0][0].setValue(5);
		mazeCell[row - 1][col - 1].setValue(6);
	}
	//返回解决方案
	public StackOfLinkedList<ResultNode> getResults() {
		return results;
	}
	//设置解决方案
	public void setRusults(StackOfLinkedList<ResultNode> results) {
		this.results = results;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("img/Section6/背景.jpg").getImage(), 0, 0,
				this.getWidth(), this.getHeight(), null);
		if (maze == null)
			return;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				int value = mazeCell[i][j].getValue();
				if (value < 4)
					mazeCell[i][j].setValue(-1);
			}
		}
		if (results != null) {
			StackOfLinkedList results = (StackOfLinkedList) this.results.clone();

			StackOfLinkedList<ResultNode> result = new StackOfLinkedList<ResultNode>();

			if (results == null) {
				JOptionPane.showMessageDialog(null,
						"This maze has no solution", "Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			while (!results.isEmpty()) {
				result.push(results.pop());
			}
			result.pop();

			while (result.size() != 1) {
				ResultNode temp = result.pop();
				int r = temp.getRow();
				int c = temp.getCol();
				//一步一步运行
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				mazeCell[r][c].setValue(temp.getNext());
			}
		}
	}
}