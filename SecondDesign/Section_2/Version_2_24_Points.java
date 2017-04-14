package Section_2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Version_2_24_Points extends JPanel {
	private ArrayList<JCard> cards = new ArrayList<JCard>();

	private JPanel topPanel = new JPanel();
	private JPanel solutionPanel = new JPanel();
	private JPanel cardPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	private JLabel jlbTitle = new JLabel("24 Points Game Version_2");
	private JLabel jlbText = new JLabel("Enter an experssion:");
	
	private JCard jcCard1 = new JCard();
	private JCard jcCard2 = new JCard();
	private JCard jcCard3 = new JCard();
	private JCard jcCard4 = new JCard();

	private JButton jbtRefresh = new JButton("刷新");
	private JButton jbtVerify = new JButton("验证");
	private JButton jbtFindSolution = new JButton("查找解决方案");

	private JTextField jtfExpression = new JTextField(10);
	private JTextField jtfSolution = new JTextField(10);
	
	private EvaluateExpression evaluate = new EvaluateExpression();

	public Version_2_24_Points() {
		//添加部件
		addElements();
		//添加事件
		addActionEvents();
	}
	//添加部件
	public void addElements(){
		for (int i = 0; i < 52; i++)
			cards.add(new JCard(new ImageIcon("img/Section2/card/" + (i + 1)
					+ ".png"), i % 13 + 1));

		jtfExpression.setHorizontalAlignment(SwingConstants.LEFT);
		jtfSolution.setHorizontalAlignment(SwingConstants.LEFT);

		jlbTitle.setFont(new Font("行楷",Font.PLAIN,20));
		jlbTitle.setForeground(Color.orange);
		
		solutionPanel.add(jbtFindSolution);
		solutionPanel.add(jtfSolution);
		solutionPanel.add(jbtRefresh);
		
		topPanel.add(jlbTitle);

		cardPanel.add(jcCard1);
		cardPanel.add(jcCard2);
		cardPanel.add(jcCard3);
		cardPanel.add(jcCard4);

		bottomPanel.add(jlbText);
		bottomPanel.add(jtfExpression);
		bottomPanel.add(jbtVerify);
		this.add(topPanel, BorderLayout.SOUTH);
		this.add(solutionPanel);
		this.add(cardPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		cardRefresh();
	}
	//添加事件
	public void addActionEvents(){
		jbtRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardRefresh();
			}
		});

		jbtVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardExpression();
			}
		});

		jbtFindSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int a = jcCard1.faceValue;
				int b = jcCard2.faceValue;
				int c = jcCard3.faceValue;
				int d = jcCard4.faceValue;
				
				jtfSolution.setText(evaluate.findSolution(a,b,c,d));
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g){
		g.drawImage(new ImageIcon
				("img/Section2/背景.jpg").getImage(),0,0,350,280,null);
	}
	/** 刷新牌 */
	private void cardRefresh() {
		jtfExpression.setText(null);
		jtfSolution.setText(null);
		//打乱牌序
		Collections.shuffle(cards);

		jcCard1.setIcon(cards.get(0).getIcon());
		jcCard1.faceValue = cards.get(0).faceValue;

		jcCard2.setIcon(cards.get(1).getIcon());
		jcCard2.faceValue = cards.get(1).faceValue;

		jcCard3.setIcon(cards.get(2).getIcon());
		jcCard3.faceValue = cards.get(2).faceValue;

		jcCard4.setIcon(cards.get(3).getIcon());
		jcCard4.faceValue = cards.get(3).faceValue;
	}
	/** 验证算式是否正确 */
	private void cardExpression() {
		ArrayList<Integer> expressionValues = new ArrayList<Integer>();

		String expression = jtfExpression.getText().trim();
		String[] numbers = expression.split("[()+-/* ]");
		//添加所有的数字
		for (String s : numbers) {
			if (s.length() > 0) {
				try{
					expressionValues.add(new Integer(s));
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "请输入正确的数学算式",
							"Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

		//验证
		if (!expressionValues.isEmpty()) {
			if (expressionValues.contains(jcCard1.faceValue))
				expressionValues.remove(expressionValues
						.indexOf(jcCard1.faceValue));

			if (expressionValues.contains(jcCard2.faceValue))
				expressionValues.remove(expressionValues
						.indexOf(jcCard2.faceValue));

			if (expressionValues.contains(jcCard3.faceValue))
				expressionValues.remove(expressionValues
						.indexOf(jcCard3.faceValue));

			if (expressionValues.contains(jcCard4.faceValue))
				expressionValues.remove(expressionValues
						.indexOf(jcCard4.faceValue));

			if (expressionValues.isEmpty()) {
				if (evaluate.evaluateExpression(expression) == 24)
					JOptionPane.showMessageDialog(null, "Correct Solution");
				else{
					JOptionPane.showMessageDialog(null, "Invalid Expression",
							"Message", JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(null, "Invalid Expression",
						"Message", JOptionPane.ERROR_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "Enter an Expression",
					"Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	public class JCard extends JLabel {
		public int faceValue;

		public JCard() {
		}

		public JCard(Icon icon, int faceValue) {
			this.setIcon(icon);
			this.faceValue = faceValue;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Version_2_24_Points());
		frame.setTitle("24_Points Card Game");
		frame.setSize(350, 280);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}