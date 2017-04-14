package Section_1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import General.Heap;

public class Compression extends JFrame {
	private JTextField jtfText = new JTextField(10);
	private JButton jbtReadFile = new JButton(new ImageIcon(
			"img/Section1/文件夹.jpg"));
	private JButton jbtShowHuffmanTree = new JButton("展示哈夫曼树");

	private JTextField jtfBits = new JTextField();
	private JButton jbtShowText = new JButton("验证");

	private TreeView treeView = new TreeView();
	private Tree tree;

	public Compression() {
		addCompoent();

		addActionListener();
	}

	// 添加部件
	public void addCompoent() {
		// 添加用户输入字符串
		JPanel panel = new JPanel(new FlowLayout());

		jbtReadFile.setBorderPainted(false);
		jbtReadFile.setContentAreaFilled(false);

		panel.add(jbtReadFile);
		panel.add(new JLabel("请输入一个字符串"), BorderLayout.WEST);
		panel.add(jtfText, BorderLayout.CENTER);
		panel.add(new JScrollPane(jbtShowHuffmanTree), BorderLayout.EAST);

		add(panel, BorderLayout.NORTH);
		// 添加用户输入二进制串查找
		JPanel panel2 = new JPanel(new BorderLayout());

		panel2.add(new JLabel("输入一个二进制串进行解压"), BorderLayout.WEST);
		panel2.add(jtfBits, BorderLayout.CENTER);
		panel2.add(new JScrollPane(jbtShowText), BorderLayout.EAST);

		add(new JScrollPane(treeView));
		add(panel2, BorderLayout.SOUTH);
	}

