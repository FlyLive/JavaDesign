package Section_6;

//结果节点
public class ResultNode implements Comparable, Cloneable {
	private int row;
	private int col;
	private int next;

	private ResultNode() {
	}

	public ResultNode(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void up() {
		row--;
	}

	public void down() {
		row++;
	}

	public void left() {
		col--;
	}

	public void right() {
		col++;
	}

	// 判断节点是否在某个迷宫中可用
	public static boolean isNodeAvaliable(int[][] maze, ResultNode node) {
		if (node.row >= maze.length || node.row < 0
				|| node.col >= maze[0].length || node.col < 0)
			return false;
		if (maze[node.row][node.col] == 1)
			return false;
		return true;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int compareTo(Object arg0) {
		ResultNode temp = (ResultNode) arg0;
		if (temp.col == col && temp.row == row)
			return 0;
		return -1;
	}

	public Object clone() {
		ResultNode node = new ResultNode();
		node.col = col;
		node.row = row;
		node.next = next;
		return node;
	}
	
	public ResultNode getClone() {
		return (ResultNode) clone();
	}
}