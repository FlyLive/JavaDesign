package Section_2;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import General.GenericStack;

public class Version_1_24_Points extends JPanel {
	private JLabel jlbTitle = new JLabel("24 Points Game Version_1");
	private JLabel jlblCard1 = new JLabel();
	private JLabel jlblCard2 = new JLabel();
	private JLabel jlblCard3 = new JLabel();
	private JLabel jlblCard4 = new JLabel();

	private JTextField jtfExpression = new JTextField(8);

	private JButton jbtRefresh = new JButton("刷新牌");
	private JButton jbtVerify = new JButton("验证");

	private ImageIcon[] cardIcons = new ImageIcon[52];

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	private EvaluateExpression evaluate = new EvaluateExpression();
	
	private ArrayList<Integer> currentCardValues = new ArrayList<Integer>();

	public Version_1_24_Points() {
		for (int i = 0; i < 52; i++){
			list.add(i);
		}
		// Load the image icons
		for (int i = 0; i < 52; i++){
			cardIcons[i] = new ImageIcon("img/Section2/card/" + (i + 1) + ".png");
		}
		//添加牌
		panel1.add(jlblCard1);
		panel1.add(jlblCard2);
		panel1.add(jlblCard3);
		panel1.add(jlblCard4);
		//添加算式
		panel2.add(jbtRefresh);
		panel2.add(new JLabel("请输入一个算式: "), BorderLayout.WEST);
		panel2.add(jtfExpression, BorderLayout.CENTER);
		panel2.add(jbtVerify, BorderLayout.EAST);

		jlbTitle.setFont(new Font("行楷",Font.PLAIN,20));
		jlbTitle.setForeground(Color.orange);
		panel3.add(jlbTitle);

		add(panel3, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		refresh();

		jbtRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		jbtVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtfExpression.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,
							"Enter an Expression","Message",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (!correctNumbers()) {
					JOptionPane
					.showMessageDialog(null,
							"The numbers in the expression"
									+ " don't \nmatch the numbers in the set ");
				} else {
					if (evaluate()) {
						JOptionPane.showMessageDialog(null, "Correct");
					} else {
						JOptionPane.showMessageDialog(null,
								"Incorrect result");
					}
				}
			}
		});
	}

	private boolean correctNumbers() {
		String[] values = jtfExpression.getText().trim().split("[()+-/* ]");
		ArrayList<Integer> valueList = new ArrayList<Integer>();

		for (int i = 0; i < values.length; i++){
			if(values[i].length() > 0){
				try{
					valueList.add(new Integer(values[i]));
				}
				catch(NumberFormatException e){
					return false;
				}
			}
		}

		Collections.sort(valueList);
		Collections.sort(currentCardValues);

		if (valueList.equals(currentCardValues)){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	protected void paintComponent(Graphics g){
		g.drawImage(new ImageIcon
				("img/Section2/背景.jpg").getImage(),0,0,350,230,null);
	}

	private boolean evaluate() {
		return evaluate.evaluateExpression(
				jtfExpression.getText().trim()) == 24;
	}
	//刷新牌
	private void refresh() {
		Collections.shuffle(list);

		int index1 = list.get(0);
		int index2 = list.get(1);
		int index3 = list.get(2);
		int index4 = list.get(3);

		currentCardValues.clear();
		currentCardValues.add(index1 % 13 + 1);
		currentCardValues.add(index2 % 13 + 1);
		currentCardValues.add(index3 % 13 + 1);
		currentCardValues.add(index4 % 13 + 1);

		jlblCard1.setIcon(cardIcons[index1]);
		jlblCard2.setIcon(cardIcons[index2]);
		jlblCard3.setIcon(cardIcons[index3]);
		jlblCard4.setIcon(cardIcons[index4]);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Version_1_24_Points());
		frame.setTitle("24_Points Card Game");
		frame.setSize(350, 230);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}