package Section_4;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import General.MyArrayList;

import java.awt.event.*;

public class TestMyArrayList extends JFrame {
	private MyArrayList<Integer> list = new MyArrayList<Integer>();
	private ListView view = new ListView();
	private JButton jbtSearch = new JButton("����");
	private JButton jbtInsert = new JButton("����");
	private JButton jbtDelete = new JButton("ɾ��");
	private JButton jbtTrimToSize = new JButton("�������");
	private JTextField jtfNumber = new JTextField(2);
	private JTextField jtfIndex = new JTextField(2);

	public TestMyArrayList() {
		JPanel panel = new JPanel();

		panel.add(new JLabel("������һ��ֵ: "));
		panel.add(jtfNumber);
		panel.add(new JLabel("�������±�: "));
		panel.add(jtfIndex);

		panel.add(jbtSearch);
		panel.add(jbtInsert);
		panel.add(jbtDelete);
		panel.add(jbtTrimToSize);

		add(view);
		add(panel, BorderLayout.SOUTH);

		jbtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jtfNumber.getText());
					if (!list.contains(number)) {
						JOptionPane.showMessageDialog(null, "�������в�����" + number);
					}
					view.repaint();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "��������ȷ��ʽ����������",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = Integer.parseInt(jtfIndex.getText());
					int number = Integer.parseInt(jtfNumber.getText());

					if (list.get(index - 1) != null) {
						list.add(index, number);
					} else {
						list.add(number);
						if(list.get(index - 1) != null){
							JOptionPane.showMessageDialog(null, "�����±�" + index
							+ "ǰ�����������������ڵ�ǰλ��");
						}
					}

					view.repaint();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "��������ȷ��ʽ����������",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jtfNumber.getText());
					if (!list.contains(number)) {
						JOptionPane.showMessageDialog(null, "�������в�����" + number);
					} else {
						list.remove(new Integer(Integer.parseInt(jtfNumber
								.getText())));
						view.repaint();
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "��������ȷ��ʽ����������",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbtTrimToSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.trimToSize();
				view.repaint();
			}
		});
	}

	public class ListView extends JPanel {
		private int startingX = 20;
		private int startingY = 20;
		private int boxWidth = 30;
		private int boxHeight = 20;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(new ImageIcon("img/Section4/����.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);

			int x = startingX + 10;
			int y = startingY + 10;

			g.drawString(
					"size = " + list.size() + " and capacity = "
							+ list.getCapacity(), startingX + 80, startingY);
			if (list.size() == 0) {
				g.drawString("list is empty.", startingX, startingY);
			} else {
				g.drawString("array list", startingX, startingY);

				for (int i = 0; i < list.size(); i++) {
					g.drawRect(x, y, boxWidth, boxHeight);
					g.drawString(list.get(i) + "", x + 10, y + 15);
					x = x + boxWidth;
				}
			}

			for (int i = list.size(); i < list.getCapacity(); i++) {
				g.drawRect(x, y, boxWidth, boxHeight);
				g.drawLine(x + boxWidth, y, x, y + boxHeight);
				x = x + boxWidth;
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new TestMyArrayList();
		frame.setTitle("TextMyArrayList");
		frame.setSize(600, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}