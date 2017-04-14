package Section_6;

import java.util.*;

public class MazeModel {
	private int[][] mazeArray;
	private int[] startPoint;
	private int[] endPoint;

	public MazeModel() {
	}

	public MazeModel(int[][] maze) {
		mazeArray = maze;
	}

	public int[][] getMaze() {
		return mazeArray;
	}

	public void setMaze(int[][] maze) {
		mazeArray = maze;
		setStartPoint(0, 0);
		setEndPoint(mazeArray.length - 1, mazeArray[0].length - 1);
	}
	//设置起点
	public boolean setStartPoint(int row, int col) {
		if (mazeArray[row][col] == 1)
			return false;
		startPoint = new int[2];
		startPoint[0] = row;
		startPoint[1] = col;
		return true;
	}
	//返回起点
	public int[] getStartPoint() {
		return startPoint;
	}
	//设置终点
	public boolean setEndPoint(int row, int col) {
		if (mazeArray[row][col] == 1)
			return false;
		endPoint = new int[2];
		endPoint[0] = row;
		endPoint[1] = col;
		return true;
	}
	//返回终点
	public int[] getEndPoint() {
		return endPoint;
	}

	/** 0 1 2 3 分别代表上下左右
	 * 获取一条路径,回溯法，当下一步的走法为4时回溯
	 * 获取所有路径
	 * */
	public ArrayList<StackOfLinkedList<ResultNode>> getAllPath() {
		ArrayList<StackOfLinkedList<ResultNode>> result = new ArrayList<StackOfLinkedList<ResultNode>>();
		
		StackOfLinkedList<ResultNode> stack = new StackOfLinkedList<ResultNode>();
		
		ResultNode start = new ResultNode(startPoint[0], startPoint[1]);
		stack.push(start);
		
		getAllPath(result, stack, start);
		return result;
	}
	
	private void getAllPath(ArrayList<StackOfLinkedList<ResultNode>> result,
			StackOfLinkedList<ResultNode> stack, ResultNode current) {
		//ResultNode.next等于四的时候表示一个点全部走过
		while (current.getNext() <= 3) {
			//克隆一个Node，代表current的下一个路径
			ResultNode next = current.getClone();
			next.setNext(0);
			//根据current.next来判断下一步的走法
			switch (current.getNext()) {
			case 0:
				next.up();
				break;
			case 1:
				next.down();
				break;
			case 2:
				next.left();
				break;
			case 3:
				next.right();
				break;
			default:
				break;
			}
			//如果下一步能走就继续，不能走退回判断下一个方向
			if (!ResultNode.isNodeAvaliable(mazeArray, next)
					|| !stack.isElementAvaliable(next)) {
				current.setNext(current.getNext() + 1);
				continue;
			}
			stack.push(next);
			// 当有一种结果时，保存结果，进行下一次循环
			if (next.getRow() == endPoint[0] && next.getCol() == endPoint[1]) {
				result.add(stack.getClone());
				current.setNext(current.getNext() + 1);
				stack.pop();
				continue;
			}
			getAllPath(result, stack, next);
			//
			stack.pop();
			current.setNext(current.getNext() + 1);
		}
	}
}