package Section_4;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;

public class MyStack extends JFrame {
	private Stack<Integer> stack = new Stack<Integer>();
	private StackPanel view = new StackPanel();
	private JButton jbtInsert = new JButton("插入");
	private JButton jbtDelete = new JButton("删除");
	private JTextField jtfNumber = new JTextField(5);

	public MyStack() {
		JPanel panel = new JPanel();

		panel.add(new JLabel("请输入一个值: "));
		panel.add(jtfNumber);
		panel.add(jbtInsert);
		panel.add(jbtDelete);

		add(view);
		add(panel, BorderLayout.SOUTH);

		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stack.push(Integer.parseInt(jtfNumber.getText()));
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
					stack.pop();
					view.repaint();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "请输入正确格式的整型数字",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public class StackPanel extends JPanel {
		private int startingX = 20;
		private int startingY = 20;
		private int boxWidth = 30;
		private int boxHeight = 20;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/背景.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);

			if (stack.size() == 0) {
				g.drawString("stack is empty", startingX, startingY);
			} else {
				g.drawString("top", startingX, startingY);
				int x = startingX + 10;
				int y = startingY + 10;
				ArrayList<Integer> list = new ArrayList<Integer>(stack);

				for (int i = list.size() - 1; i >= 0; i--) {
					g.drawRect(x, y, boxWidth, boxHeight);
					g.drawString(list.get(i) + "", x + 10, y + 15);
					y = y + boxHeight;
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new MyStack();
		frame.setTitle("MyStack");
		frame.setSize(350, 200);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}