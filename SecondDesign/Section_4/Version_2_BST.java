package Section_4;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

import General.BinaryTree;

import java.awt.event.*;
import java.util.*;

public class Version_2_BST extends JFrame {
	private BinaryTree<Integer> tree = new BinaryTree<Integer>();

	public Version_2_BST() {
		int[] nums = { 25, 31, 27, 18, 13, 11, 10, 62, 77 };
		for (int i = 0; i < nums.length; i++) {
			tree.insert(nums[i]);
		}

		add(new BinaryTreeDeleteAnimation(tree));
	}

	private class BinaryTreeDeleteAnimation extends JPanel {
		private BinaryTree<Integer> tree;
		private JButton jbtSearch = new JButton("����");
		private JButton jbtInsert = new JButton("����");
		private JButton jbtDelete = new JButton("ɾ��");
		private JButton jbtInorderTraversal = new JButton("�������");
		private JTextField jtfKey = new JTextField(5);
		private treePanel paintTree = new treePanel();
		private Timer timer;
		protected ArrayList<BinaryTree.TreeNode<Integer>> paths;

		public BinaryTreeDeleteAnimation(BinaryTree<Integer> tree) {
			this.tree = tree;
			setUI();
		}

		private void setUI() {
			setLayout(new BorderLayout());

			add(paintTree, BorderLayout.CENTER);
			JPanel panel = new JPanel();
			panel.add(new JLabel("������һ��ֵ: "));
			panel.add(jtfKey);
			panel.add(jbtSearch);
			panel.add(jbtInsert);
			panel.add(jbtDelete);
			panel.add(jbtInorderTraversal);
			add(panel, BorderLayout.SOUTH);

			jbtSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						int key = Integer.parseInt(jtfKey.getText());
						if (!tree.search(key)) {
							JOptionPane.showMessageDialog(null, key + " ���ڸ�����");
						} else {
							paintTree.setOfHighlightesNodes.clear();
							timer = new Timer(500, new AnimationListener(null, 0));
							paths = tree.path(key);
							timer.start();
							JOptionPane.showMessageDialog(null, "���ҵ��ý��");
						}
					}
					catch(Exception error){
						JOptionPane.showMessageDialog(null,
								"��������ȷ����������","Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			jbtInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						int key = Integer.parseInt(jtfKey.getText());
						if (tree.search(key)) {
							JOptionPane.showMessageDialog(null, key
									+ " �Ѿ���������");
						} else {
							paintTree.setOfHighlightesNodes.clear();
							paths = tree.path(key);
							timer = new Timer(500, new AnimationListener(key, 1));
							timer.start();
							JOptionPane.showMessageDialog(null, "�Ѳ���ɹ�");
						}
					}
					catch(Exception error){
						JOptionPane.showMessageDialog(null,
								"��������ȷ����������","Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			jbtDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						int key = Integer.parseInt(jtfKey.getText());
						if (!tree.search(key)) {
							JOptionPane.showMessageDialog(null, key
									+ " �����������");
						} else {
							paintTree.setOfHighlightesNodes.clear();
							paths = tree.path(key);
							timer = new Timer(500, new AnimationListener(key, 2));
							timer.start();
							JOptionPane.showMessageDialog(null, "��ɾ���ý��");
						}
					}
					catch(Exception error){
						JOptionPane.showMessageDialog(null,
								"��������ȷ����������","Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			jbtInorderTraversal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tree.inorder();
					JOptionPane.showMessageDialog(null, "�ѱ���");
				}
			});
		}

		private class AnimationListener implements ActionListener {
			int mode = 0;
			Integer key = null;

			public AnimationListener(Integer key, int mode) {
				this.key = key;
				this.mode = mode;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!paths.isEmpty())
					paintTree.setOfHighlightesNodes.add(paths.remove(0));
				else {
					timer.stop();
					if (mode == 1) {
						tree.insert(key);
						paintTree.setOfHighlightesNodes.addAll(tree.path(key));
					} else if (mode == 2) {
						tree.delete(key);
					}
				}
				paintTree.repaint();
			}
		}

		private class treePanel extends JPanel {
			protected Set<BinaryTree.TreeNode<Integer>> setOfHighlightesNodes = new HashSet<BinaryTree.TreeNode<Integer>>();
			protected int radius = 20;
			protected int virticalGap = 50;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(new ImageIcon("img/Section4/����.jpg").getImage(), 0,
						0, this.getWidth(), this.getHeight(), null);

				displayTree(g, tree.getRoot(), getWidth() / 2, 30,
						getWidth() / 4);
			}

			private void displayTree(Graphics g, BinaryTree.TreeNode root,
					int x, int y, int gap) {
				if (root != null) {
						g.setFont(new Font("TimesRoman",Font.PLAIN, 20));
					if (setOfHighlightesNodes.contains(root)) {
						g.setColor(Color.BLUE);
						g.fillOval(x - radius, y - radius, 2 * radius,
								2 * radius);
						g.drawImage(new ImageIcon("img/Section4/��.png")
							.getImage(), x - radius, y - radius,
							2 * radius, 2 * radius, null);
						g.setColor(Color.BLACK);
					}
					else {
						g.drawImage(new ImageIcon("img/Section4/��.png")
								.getImage(), x - radius, y - radius,
								2 * radius, 2 * radius, null);
					}
					g.setColor(Color.RED);
					g.drawString(root.element + "", x - 10, y + 6);
					g.setColor(Color.black);

					// ����������
					if (root.left != null){
						connectLeftChild(g, x - gap, y + virticalGap, x, y);
					
					// ������
					displayTree(g, root.left, x - gap, y + virticalGap, gap / 2);
					}
					// ���Һ�������
					if (root.right != null){
						connectRightChild(g, x + gap, y + virticalGap, x, y);
					
					// ���Һ���
					displayTree(g, root.right, x + gap, y + virticalGap,
							gap / 2);
					}
				}
			}

			// ������
			private void connectLeftChild(Graphics g, int x1, int y1, int x2,
					int y2) {
				double r = Math.sqrt(virticalGap * virticalGap + (x2 - x1)
						* (x2 - x1));
				int x11 = (int) (x1 + radius * (x2 - x1) / r);
				int y11 = (int) (y1 - radius * virticalGap / r);
				int x21 = (int) (x2 - radius * (x2 - x1) / r);
				int y21 = (int) (y2 + radius * virticalGap / r);
				g.drawLine(x11, y11, x21, y21);
			}

			// ���Һ���
			private void connectRightChild(Graphics g, int x1, int y1, int x2,
					int y2) {
				double r = Math.sqrt(virticalGap * virticalGap + (x2 - x1)
						* (x2 - x1));
				int x11 = (int) (x1 - radius * (x1 - x2) / r);
				int y11 = (int) (y1 - radius * virticalGap / r);
				int x21 = (int) (x2 + radius * (x1 - x2) / r);
				int y21 = (int) (y2 + radius * virticalGap / r);
				g.drawLine(x11, y11, x21, y21);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Version_2_BST();
		frame.setTitle("Version_2_BST");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}