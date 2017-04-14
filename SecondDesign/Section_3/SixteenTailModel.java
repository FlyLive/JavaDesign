package Section_3;

import java.util.*;
import General.AbstractGraph;
import General.AbstractGraph.Edge;
import General.AbstractGraph.Tree;
import General.UnweightedGraph;

public class SixteenTailModel {
	// �ܵķ���2^16��
	public final static int NUMBER_OF_NODES = (int) Math.pow(2, 16);
	private int role;
	private Tree tree;

	public SixteenTailModel(int n) {
		// ������Ȩͼ�����з�ת������������
		role = n;
		List<Edge> edges = getEdges();
		UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(edges,
				NUMBER_OF_NODES);

		tree = graph.bfs((int) (Math.pow(2, 16) - 1));
	}

	// ����һ��Edge��������Ա�������״̬�ȳ�ʼ��
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

	// ���ù���
	public void setRoles(int role) {
		this.role = role;
	}

	// ��תָ��λ�õĽڵ㲢���ر���ת�ڵ���±�
	public int getFilppedNode(char[] node, int position) {
		// ��øý����±�
		int row = position / 4;
		int column = position % 4;
		// ��ת�ý���Լ���Χ��Ԫ��
		switch (role) {
		case 0:// ʮ�ֹ���
				// �ý��
			flipCell(node, row, column);
			// ��
			flipCell(node, row - 1, column);
			// ��
			flipCell(node, row + 1, column);
			// ��
			flipCell(node, row, column - 1);
			// ��
			flipCell(node, row, column + 1);
			break;
		case 1:// �Ź������
				// �ý��
			flipCell(node, row, column);
			// ��
			flipCell(node, row - 1, column);
			// ��
			flipCell(node, row + 1, column);
			// ��
			flipCell(node, row, column - 1);
			// ��
			flipCell(node, row, column + 1);
			// ����
			flipCell(node, row - 1, column - 1);
			// ����
			flipCell(node, row - 1, column + 1);
			// ����
			flipCell(node, row + 1, column + 1);
			// ����
			flipCell(node, row + 1, column - 1);
			break;
		case 2:
			// �ý��
			flipCell(node, row, column);
			// ����
			flipCell(node, row - 1, column - 1);
			// ����
			flipCell(node, row - 1, column + 1);
			// ����
			flipCell(node, row + 1, column + 1);
			// ����
			flipCell(node, row + 1, column - 1);
			break;
		}

		return getIndex(node);
	}

	// ����ָ���ڵ���±�(ʮ������)
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

	// ��תָ���к��еĽ��
	public void flipCell(char[] node, int row, int column) {

		if (row >= 0 && row <= 3 && column >= 0 && column <= 3) {
			if (node[row * 4 + column] == 'H')
				node[row * 4 + column] = 'T';
			else
				node[row * 4 + column] = 'H';
		}
	}

	// ���һ������16��H��T�ַ��Ľڵ㣨getNode��0�������ذ���16��H�Ľ�㣻getNode��65535�������ذ���9��T�Ľ�㣻��
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

	// �������·��0~2^16
	public List<Integer> getShortestPath(int nodeIndex) {
		return tree.getPath(nodeIndex);
	}
}