package Section_6;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Maze extends JFrame implements ActionListener {
	private JPanel optionPanel = new JPanel();
	private MazeModel mazeModel = new MazeModel();
	private MazePanel mazePanel = new MazePanel();

	private JButton jbtGetAllResult = new JButton("找出解决方案");
	private JButton jbtShowNext = new JButton("展示下一个答案");
	private JButton jbtShowPre = new JButton("展示前一个答案");
	private JButton jbtLoadMaze = new JButton("加载迷宫");

	private ArrayList<StackOfLinkedList<ResultNode>> solution;
	// 目前答案的数目
	private int current;

	public Maze() {
		optionPanel.add(jbtShowPre);
		optionPanel.add(jbtGetAllResult);
		optionPanel.add(jbtShowNext);
		optionPanel.add(jbtLoadMaze);

		jbtGetAllResult.setEnabled(false);
		jbtShowNext.setEnabled(false);
		jbtShowPre.setEnabled(false);

		this.add(mazePanel, BorderLayout.CENTER);
		this.add(optionPanel, BorderLayout.SOUTH);

		mazePanel.setSize(400, 600);
		optionPanel.setSize(200, 600);

		jbtShowPre.addActionListener(this);
		jbtGetAllResult.addActionListener(this);
		jbtShowNext.addActionListener(this);
		jbtLoadMaze.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtGetAllResult) {
			solution = mazeModel.getAllPath();
			if (solution.size() == 0) {
				JOptionPane.showMessageDialog(null, "这个迷宫无解！");
				return;
			}
			mazePanel.setRusults(solution.get(current).getClone());
			setButtonAvaliable();
			mazePanel.repaint();
		}
		if (e.getSource() == jbtShowNext) {
			current++;
			mazePanel.setRusults(solution.get(current).getClone());
			setButtonAvaliable();
			mazePanel.repaint();
		}
		if (e.getSource() == jbtShowPre) {
			current--;
			mazePanel.setRusults(solution.get(current).getClone());
			setButtonAvaliable();
			mazePanel.repaint();
		}
		if (e.getSource() == jbtLoadMaze) {
			 JFileChooser jfc = new JFileChooser();
			
			 jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			 jfc.showDialog(new JLabel(), "选择地图模型");
			
			 File file = jfc.getSelectedFile();
			 if (file != null) {
				 if (file.isDirectory()) {
					 JOptionPane.showMessageDialog(null, "请选择文件");
				 }
				 else if (file.isFile()) {
					 setMazeByFile(file);
					 solution = null;
					 current = 0;
				 }
			 }
			this.validate();
		}
	}

	public void setButtonAvaliable() {
		if (solution == null) {
			jbtGetAllResult.setEnabled(true);
			jbtShowNext.setEnabled(false);
			jbtShowPre.setEnabled(false);
			return;
		} else
			jbtGetAllResult.setEnabled(false);
		if (current >= solution.size() - 1)
			jbtShowNext.setEnabled(false);
		else
			jbtShowNext.setEnabled(true);
		if (current == 0)
			jbtShowPre.setEnabled(false);
		else
			jbtShowPre.setEnabled(true);
	}

	public void setMazeByFile(File file) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int[][] maze = readMaze(input);
		if (maze == null)
			return;
		mazeModel.setMaze(maze);
		solution = null;
		current = 0;
		setButtonAvaliable();
		mazePanel.setMaze(maze);
	}

	public int[][] readMaze(BufferedReader input) {
		StringBuffer strs = new StringBuffer();
		int row = 0;
		int col = 0;
		String str = new String();
		int[][] mazeArray = new int[0][0];

		while (true) {
			try {
				str = input.readLine();
				if (str != null) {
					strs.append(str.trim());
					row++;
				} else {
					for (int i = 0; i < strs.length(); i++) {
						char c = strs.charAt(i);
						if (c != '0' && c != '1') {
							strs.deleteCharAt(i);
							i--;
						}
					}
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			col = strs.length() / row;
			mazeArray = new int[row][col];
			for (int i = 0, r = 0, c = 0; i < strs.length(); i++, c++) {
				mazeArray[r][c] = strs.charAt(i) - 48;
				if ((c + 1) % col == 0) {
					r++;
					c = -1;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "文件行列数异常！！");
		}
		return mazeArray;
	}

	public static void main(String[] args) {
		JFrame frame = new Maze();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Maze");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}