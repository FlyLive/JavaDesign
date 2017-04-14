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
	//��������·��
	public List<List<State>> getAllPath() {
		result = new ArrayList<List<State>>();
		List<State> list = new ArrayList<State>();
		//���ϰ���ʼ0Ϊ0000������Ϊ16     1111
		list.add(vertices.get(0));
		
		crossRiver(list);
		return result;
	}
	//��ʼ�ɺ�
	private void crossRiver(List<State> list) {
		State state = list.get(list.size() - 1);
		//״̬ת��Ϊʮ����
		int index = state.getNumOfTheState();
		//�����ھ�
		for (int i : neighbors.get(index)) {
			if (isNoneRepeat(vertices.get(i), list)) {
				list.add(vertices.get(i));
				//�ҵ�һ���������
				if (isCrossed(vertices.get(i))) {
					result.add(cloneList(list));
					//ɾ�����һ��
					list.remove(list.size() - 1);
					continue;
				}
				//�ݹ�
				crossRiver(list);
			}
		}
		list.remove(list.size() - 1);
	}
	//�������н������
	public List<List<State>> getSolution() {
		if (result == null)
			getAllPath();
		return result;
	}
	//��̬��ʼ��16��״̬
	public static List<State> creatState() {
		ArrayList<State> list = new ArrayList<State>();
		for (int i = 0; i < 16; i++) {
			list.add(new State(i));
		}
		return list;
	}
	//��̬��ʼ����������״̬����
	public static List<Edge> creatEdge(List<State> states) {
		List<Edge> edge = new ArrayList<Edge>();
		
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).isStateAvaliable()){
				for (int j = 0; j < 4; j++) {
					State temp = (State) states.get(i).clone();
					switch (j) {
					// ��ʲô������
					case 0:
						temp.changeStateOfFramer();
						break;
					// �˴�����
					case 1:
						if (temp.isWolfCanbeDeliver()) {
							temp.changeStateOfFramer();
							temp.changeStateOfWolf();
						}
						break;
					// �˴�����
					case 2:
						if (temp.isSheepCanbeDeliver()) {
							temp.changeStateOfFramer();
							temp.changeStateOfSheep();
						}
						break;
					// �˴��ϲ�
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
	// �ж��Ƿ��ظ�
	public boolean isNoneRepeat(State state, List<State> list) {
		for (int i = 0; i < list.size(); i++){
			if (state.compareTo(list.get(i)) == 0){
				return false;
			}
		}
		return true;
	}
	// �ж��Ƿ���ӳɹ�
	public boolean isCrossed(State state) {
		if (state.toString().compareTo("1111") == 0)
			return true;
		return false;
	}
	//���Ʋ�����״̬����
	public static List<State> cloneList(List<State> list) {
		List<State> result = new ArrayList<State>();
		for (int i = 0; i < list.size(); i++)
			result.add((State) list.get(i).clone());
		return result;
	}

	// �������
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