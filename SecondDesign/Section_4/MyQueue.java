package Section_4;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class MyQueue extends JFrame {
	private LinkedList<Integer> queue = new LinkedList<Integer>();
	private QueuePanel view = new QueuePanel();
	private JButton jbtInsert = new JButton("插入");
	private JButton jbtDelete = new JButton("删除");
	private JTextField jtfNumber = new JTextField(5);

	public MyQueue() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("输入一个值: "));
		panel.add(jtfNumber);
		panel.add(jbtInsert);
		panel.add(jbtDelete);

		add(view);
		add(panel, BorderLayout.SOUTH);

		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.addLast(Integer.parseInt(jtfNumber.getText()));
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
					if (queue.size() == 0) {
						JOptionPane.showMessageDialog(null, "该队列为空",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						queue.removeFirst();

						view.repaint();
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "请输入正确格式的整型数字",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public class QueuePanel extends JPanel {
		private int startingX = 20;
		private int startingY = 20;
		private int boxWidth = 30;
		private int boxHeight = 20;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/背景.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);

			if (queue.size() == 0) {
				g.drawString("queue is empty", startingX, startingY);
			} else {
				g.drawString("top", startingX, startingY);
				int x = startingX + 10;
				int y = startingY + 10;
				// 画队列
				for (int i = 0; i < queue.size(); i++) {
					g.drawRect(x, y, boxWidth, boxHeight);
					g.drawString(queue.get(i) + "", x + 10, y + 15);
					y = y + boxHeight;
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new MyQueue();
		frame.setTitle("MyQueue");
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}