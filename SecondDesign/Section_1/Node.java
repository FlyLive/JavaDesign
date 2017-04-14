package Section_1;

import java.io.Serializable;

public class Node implements Serializable{
	public char element;
	public int weight;
	public Node left;
	public Node right;
	public String code = "";

	public Node() {
	}

	public Node(int weight, char element) {
		this.weight = weight;
		this.element = element;
	}
}