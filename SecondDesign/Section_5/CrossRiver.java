package Section_5;

import javax.swing.*;

import General.AbstractGraph.Edge;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CrossRiver extends JFrame implements ActionListener {
	private JPanel optionPanel = new JPanel();;
	private CrossRiverPanel crossRiverPanel = new CrossRiverPanel();;

	private JButton jbtGetAllResult = new JButton("�ҳ��������");;
	private JButton jbtShowNext = new JButton("չʾ��һ����");;
	private JButton jbtShowPre = new JButton("չʾǰһ����");;

	private CrossRiverModel model;
	private List<List<State>> solution;
	// ��ǰ�𰸵��±�
	private int current;
	
	public CrossRiver() {
		this.setSize(600, 600);
		//��ʼ��
		List<State> states = CrossRiverModel.creatState();
		List<Edge> edges = CrossRiverModel.creatEdge(states);
		model = new CrossRiverModel(edges, states);

		optionPanel.add(jbtShowPre);
		optionPanel.add(jbtGetAllResult);
		optionPanel.add(jbtShowNext);

		setButtonAvaliable();
		this.setLayout(new BorderLayout());
		this.add(optionPanel, BorderLayout.NORTH);
		this.add(crossRiverPanel, BorderLayout.CENTER);
		// ��Ӽ�����
		jbtShowPre.addActionListener(this);
		jbtGetAllResult.addActionListener(this);
		jbtShowNext.addActionListener(this);
	}
	// ��Ӽ�����
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtGetAllResult) {
			solution = model.getSolution();
			setButtonAvaliable();
			crossRiverPanel.drawResult(solution.get(current));
		}
		if (e.getSource() == jbtShowNext) {
			current++;
			setButtonAvaliable();
			crossRiverPanel.drawResult(solution.get(current));
		}

		if (e.getSource() == jbtShowPre) {
			current--;
			setButtonAvaliable();
			crossRiverPanel.drawResult(solution.get(current));
		}
	}
	//���İ�ť״̬
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

	public static void main(String[] args) {
		CrossRiver gui = new CrossRiver();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Cross River");
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.setResizable(false);
	}
}