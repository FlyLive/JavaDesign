package Section_3;

import java.util.*;
import General.AbstractGraph;
import General.AbstractGraph.Edge;
import General.AbstractGraph.Tree;
import General.UnweightedGraph;

public class SixteenTailModel {
	// 总的方法2^16种
	public final static int NUMBER_OF_NODES = (int) Math.pow(2, 16);
	private int role;
	private Tree tree;

	public SixteenTailModel(int n) {
		// 利用无权图将所有反转可能连接起来
		role = n;
		List<Edge> edges = getEdges();
		UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(edges,
				NUMBER_OF_NODES);

		tree = graph.bfs((int) (Math.pow(2, 16) - 1));
	}

	// 返回一个Edge对象的线性表，将各个状态先初始化
	private List<Edge> getEdges() {
		List<Edge> edges = new ArrayList<Edge>();
		for (int u = 0; u < NUMBER_OF_NODES; u++) {
			for (int k = 0; k < 16; k++) {
				char[] node = getNode(u);
				if (node[k] == 'H') {
					int v = getFilppedNode(node, k);
					edges.add(new Edge(v, u));
				}
			}
		}
		return edges;
	}

	// 设置规则
	public void setRoles(int role) {
		this.role = role;
	}

	// 翻转指定位置的节点并返回被翻转节点的下标
	public int getFilppedNode(char[] node, int position) {
		// 获得该结点的下标
		int row = position / 4;
		int column = position % 4;
		// 翻转该结点以及周围的元素
		switch (role) {
		case 0:// 十字规则
				// 该结点
			flipCell(node, row, column);
			// 上
			flipCell(node, row - 1, column);
			// 下
			flipCell(node, row + 1, column);
			// 左
			flipCell(node, row, column - 1);
			// 右
			flipCell(node, row, column + 1);
			break;
		case 1:// 九宫格规则
				// 该结点
			flipCell(node, row, column);
			// 上
			flipCell(node, row - 1, column);
			// 下
			flipCell(node, row + 1, column);
			// 左
			flipCell(node, row, column - 1);
			// 右
			flipCell(node, row, column + 1);
			// 左上
			flipCell(node, row - 1, column - 1);
			// 左上
			flipCell(node, row - 1, column + 1);
			// 右上
			flipCell(node, row + 1, column + 1);
			// 右下
			flipCell(node, row + 1, column - 1);
			break;
		case 2:
			// 该结点
			flipCell(node, row, column);
			// 左上
			flipCell(node, row - 1, column - 1);
			// 左上
			flipCell(node, row - 1, column + 1);
			// 右上
			flipCell(node, row + 1, column + 1);
			// 右下
			flipCell(node, row + 1, column - 1);
			break;
		}

		return getIndex(node);
	}

	// 返回指定节点的下标(十进制数)
	public int getIndex(char[] node) {
		int result = 0;

		for (int i = 0; i < 16; i++) {
			if (node[i] == 'T')
				result = result * 2 + 1;
			else
				result = result * 2 + 0;
		}
		return result;
	}

	// 翻转指定行和列的结点
	public void flipCell(char[] node, int row, int column) {

		if (row >= 0 && row <= 3 && column >= 0 && column <= 3) {
			if (node[row * 4 + column] == 'H')
				node[row * 4 + column] = 'T';
			else
				node[row * 4 + column] = 'H';
		}
	}

	// 获得一个包含16个H和T字符的节点（getNode（0）：返回包含16个H的结点；getNode（65535）：返回包含9个T的结点；）
	public char[] getNode(int index) {
		char[] result = new char[16];

		for (int i = 0; i < 16; i++) {
			int digit = index % 2;
			if (digit == 0)
				result[15 - i] = 'H';
			else
				result[15 - i] = 'T';
			index = index / 2;
		}
		return result;
	}

	// 返回最短路径0~2^16
	public List<Integer> getShortestPath(int nodeIndex) {
		return tree.getPath(nodeIndex);
	}
}