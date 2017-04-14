package Section_5;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import General.UnweightedGraph;

public class CrossRiverModel extends UnweightedGraph<State> {
	private List<List<State>> result;

	public CrossRiverModel(List<Edge> edges, List<State> states) {
		super(edges, states);
	}
	//返回所有路径
	public List<List<State>> getAllPath() {
		result = new ArrayList<List<State>>();
		List<State> list = new ArrayList<State>();
		//从南岸开始0为0000，北岸为16     1111
		list.add(vertices.get(0));
		
		crossRiver(list);
		return result;
	}
	//开始渡河
	private void crossRiver(List<State> list) {
		State state = list.get(list.size() - 1);
		//状态转化为十进制
		int index = state.getNumOfTheState();
		//遍历邻居
		for (int i : neighbors.get(index)) {
			if (isNoneRepeat(vertices.get(i), list)) {
				list.add(vertices.get(i));
				//找到一个解决方案
				if (isCrossed(vertices.get(i))) {
					result.add(cloneList(list));
					//删除最后一个
					list.remove(list.size() - 1);
					continue;
				}
				//递归
				crossRiver(list);
			}
		}
		list.remove(list.size() - 1);
	}
	//返回所有解决方案
	public List<List<State>> getSolution() {
		if (result == null)
			getAllPath();
		return result;
	}
	//静态初始化16种状态
	public static List<State> creatState() {
		ArrayList<State> list = new ArrayList<State>();
		for (int i = 0; i < 16; i++) {
			list.add(new State(i));
		}
		return list;
	}
	//静态初始化，将各个状态连接
	public static List<Edge> creatEdge(List<State> states) {
		List<Edge> edge = new ArrayList<Edge>();
		
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).isStateAvaliable()){
				for (int j = 0; j < 4; j++) {
					State temp = (State) states.get(i).clone();
					switch (j) {
					// 人什么都不带
					case 0:
						temp.changeStateOfFramer();
						break;
					// 人带上狼
					case 1:
						if (temp.isWolfCanbeDeliver()) {
							temp.changeStateOfFramer();
							temp.changeStateOfWolf();
						}
						break;
					// 人带上羊
					case 2:
						if (temp.isSheepCanbeDeliver()) {
							temp.changeStateOfFramer();
							temp.changeStateOfSheep();
						}
						break;
					// 人带上草
					case 3:
						if (temp.isGrassCanbeDeliver()) {
							temp.changeStateOfFramer();
							temp.changeStateOfGrass();
						}
						break;
					}
					if (temp.isStateAvaliable()
						&& temp.compareTo((State) states.get(i)) != 0){
						for (int k = 0; k < states.size(); k++){
							if (states.get(k).compareTo(temp) == 0) {
								edge.add(new Edge(i, k));
								break;
							}
						}
					}
				}
			}
		}
		return edge;
	}
	// 判断是否重复
	public boolean isNoneRepeat(State state, List<State> list) {
		for (int i = 0; i < list.size(); i++){
			if (state.compareTo(list.get(i)) == 0){
				return false;
			}
		}
		return true;
	}
	// 判断是否过河成功
	public boolean isCrossed(State state) {
		if (state.toString().compareTo("1111") == 0)
			return true;
		return false;
	}
	//复制并返回状态集合
	public static List<State> cloneList(List<State> list) {
		List<State> result = new ArrayList<State>();
		for (int i = 0; i < list.size(); i++)
			result.add((State) list.get(i).clone());
		return result;
	}

	// 输出过程
	public static void printProcess(List<State> list) {
		System.out.println("Step\t|\t\t\t\tState\t\t\t\t|");
		System.out.println("\t|\t\tSouth\t\t|\t\tNorth\t\t|");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%s\t|%s\t|%s\t|\n", i + 1, list.get(i)
					.getSouth(), list.get(i).getNorth());
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		List<State> vertices = creatState();
		List<Edge> edges = creatEdge(vertices);
		CrossRiverModel model = new CrossRiverModel(edges, vertices);
		List<List<State>> result = model.getAllPath();
		for (int i = 0; i < result.size(); i++)
			printProcess(result.get(i));
	}
}