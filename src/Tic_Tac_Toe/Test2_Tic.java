package Tic_Tac_Toe;

import java.awt.*;
import javax.swing.*;

public class Test2_Tic {
	public static void main(String[] args){
		cell[][] c = new cell[3][3];
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(3,3,0,0));
		creatCell(f,c);
		display(c);
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public static void creatCell(JFrame f,cell[][] c){
		for(int i = 0;i < c.length;i++){
			for(int j = 0;j < c[i].length;j++){
				f.add(c[i][j] = new cell(false));
			}
		}
	}
	
	public static void display(cell[][] c){
		for(int i = 0;i < c.length;i++){
			for(int j = 0;j < c[i].length;j++){
				int n = (int)(Math.random() * 3);
				if(n == 0){
					c[i][j].setWhose(' ');
				}
				else if(n == 1){
					c[i][j].setWhose('X');
				}
				else if(n == 2){
					c[i][j].setWhose('O');
				}
			}
		}
	}
}
/*�����Զ������ Cell ��������ʾ X�� O ���߲���ʾ��
 ���������ʱ�����������ʾ���ݡ�
 ��ʾ��ʹ�� Math.random()������������ 0�� 1 ���� 2����Ӧ����ʾ X�� O ���߲���ʾ��
����һ����ܰ��� 9 ���Զ�����壬����������Ϸ���档*/