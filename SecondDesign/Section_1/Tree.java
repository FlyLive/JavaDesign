package Section_1;

import java.io.Serializable;

public class Tree implements Comparable<Tree>,Serializable{
	public Node root; // The root of the tree

	/** 将两棵树合成一颗新树 */
	public Tree(Tree t1, Tree t2) {
		root = new Node();
		root.left = t1.root;
		root.right = t2.root;
		root.weight = t1.root.weight + t2.root.weight;
	}

	public Tree(int weight, char element) {
		root = new Node(weight, element);
	}

	public int compareTo(Tree o) {
		if (root.weight < o.root.weight)
			return 1;
		else if (root.weight == o.root.weight)
			return 0;
		else
			return -1;
	}
}