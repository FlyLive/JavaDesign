package Section_1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import General.Heap;

public class Compression extends JFrame {
	private JTextField jtfText = new JTextField(10);
	private JButton jbtReadFile = new JButton(new ImageIcon(
			"img/Section1/�ļ���.jpg"));
	private JButton jbtShowHuffmanTree = new JButton("չʾ��������");

	private JTextField jtfBits = new JTextField();
	private JButton jbtShowText = new JButton("��֤");

	private TreeView treeView = new TreeView();
	private Tree tree;

	public Compression() {
		addCompoent();

		addActionListener();
	}

	// ��Ӳ���
	public void addCompoent() {
		// ����û������ַ���
		JPanel panel = new JPanel(new FlowLayout());

		jbtReadFile.setBorderPainted(false);
		jbtReadFile.setContentAreaFilled(false);

		panel.add(jbtReadFile);
		panel.add(new JLabel("������һ���ַ���"), BorderLayout.WEST);
		panel.add(jtfText, BorderLayout.CENTER);
		panel.add(new JScrollPane(jbtShowHuffmanTree), BorderLayout.EAST);

		add(panel, BorderLayout.NORTH);
		// ����û���������ƴ�����
		JPanel panel2 = new JPanel(new BorderLayout());

		panel2.add(new JLabel("����һ�������ƴ����н�ѹ"), BorderLayout.WEST);
		panel2.add(jtfBits, BorderLayout.CENTER);
		panel2.add(new JScrollPane(jbtShowText), BorderLayout.EAST);

		add(new JScrollPane(treeView));
		add(panel2, BorderLayout.SOUTH);
	}

	// ��Ӽ�����
	public void addActionListener() {
		// ���ѡ���ļ�������
		jbtReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();

				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "ѡ��");

