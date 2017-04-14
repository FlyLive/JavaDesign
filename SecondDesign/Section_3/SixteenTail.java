package Section_3;

import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SixteenTail extends JFrame {
	private TailPanel tailPanel = new TailPanel();
	private JButton jbtSolve = new JButton("开始解决");
	private JButton jbtStartOver = new JButton("重新开始");
	private JPanel solutionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,
					10, 10));
	private SixteenTailModel model;
	
	private JComboBox roleName;

	public SixteenTail() {
		solutionPanel.add(tailPanel);
		add(new JScrollPane(solutionPanel), BorderLayout.CENTER);

		String[] names = {"十字规则","九宫格规则","十字对角规则"};
		roleName = new JComboBox(names);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(roleName);
		buttonPanel.add(jbtSolve);
		buttonPanel.add(jbtStartOver);
		add(buttonPanel, BorderLayout.SOUTH);

		addActionListener();
	}

	public void addActionListener() {
		jbtSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int n = roleName.getSelectedIndex();
				model = new SixteenTailModel(n);
				
				solutionPanel.removeAll();
				
				// 查询解决方案的路径
				List<Integer> list = model.getShortestPath(model
						.getIndex(tailPanel.getNode()));
				
				solutionPanel.add(tailPanel);
				if(list.size() == 1){
					JOptionPane.showMessageDialog(null, "未找到解决方案","错误", JOptionPane.ERROR_MESSAGE);
				}
				else{
					for (int i = 1; i < list.size(); i++) {
						// 根据模型生成解决步骤
						solutionPanel.add(new TailPanel(model.getNode(list
								.get(i - 1)), model.getNode(list.get(i))));
					}
					JOptionPane.showMessageDialog(null, "已找到解决方案");
				}
				solutionPanel.revalidate();
				model =null;
			}
		});

		jbtStartOver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tailPanel = new TailPanel();
				solutionPanel.removeAll();
				solutionPanel.add(tailPanel);
				model = null;
				solutionPanel.repaint();
				solutionPanel.revalidate();
			}
		});
		
		roleName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				int n = roleName.getSelectedIndex();
				if(model != null)
					model.setRoles(n);
			}
		});
	}

	private class Cell extends JLabel {
		private char c;

		public Cell(char s) {
			this.setBorder(new LineBorder(Color.black, 2));
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setFont(new Font("TimesRoman", Font.BOLD, 20));
			c = s;

			setText(s + "");

			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (c == 'H') {
						c = 'T';
						setBorder(new LineBorder(Color.red, 2));
						setText(c + "");
					} else {
						c = 'H';
						setBorder(new LineBorder(Color.black, 2));
						setText(c + "");
					}
				}
			});
		}

		public Cell(char c, boolean isChange) {
			this.setBorder(new LineBorder(Color.red, 2));
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setFont(new Font("TimesRoman", Font.BOLD, 20));
			this.c = c;
			setText(c + "");
		}
	}

	private class TailPanel extends JPanel {
		private Cell[][] cells = new Cell[4][4];

		public TailPanel() {
			this.setLayout(new GridLayout(4, 4));
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					add(cells[i][j] = new Cell('H'));
				}
			}
		}

		public TailPanel(char[] preNode, char[] currentNode) {
			this.setLayout(new GridLayout(4, 4));
			for (int i = 0; i < 16; i++) {
				if (preNode[i] != currentNode[i]) {
					add(new Cell(currentNode[i], true));
				} else {
					add(new Cell(currentNode[i]));
				}
			}
		}

		public char[] getNode() {
			char[] node = new char[16];
			int k = 0;
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					node[k] = cells[i][j].c;
					k++;
				}
			return node;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new SixteenTail();
		frame.setTitle("Sixteen Tail");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}