package Section_4;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;

public class MyLinkedList extends JFrame {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private LinkedListPanel view = new LinkedListPanel();
	private JButton jbtSearch = new JButton("搜索");
	private JButton jbtInsert = new JButton("插入");
	private JButton jbtDelete = new JButton("删除");
	private JTextField jtfNumber = new JTextField(5);
	private JTextField jtfIndex = new JTextField(5);

	public MyLinkedList() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("输入一个值: "));
		panel.add(jtfNumber);
		panel.add(new JLabel("输入下标: "));
		panel.add(jtfIndex);
		panel.add(jbtSearch);
		panel.add(jbtInsert);
		panel.add(jbtDelete);

		add(view);
		add(panel, BorderLayout.SOUTH);

		jbtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jtfNumber.getText());
					if (!list.contains(number)) {
						JOptionPane.showMessageDialog(null, "该链表中未包含" + number);
					}
					view.repaint();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "请输入正确格式的整型数字",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (jtfIndex.getText().trim().length() > 0)
						list.add(Integer.parseInt(jtfIndex.getText()),
								Integer.parseInt(jtfNumber.getText()));
					else
						list.add(Integer.parseInt(jtfNumber.getText()));
					view.repaint();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "请输入正确格式的整型数字",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jtfNumber.getText());
					if (!list.contains(number)) {
						JOptionPane.showMessageDialog(null, "该链表中未包含" + number);
					} else {
						list.remove(new Integer(Integer.parseInt(jtfNumber
								.getText())));
						view.repaint();
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "请输入正确格式的整型数字",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public class LinkedListPanel extends JPanel {
		private int startingX = 20;
		private int startingY = 20;
		private int boxWidth = 50;
		private int boxHeight = 20;
		private int arrowLineLength = 30;
		private int hGap = 80;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/背景.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);

			if (list.size() == 0) {
				g.drawString("head: null", startingX, startingY);
				g.drawString("tail: null", startingX, startingY + 15);
			} else {
				g.drawString("head", startingX, startingY);

				int x = startingX + 30;
				int y = startingY + 20;
				drawArrowLine(startingX + 5, startingY, x, y, g);
				g.setColor(Color.BLACK);

				for (int i = 0; i < list.size(); i++) {
					g.drawRect(x, y, boxWidth, boxHeight);
					g.drawLine(x + arrowLineLength, y, x + arrowLineLength, y
							+ boxHeight);
					g.setColor(Color.RED);

					if (i < list.size() - 1)
						drawArrowLine(x + 40, y + boxHeight / 2, x + hGap, y
								+ boxHeight / 2, g);
					g.setColor(Color.BLACK);
					g.drawString(list.get(i) + "", x + 10, y + 15);
					x = x + hGap;
				}

				g.drawString("tail", x, startingY);
				drawArrowLine(x, startingY, x - hGap, y, g);
			}
		}
	}

	public static void drawArrowLine(int x1, int y1, int x2, int y2, Graphics g) {
		g.setColor(Color.red);
		g.drawLine(x1, y1, x2, y2);

		double slope = ((((double) y1) - (double) y2))
				/ (((double) x1) - (((double) x2)));

		double arctan = Math.atan(slope);

		double set45 = 1.57 / 2;

		if (x1 < x2) {
			set45 = -1.57 * 1.5;
		}

		int arrlen = 10;

		g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan + set45) * arrlen))),
				(int) (((y2)) + (Math.sin(arctan + set45) * arrlen)));

		g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan - set45) * arrlen))),
				(int) (((y2)) + (Math.sin(arctan - set45) * arrlen)));
	}

	public static void main(String[] args) {
		JFrame frame = new MyLinkedList();
		frame.setTitle("MyArrayList");
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}