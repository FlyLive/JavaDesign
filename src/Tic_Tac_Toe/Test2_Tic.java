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
/*创建自定义面板 Cell 类用来显示 X、 O 或者不显示。
 当绘制面板时，随机决定显示内容。
 提示：使用 Math.random()方法产生整数 0、 1 或者 2，对应于显示 X、 O 或者不显示。
创建一个框架包含 9 个自定义面板，产生井字游戏界面。*/