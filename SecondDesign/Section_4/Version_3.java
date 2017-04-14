package Section_4;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import General.*;

import java.awt.event.*;
public class Version_3 extends JFrame {
	private JTextField jtfStartCity = new JTextField(15);
	private JButton jbtDisplayDFS = new JButton("深度优先遍历");
	private JButton jbtDisplayBFS = new JButton("广度优先遍历");
	private City[] vertices = {
			new City("Seattle", 75, 50),
			new City("San Francisco", 50, 210),
			new City("Los Angeles", 75, 275), 
			new City("Denver", 275, 175),
			new City("Kansas City", 400, 245),
			new City("Chicago", 450, 100),
			new City("Boston", 700, 80),
			new City("New York", 675, 120),
			new City("Atlanta", 575, 295),
			new City("Miami", 600, 400),
			new City("Dallas", 408, 325),
			new City("Houston", 450, 360)};

	// Edge array for graph in Figure 27.1
	private int[][] edges = {
			{0, 1}, {0, 3}, {0, 5},
			{1, 0}, {1, 2}, {1, 3},
			{2, 1}, {2, 3}, {2, 4}, {2, 10},
			{3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
			{4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
			{5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
			{6, 5}, {6, 7},
			{7, 4}, {7, 5}, {7, 6}, {7, 8},
			{8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
			{9, 8}, {9, 11},
			{10, 2}, {10, 4}, {10, 8}, {10, 11},
			{11, 8}, {11, 9}, {11, 10}
	};

	private Graph<City> graph1 = new UnweightedGraph<City>(edges, vertices);
	private GraphView view = new GraphView(graph1);

	public Version_3() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("开始的城市:"));
		panel.add(jtfStartCity);
		panel.add(jbtDisplayDFS);
		panel.add(jbtDisplayBFS);

		add(view);
		add(panel, BorderLayout.SOUTH);  

		jbtDisplayDFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtfStartCity.getText();
				int index = graph1.getIndex(new City(name, 0, 0));
				if (index < 0) 
					JOptionPane.showMessageDialog(null, name + " 不再该图中");
				else
					view.setTree(graph1.dfs(index));
			}
		});

		jbtDisplayBFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtfStartCity.getText();
				int index = graph1.getIndex(new City(name, 0, 0));
				if (index < 0) 
					JOptionPane.showMessageDialog(null, name + " 不在该图中");
				else
					view.setTree(graph1.bfs(index));
			}
		});
	}

	private class GraphView extends JPanel {
		private Graph<? extends Displayable> graph;
		private AbstractGraph<? extends Displayable>.Tree tree;

		public GraphView(Graph<? extends Displayable> graph, 
				AbstractGraph<? extends Displayable>.Tree tree) {
			this.graph = graph;
			this.tree = tree;
		}

		public GraphView(Graph<? extends Displayable> graph) {
			this.graph = graph;
		}

		public void setTree(AbstractGraph<? extends Displayable>.Tree tree) {
			this.tree = tree;
			repaint();
		}

		protected void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/背景.jpg").getImage(), 0,
					0, this.getWidth(), this.getHeight(), null);
			// 画点
			List<? extends Displayable> vertices = graph.getVertices();

			for (int i = 0; i < graph.getSize(); i++) {
				int x = vertices.get(i).getX();
				int y = vertices.get(i).getY();
				String name = vertices.get(i).getName();

				g.fillOval(x - 8, y - 8, 16, 16);
				g.drawString(name, x - 12, y - 12);
			}

			// 画路径
			for (int i = 0; i < graph.getSize(); i++) {
				List<Integer> edges = graph.getNeighbors(i);
				for (int j = 0; j < edges.size(); j++) {
					int v = edges.get(j);
					int x1 = graph.getVertex(i).getX();
					int y1 = graph.getVertex(i).getY();
					int x2 = graph.getVertex(v).getX();
					int y2 = graph.getVertex(v).getY();

					g.drawLine(x1, y1, x2, y2);
				}
			}

			// 将最短路径选出
			if (tree == null) return;

			g.setColor(java.awt.Color.RED);
			for (int i = 0; i < graph.getSize(); i++) {
				if (tree.getParent(i) != -1) {
					int v = tree.getParent(i);
					int x1 = graph.getVertex(i).getX();
					int y1 = graph.getVertex(i).getY();
					int x2 = graph.getVertex(v).getX();
					int y2 = graph.getVertex(v).getY();

					drawArrowLine(x2, y2, x1, y1, g);  
				}
			}
		}
	}

	public static void drawArrowLine(int x1, int y1, 
			int x2, int y2, Graphics g) {
		g.setColor(Color.red);
		g.drawLine(x1, y1, x2, y2);

		double slope = ((((double) y1) - (double) y2))
				/ (((double) x1) - (((double) x2)));

		double arctan = Math.atan(slope);


		double set45 = 1.57 / 2;

		if (x1 < x2) {
			set45 = -1.57 * 1.5;
		}

		int arrlen = 15;

		g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan + set45) * arrlen))),
				(int) (((y2)) + (Math.sin(arctan + set45) * arrlen)));

		g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan - set45) * arrlen))),
				(int) (((y2)) + (Math.sin(arctan - set45) * arrlen)));
	}

	class City implements Displayable {
		private int x, y;
		private String name;

		City(String name, int x, int y) {
			this.name = name;
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String getName() {
			return name;
		}

		public boolean equals(Object o) {
			return ((City)o).name.equals(this.name);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Version_3();
		frame.setTitle("Version_3");
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}