				File file;
				file = jfc.getSelectedFile();
				if (file != null) {
					if (file.isDirectory()) {
						JOptionPane.showMessageDialog(null, "��ѡ���ļ�");
					} else if (file.isFile()) {
						readHfmTree(file);
					}
				}
			}
		});
		// ����ַ���ת��������������
		jbtShowHuffmanTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = jtfText.getText().trim();
				int[] counts = null;
				try {
					counts = getCharacterFrequency(text);
					if (text.length() == 0) {
						JOptionPane.showMessageDialog(null, "û���ַ������ַ���Ϊ��");
					} else {
						tree = getHuffmanTree(counts);
						treeView.setTree(tree);
						writeHfmFile();
						writeTextFile(text);
						String[] codes = getCode();
						String code = encode(text, codes);
						writeCodeFile(code);
						JOptionPane.showMessageDialog(null, text + " ��Ӧ���� "
								+ code, "������", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception error) {

				}
			}
		});
		// ��Ӷ����ƴ����Ҽ�����
		jbtShowText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bits = jtfBits.getText();

				if (tree == null) {
					JOptionPane.showMessageDialog(null, "��û��������");
				} else if (bits.length() == 0) {
					JOptionPane.showMessageDialog(null, "�������������в���");
				} else {

					String text = decode(bits);

					if (text == null) {
						JOptionPane.showMessageDialog(null, "δ���ҵ�ƥ��Ĺ�����");
					} else
						JOptionPane
								.showMessageDialog(null,
										bits + " ��Ӧ���� " + text, "���ҽ��",
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
			JOptionPane.showMessageDialog(null, "д����������ļ�����", "����",
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
			JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	//д������ļ�
	public void writeCodeFile(String code) {
		//д������ַ�����ʽ�ļ�
		writeCodePrintFile(code);
		
		String[] binaryStr = getSubString(code);
		
		byte[] byteArray = new byte[binaryStr.length];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) parse(binaryStr[i]);
		}
		saveByteAndLengthIntoFile(byteArray,byteArray.length);
	}

	// ��byte�Ͷ������ַ������ȴ洢Ϊ�ļ�
	public void saveByteAndLengthIntoFile(byte[] bit, int length) {
		try {
			FileOutputStream configurationFile = new FileOutputStream(new File(
					"dat/Section1/configuration.txt"));
			configurationFile.write(length);
			// ������������ƴ�����
			File file = new File("dat/Section1/CodeFile.dat");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bit);

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fileOutputStream);
			bufferedOutputStream.write(bit);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "д������ļ�����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeCodePrintFile(String codePrint) {
		try (DataOutputStream output = new DataOutputStream(
				new FileOutputStream("dat/Section1/CodePrint.dat"));) {
			output.writeUTF(codePrint);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "д���ӡ�����ļ�����", "����",
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
			JOptionPane.showMessageDialog(null, "д���ַ��ļ�����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeTextFile(String text) {
		try (DataOutputStream output = new DataOutputStream(
				new FileOutputStream("dat/Section1/TextFile.dat"));) {
			output.writeUTF(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "д���ַ��ļ�����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public int parse(String str) {
		// 32λ Ϊ����
		if (str.charAt(0) == '1') {
			for(int i = 0 ;i< str.length();i++){
				str = str.substring(1, str.length());
			}
			return -(Integer.parseInt(str, 2) + Integer.MAX_VALUE + 1);
		}
		return Integer.parseInt(str, 2);
	}

	// ���������ַ������
	public String[] getSubString(String bString) {
		int length = bString.length(); // ����ַ����ĳ���
		String[] binaryStr = null;
		if (length % 8 != 0)
			binaryStr = new String[length / 8 + 1]; // ��ʼ���ַ����ĳ���
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

	/** ���ݹ���������ַ� */
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
				p = tree.root; // ���¿�ʼ����
			}
		}
		return result;
	}

	/** �����ַ������ɹ������� */
	public String encode(String text, String[] codes) {
		String result = "";
		for (int i = 0; i < text.length(); i++)
			result += codes[text.charAt(i)];
		return result;
	}

	/** Ͱ����ķ�ʽ���ַ�����Ϊ�ַ���洢 */
	public String[] getCode() {
		if (tree.root == null) {
			return null;
		}

		String[] codes = new String[2 * 128];
		assignCode(tree.root, codes);

		return codes;
	}

	/** �����ַ������ɹ������� */
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

	/** �����ַ�����Ƶ�ʹ���������� */
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
			heap.add(new Tree(t1, t2)); // ����С����������ϳ��������������
		}

		return heap.remove(); // ���ع�����������ջ���
	}

	/** ����ÿ���ַ����ַ����г��ֵ�Ƶ�� */
	public int[] getCharacterFrequency(String text) throws Exception {
		int[] counts = new int[256];

		for (int i = 0; i < text.length(); i++) {
			counts[(int) text.charAt(i)]++;
		}

		return counts;
	}

	/** GUI�������� */
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
			Image img = new ImageIcon("img/Section1/��.png").getImage();
			g.setFont(new Font("����", Font.PLAIN, 20));
			g.drawImage(new ImageIcon("img/Section1/����.jpg").getImage(), 0, 0,
					this.getWidth(), this.getHeight(), null);
			if (tree == null)
				return;
			if (tree.root != null) {
				displayTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4,
						img);
			}
		}

		/** ���ƹ������� */
		private void displayTree(Graphics g, Node root, int x, int y, int hGap,
				Image img) {
			// ��
			g.drawImage(img, x - radius, y - radius, 2 * radius, 2 * radius,
					null);
			g.setColor(Color.red);
			g.drawString(root.weight + "", x - 6, y + 4);
			g.setColor(Color.black);
			if (root.left == null) // �����ַ�
				g.drawString(root.element + "", x - 6, y + 34);

			if (root.left != null) {
				// ��������
				connectLeftChild(g, x - hGap, y + distance, x, y);
				// ����������
				displayTree(g, root.left, x - hGap, y + distance, hGap / 2, img);
			}

			if (root.right != null) {
				// �����Һ���
				connectRightChild(g, x + hGap, y + distance, x, y);
				// ����������
				displayTree(g, root.right, x + hGap, y + distance, hGap / 2,
						img);
			}
		}

		/** �������ߺ�0(����) */
		private void connectLeftChild(Graphics g, int x1, int y1, int x2, int y2) {
			double d = Math.sqrt(distance * distance + (x2 - x1) * (x2 - x1));

			int x11 = (int) (x1 + radius * (x2 - x1) / d);
			int y11 = (int) (y1 - radius * distance / d);

			int x21 = (int) (x2 - radius * (x2 - x1) / d);
			int y21 = (int) (y2 + radius * distance / d);

			g.drawLine(x11, y11, x21, y21);

			g.drawString("0", (x11 + x21) / 2 - 5, (y11 + y21) / 2);
		}

		/** �������ߺ�1(�Һ���) */
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
