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
	//�������
	public boolean setStartPoint(int row, int col) {
		if (mazeArray[row][col] == 1)
			return false;
		startPoint = new int[2];
		startPoint[0] = row;
		startPoint[1] = col;
		return true;
	}
	//�������
	public int[] getStartPoint() {
		return startPoint;
	}
	//�����յ�
	public boolean setEndPoint(int row, int col) {
		if (mazeArray[row][col] == 1)
			return false;
		endPoint = new int[2];
		endPoint[0] = row;
		endPoint[1] = col;
		return true;
	}
	//�����յ�
	public int[] getEndPoint() {
		return endPoint;
	}

	/** 0 1 2 3 �ֱ������������
	 * ��ȡһ��·��,���ݷ�������һ�����߷�Ϊ4ʱ����
	 * ��ȡ����·��
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
		//ResultNode.next�����ĵ�ʱ���ʾһ����ȫ���߹�
		while (current.getNext() <= 3) {
			//��¡һ��Node������current����һ��·��
			ResultNode next = current.getClone();
			next.setNext(0);
			//����current.next���ж���һ�����߷�
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
			//�����һ�����߾ͼ������������˻��ж���һ������
			if (!ResultNode.isNodeAvaliable(mazeArray, next)
					|| !stack.isElementAvaliable(next)) {
				current.setNext(current.getNext() + 1);
				continue;
			}
			stack.push(next);
			// ����һ�ֽ��ʱ����������������һ��ѭ��
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