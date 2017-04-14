package Section_2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Version_3_24_Points extends JPanel {
	private JLabel jlbTitle = new JLabel("24 Points Game Version_3");
	
	private JTextField jcCard1 = new JTextField(2);
	private JTextField jcCard2 = new JTextField(2);
	private JTextField jcCard3 = new JTextField(2);
	private JTextField jcCard4 = new JTextField(2);
	
	private JPanel titlePanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel cardPanel = new JPanel();

	private JTextField jtfSolution = new JTextField(13);
	private JButton jbtFindSolution = new JButton("查找解决方案");
	
	private EvaluateExpression evaluate = new EvaluateExpression();

	public Version_3_24_Points() {
		jlbTitle.setFont(new Font("行楷",Font.PLAIN,20));
		jlbTitle.setForeground(Color.orange);
		titlePanel.add(jlbTitle);
		
		topPanel = new JPanel();
		cardPanel = new JPanel(new GridLayout(1, 4, 5, 5));

		jtfSolution.setEditable(false);
		jtfSolution.setHorizontalAlignment(SwingConstants.LEFT);
		jtfSolution.setFont(new Font("Times", Font.PLAIN, 20));

		jcCard1.setHorizontalAlignment(JTextField.CENTER);
		jcCard2.setHorizontalAlignment(JTextField.CENTER);
		jcCard3.setHorizontalAlignment(JTextField.CENTER);
		jcCard4.setHorizontalAlignment(JTextField.CENTER);

		Font font = new Font("Times", Font.PLAIN, 45);
		jcCard1.setFont(font);
		jcCard2.setFont(font);
		jcCard3.setFont(font);
		jcCard4.setFont(font);
		
		topPanel.add(jtfSolution);
		topPanel.add(jbtFindSolution);
		
		cardPanel.add(jcCard1);
		cardPanel.add(jcCard2);
		cardPanel.add(jcCard3);
		cardPanel.add(jcCard4);

		this.add(titlePanel,BorderLayout.NORTH);
		this.add(topPanel, BorderLayout.CENTER);
		this.add(cardPanel, BorderLayout.SOUTH);

		jbtFindSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int a = Integer.parseInt(jcCard1.getText().trim());
					int b = Integer.parseInt(jcCard2.getText().trim());
					int c = Integer.parseInt(jcCard3.getText().trim());
					int d = Integer.parseInt(jcCard4.getText().trim());
					jtfSolution.setText(evaluate.findSolution(a,b,c,d));
				}
				catch(NumberFormatException error){
					JOptionPane.showMessageDialog(null, "请输入1-13的数字",
							"Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g){
		g.drawImage(new ImageIcon
				("img/Section2/背景.jpg").getImage(),0,0,350,230,null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Version_3_24_Points());

		frame.setTitle("24_Points Card Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 210);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}