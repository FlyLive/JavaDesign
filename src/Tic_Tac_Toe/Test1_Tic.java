package Tic_Tac_Toe;

import java.util.Scanner;

public class Test1_Tic {
	public static void main(String[] args){
		char[][] c = creatGame();
		display(c);
	}
	
	public static char[][] creatGame(){				//创建九个矩阵
		char[][] c = new char[3][3];
		
		for(int i = 0;i < c.length;i++){
			for(int j = 0;j < c[i].length;j++){
				c[i][j] = ' ';
			}
		}
		
		return c;
	}
	
	public static void display(char[][] c){			//开始游戏
		Scanner input = new Scanner(System.in);
		
		char whoseTurn = 'X';
		int row,column;
		
		do{
			System.out.println("Enter a row for Player " + whoseTurn);
			row = input.nextInt();
			System.out.println("Enter a colume for Player " + whoseTurn);
			column = input.nextInt();
			
			if(c[row][column] == ' '){
				c[row][column] = whoseTurn;
				print(c);
				if(won(c,whoseTurn)){
					System.out.println(whoseTurn + " player won");
					break;
				}
				else if(isFull(c)){
					System.out.println("No winenr");
					break;
				}
					whoseTurn = (whoseTurn == 'X')?'O':'X';
			}
			else{
				System.out.println
				("This cell is already occupied.Try a different cell");
			}
		}while(true);
	}
	
	public static void print(char[][] c){				//绘制矩阵
		System.out.println("-------------");
		System.out.println
		("| " + c[0][0] + " | " + c[0][1] + " | " + c[0][2] + " |");
		System.out.println("-------------");
		System.out.println
		("| " + c[1][0] + " | " + c[1][1] + " | " + c[1][2] + " |");
		System.out.println("-------------");
		System.out.println
		("| " + c[2][0] + " | " + c[2][1] + " | " + c[2][2] + " |");
		System.out.println("-------------");
	}
	
	public static boolean isFull(char[][] c){			//是否走完
		for(int i = 0;i < c.length;i++){
			for(int j = 0;j < c[i].length;j++){
				if(c[i][j] == ' ')
					return false;
			}
		}
		return true;
	}
	
	public static boolean won(char[][] cell,char c){
		for(int i = 0;i <3;i++){
			if(cell[i][0] == c					//纵向
				&& cell[i][1] == c
				&& cell[i][2] == c){
				return true;
			}
			else if(cell[0][i] == c				//横向
				&& cell[1][i] == c
				&& cell[2][i] == c){
				return true;
			}
		}
		
		if(cell[0][0] == c						//左上右下
			&& cell[1][1] == c
			&& cell[2][2] == c){
			return true;
		}
		
		if(cell[0][2] == c						//左下右上
			&& cell[1][1] == c
			&& cell[2][0] == c){
			return true;
		}
		
		return false;
	}
}