	// 添加监听器
	public void addActionListener() {
		// 添加选择文件监听器
		jbtReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();

				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择");

				File file;
				file = jfc.getSelectedFile();
				if (file != null) {
					if (file.isDirectory()) {
						JOptionPane.showMessageDialog(null, "请选择文件");
					} else if (file.isFile()) {
						readHfmTree(file);
					}
				}
			}
		});
		// 添加字符串转哈夫曼树监听器
		jbtShowHuffmanTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = jtfText.getText().trim();
				int[] counts = null;
				try {
					counts = getCharacterFrequency(text);
					if (text.length() == 0) {
						JOptionPane.showMessageDialog(null, "没有字符串或字符串为空");
					} else {
						tree = getHuffmanTree(counts);
						treeView.setTree(tree);
						writeHfmFile();
						writeTextFile(text);
						String[] codes = getCode();
						String code = encode(text, codes);
						writeCodeFile(code);
						JOptionPane.showMessageDialog(null, text + " 对应的是 "
								+ code, "哈夫码", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception error) {

				}
			}
		});
		// 添加二进制串查找监听器
		jbtShowText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bits = jtfBits.getText();

				if (tree == null) {
					JOptionPane.showMessageDialog(null, "还没有生成树");
				} else if (bits.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入哈夫码进行查找");
				} else {

					String text = decode(bits);

					if (text == null) {
						JOptionPane.showMessageDialog(null, "未查找到匹配的哈夫码");
					} else
						JOptionPane
								.showMessageDialog(null,
										bits + " 对应的是 " + text, "查找结果",
										JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void writeHfmFile() {
		// Write hfmTree to the file
		try (ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream("dat/Section1/HfmTree.dat"));) {
			output.writeObject(tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "写入哈夫曼树文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void readHfmTree(File file) {
		Tree fileHfmTree;
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream(file));) {
			fileHfmTree = (Tree) (input.readObject());
			readTextFile();
			tree = fileHfmTree;
			treeView.setTree(tree);
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "读取文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	//写入代码文件
	public void writeCodeFile(String code) {
		//写入代码字符串格式文件
		writeCodePrintFile(code);
		
		String[] binaryStr = getSubString(code);
		
		byte[] byteArray = new byte[binaryStr.length];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) parse(binaryStr[i]);
		}
		saveByteAndLengthIntoFile(byteArray,byteArray.length);
	}

	// 将byte和二进制字符串长度存储为文件
	public void saveByteAndLengthIntoFile(byte[] bit, int length) {
		try {
			FileOutputStream configurationFile = new FileOutputStream(new File(
					"dat/Section1/configuration.txt"));
			configurationFile.write(length);
			// 创建输出二进制代码流
			File file = new File("dat/Section1/CodeFile.dat");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bit);

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fileOutputStream);
			bufferedOutputStream.write(bit);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "写入代码文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeCodePrintFile(String codePrint) {
		try (DataOutputStream output = new DataOutputStream(
				new FileOutputStream("dat/Section1/CodePrint.dat"));) {
			output.writeUTF(codePrint);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "写入打印代码文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void readTextFile() {
		try (DataInputStream input = new DataInputStream(new FileInputStream(
				"dat/Section1/TextFile.dat"));) {
			String text = input.readUTF();
			jtfText.setText(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "写入字符文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeTextFile(String text) {
		try (DataOutputStream output = new DataOutputStream(
				new FileOutputStream("dat/Section1/TextFile.dat"));) {
			output.writeUTF(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "写入字符文件错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public int parse(String str) {
		// 32位 为负数
		if (str.charAt(0) == '1') {
			for(int i = 0 ;i< str.length();i++){
				str = str.substring(1, str.length());
			}
			return -(Integer.parseInt(str, 2) + Integer.MAX_VALUE + 1);
		}
		return Integer.parseInt(str, 2);
	}

	// 将二进制字符串拆分
	public String[] getSubString(String bString) {
		int length = bString.length(); // 获得字符串的长度
		String[] binaryStr = null;
		if (length % 8 != 0)
			binaryStr = new String[length / 8 + 1]; // 初始化字符串的长度
		else
			binaryStr = new String[length / 8];

		String add = "";

		for (int i = 0; i < binaryStr.length; i++) {

			if ((i + 1) * 8 > bString.length()) {
				String temp = bString.substring(i * 8, bString.length()).trim();
				for (int j = 0; j < 8 - temp.length(); j++) {
					add += "0";
				}
				binaryStr[i] = add
						+ bString.substring(i * 8, bString.length()).trim();

			} else
				binaryStr[i] = bString.substring(i * 8, (i + 1) * 8).trim();
		}
		return binaryStr;
	}

	/** 根据哈弗码查找字符 */
	public String decode(String bits) {
		String result = "";

		Node p = tree.root;
		for (int i = 0; i < bits.length(); i++) {
			if (bits.charAt(i) == '0')
				p = p.left;
			else if (bits.charAt(i) == '1')
				p = p.right;
			else
				return null;

			if (p.left == null) {
				result += p.element + " ";
				p = tree.root; // 重新开始查找
			}
		}
		return result;
	}

	/** 根据字符串生成哈夫曼码 */
	public String encode(String text, String[] codes) {
		String result = "";
		for (int i = 0; i < text.length(); i++)
			result += codes[text.charAt(i)];
		return result;
	}

	/** 桶排序的方式将字符串分为字符组存储 */
	public String[] getCode() {
		if (tree.root == null) {
			return null;
		}

		String[] codes = new String[2 * 128];
		assignCode(tree.root, codes);

		return codes;
	}

	/** 根据字符串生成哈夫曼码 */
	private void assignCode(Node root, String[] codes) {
		if (root.left != null) {
			root.left.code = root.code + "0";
			assignCode(root.left, codes);

			root.right.code = root.code + "1";
			assignCode(root.right, codes);
		} else {
			codes[(int) root.element] = root.code + " ";
		}
	}

	/** 根据字符串和频率构造哈夫曼树 */
	public Tree getHuffmanTree(int[] counts) {
		Heap<Tree> heap = new Heap<Tree>();

		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				heap.add(new Tree(counts[i], (char) i));
			}
		}

		while (heap.getSize() > 1) {
			Tree t1 = heap.remove();
			Tree t2 = heap.remove();
			heap.add(new Tree(t1, t2)); // 将最小的两棵树结合成新树并加入堆中
		}

		return heap.remove(); // 返回哈夫曼树并清空缓存
	}

	/** 返回每个字符在字符串中出现的频率 */
	public int[] getCharacterFrequency(String text) throws Exception {
		int[] counts = new int[256];

		for (int i = 0; i < text.length(); i++) {
			counts[(int) text.charAt(i)]++;
		}

		return counts;
	}

	/** GUI绘制树形 */
	class TreeView extends JPanel {
		private int radius = 20;
		private int distance = 50;
		Tree tree;

		public TreeView() {
		}

		public TreeView(Tree tree) {
			this.tree = tree;
		}

		public void setTree(Tree tree) {
			this.tree = tree;
			repaint();
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image img = new ImageIcon("img/Section1/树.png").getImage();
			g.setFont(new Font("楷体", Font.PLAIN, 20));
			g.drawImage(new ImageIcon("img/Section1/背景.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);
			if (tree == null)
				return;
			if (tree.root != null) {
				displayTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4,
						img);
			}
		}

		/** 绘制哈夫曼树 */
		private void displayTree(Graphics g, Node root, int x, int y, int hGap,
				Image img) {
			// 树
			g.drawImage(img, x - radius, y - radius, 2 * radius, 2 * radius,
					null);
			g.setColor(Color.red);
			g.drawString(root.weight + "", x - 6, y + 4);
			g.setColor(Color.black);
			if (root.left == null) // 绘制字符
				g.drawString(root.element + "", x - 6, y + 34);

			if (root.left != null) {
				// 连接左孩子
				connectLeftChild(g, x - hGap, y + distance, x, y);
				// 绘制左子树
				displayTree(g, root.left, x - hGap, y + distance, hGap / 2, img);
			}

			if (root.right != null) {
				// 连接右孩子
				connectRightChild(g, x + hGap, y + distance, x, y);
				// 绘制右子树
				displayTree(g, root.right, x + hGap, y + distance, hGap / 2,
						img);
			}
		}

		/** 绘制连线和0(左孩子) */
		private void connectLeftChild(Graphics g, int x1, int y1, int x2, int y2) {
			double d = Math.sqrt(distance * distance + (x2 - x1) * (x2 - x1));

			int x11 = (int) (x1 + radius * (x2 - x1) / d);
			int y11 = (int) (y1 - radius * distance / d);

			int x21 = (int) (x2 - radius * (x2 - x1) / d);
			int y21 = (int) (y2 + radius * distance / d);

			g.drawLine(x11, y11, x21, y21);

			g.drawString("0", (x11 + x21) / 2 - 5, (y11 + y21) / 2);
		}

		/** 绘制连线和1(右孩子) */
		private void connectRightChild(Graphics g, int x1, int y1, int x2,
				int y2) {
			double d = Math.sqrt(distance * distance + (x2 - x1) * (x2 - x1));

			int x11 = (int) (x1 - radius * (x1 - x2) / d);
			int y11 = (int) (y1 - radius * distance / d);

			int x21 = (int) (x2 + radius * (x1 - x2) / d);
			int y21 = (int) (y2 + radius * distance / d);

			g.drawLine(x11, y11, x21, y21);

			g.drawString("1", (x11 + x21) / 2 + 5, (y11 + y21) / 2);
		}

		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Compression();
		frame.setTitle("Compression");
		frame.setIconImage(new ImageIcon("").getImage());
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
