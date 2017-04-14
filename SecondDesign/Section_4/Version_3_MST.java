package Section_4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.List;

import General.*;

public class Version_3_MST extends JFrame {
	private JTextField jtfVertexName = new JTextField(5);
	private JTextField jtfX = new JTextField(5);
	private JTextField jtfY = new JTextField(5);
	private JButton jbtAddVertex = new JButton("添加一个点");

	private JTextField jtfu = new JTextField(5);
	private JTextField jtfv = new JTextField(5);
	private JTextField jtfWeight = new JTextField(5);
	private JButton jbtAddEdge = new JButton("添加路径");

	private JButton jbtStartOver = new JButton("清空页面");

	private WeightedGraph<Vertex> graph = new WeightedGraph<Vertex>();
	/***/
	private GraphView view = new GraphView(graph);

	public Version_3_MST() {
		//添加点的Panel
		JPanel panel1 = new JPanel(new GridLayout(4, 2));
		panel1.add(new JLabel("名称: "));
		panel1.add(jtfVertexName);
		panel1.add(new JLabel("x-坐标: "));
		panel1.add(jtfX);
		panel1.add(new JLabel("y-坐标: "));
		panel1.add(jtfY);
		panel1.add(new JLabel());
		panel1.add(jbtAddVertex);
		panel1.setBorder(new TitledBorder("添加一个新的点"));
		//添加路径的Panel
		JPanel panel2 = new JPanel(new GridLayout(4, 2));
		panel2.add(new JLabel("名称u下标: "));
		panel2.add(jtfu);
		panel2.add(new JLabel("名称v下标: "));
		panel2.add(jtfv);
		panel2.add(new JLabel("长度: "));
		panel2.add(jtfWeight);
		panel2.add(new JLabel(""));
		panel2.add(jbtAddEdge);
		panel2.setBorder(new TitledBorder("添加一个新的路径"));
		//输入Panel
		JPanel panel4 = new JPanel(new GridLayout(1, 2));
		panel4.add(panel1);
		panel4.add(panel2);
		
		JPanel panel5 = new JPanel(new BorderLayout());
		panel5.add(panel4);
		panel5.add(jbtStartOver, BorderLayout.SOUTH);
		//GraphView   Panel
		add(new JScrollPane(view));
		add(panel5, BorderLayout.SOUTH);

		jbtAddVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = jtfVertexName.getText();
					if (graph.getSize() != Integer.parseInt(name.trim()))
						JOptionPane.showMessageDialog(
								null,
								"The next vertex to be added must be "
										+ graph.getSize());
					else {
						int x = Integer.parseInt(jtfX.getText().trim());
						int y = Integer.parseInt(jtfY.getText().trim());
	
						graph.addVertex(new Vertex(name, x, y));
						view.repaint();
					}
				}
				catch(Exception error){
					JOptionPane.showMessageDialog(null,
							"请输入正确的数字","Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtAddEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int u = Integer.parseInt(jtfu.getText().trim());
					int v = Integer.parseInt(jtfv.getText().trim());
					double weight = Double.parseDouble(jtfWeight.getText().trim());
	
					if (u < 0 || u >= graph.getSize())
						JOptionPane.showMessageDialog(null, "Vertex " + u
								+ " is not in the graph");
					else if (v < 0 || v >= graph.getSize())
						JOptionPane.showMessageDialog(null, "Vertex " + v
								+ " is not in the graph");
					else if (u == v)
						JOptionPane.showMessageDialog(null,
								"Two vertices cannot be the same");
					else {
						graph.addEdge(u, v, weight);
	
						view.setTree(graph.getMinimumSpanningTree());
						view.repaint();
					}
				}
				catch(Exception error){
					JOptionPane.showMessageDialog(null,
							"请输入正确的数字","Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtStartOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph.clear();
				view.repaint();
			}
		});
	}

	private class Vertex implements Displayable {
		private int x, y;
		private String name;

		Vertex(String name, int x, int y) {
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
			return ((Vertex) o).name.equals(this.name);
		}
	}

	private class GraphView extends JPanel {
		Graph<? extends Displayable> graph;
		List<? extends Displayable> path;
		private AbstractGraph<? extends Displayable>.Tree tree;

		public GraphView(Graph<? extends Displayable> graph,
				List<? extends Displayable> path) {
			this.graph = graph;
			this.path = path;
		}

		public GraphView(Graph<? extends Displayable> graph) {
			this.graph = graph;
		}

		public void setTree(AbstractGraph<? extends Displayable>.Tree tree) {
			this.tree = tree;
			repaint();
		}

		public void setPath(List<? extends Displayable> path) {
			this.path = path;
			repaint();
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/背景.jpg").getImage(), 0,
					0, this.getWidth(), this.getHeight(), null);
			
			//画点
			List<? extends Displayable> vertices = graph.getVertices();

			for (int i = 0; i < graph.getSize(); i++) {
				int x = vertices.get(i).getX();
				int y = vertices.get(i).getY();
				String name = vertices.get(i).getName();

				g.fillOval(x - 8, y - 8, 16, 16);
				g.drawString(name, x - 12, y - 12);
			}

			//画路径
			for (int i = 0; i < graph.getSize(); i++) {
				List<Integer> neighbors = graph.getNeighbors(i);
				for (int j = 0; j < neighbors.size(); j++) {
					int v = neighbors.get(j);
					int x1 = graph.getVertex(i).getX();
					int y1 = graph.getVertex(i).getY();
					int x2 = graph.getVertex(v).getX();
					int y2 = graph.getVertex(v).getY();

					g.drawLine(x1, y1, x2, y2);
				}
			}

			// 画值
			List<PriorityQueue<WeightedEdge>> queues = ((WeightedGraph<? extends Displayable>) graph)
					.getWeightedEdges();

			for (int i = 0; i < graph.getSize(); i++) {
				ArrayList<WeightedEdge> list = new ArrayList<WeightedEdge>(
						queues.get(i));

				for (WeightedEdge edge : list) {
					int u = edge.u;
					int v = edge.v;
					double weight = edge.weight;
					int x1 = graph.getVertex(u).getX();
					int y1 = graph.getVertex(u).getY();
					int x2 = graph.getVertex(v).getX();
					int y2 = graph.getVertex(v).getY();
					g.drawString(weight + "", (x1 + x2) / 2, (y1 + y2) / 2 - 6);
				}
			}

			// 画边
			if (tree != null) {
				g.setColor(java.awt.Color.RED);
				for (int i = 0; i < tree.getNumberOfVerticesFound(); i++) {
					if (tree.getParent(i) != -1) {
						int v = tree.getParent(i);
						int x1 = graph.getVertex(i).getX();
						int y1 = graph.getVertex(i).getY();
						int x2 = graph.getVertex(v).getX();
						int y2 = graph.getVertex(v).getY();

						g.drawLine(x1, y1, x2, y2);
					}
				}
			}

			// Display the path
			if (path != null) {
				g.setColor(Color.RED);
				for (int i = 1; i < path.size(); i++) {
					int x1 = path.get(i).getX();
					int y1 = path.get(i).getY();
					int x2 = path.get(i - 1).getX();
					int y2 = path.get(i - 1).getY();
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(600, 400);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Version_3_MST();
		frame.setTitle("Version_3_MST");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}