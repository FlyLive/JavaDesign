package Section_5;

public class State implements Cloneable,Comparable<State> {
	private int stateOfFarmer;
	private int stateOfWolf;
	private int stateOfSheep;
	private int stateOfGrass;
	//��ʼ������
	public State(){
		stateOfFarmer = 0;
		stateOfWolf = 0;
		stateOfSheep = 0;
		stateOfGrass = 0;
	}
	//��ʮ��������ת���ɶ������ַ�������ʼ������״̬
	public State(int i){
		String temp = Integer.toBinaryString(i);
		if(temp.length() == 4){
			stateOfFarmer = temp.charAt(0) - '0';
			stateOfWolf = temp.charAt(1) - '0';
			stateOfSheep = temp.charAt(2) - '0';
			stateOfGrass = temp.charAt(3) - '0';
		}
		if(temp.length() == 3){
			stateOfWolf = temp.charAt(0) - '0';
			stateOfSheep = temp.charAt(1) - '0';
			stateOfGrass = temp.charAt(2) - '0';
		}
		if(temp.length() == 2){
			stateOfSheep = temp.charAt(0) - '0';
			stateOfGrass = temp.charAt(1) - '0';
		}
		if(temp.length() == 1)
			stateOfGrass = temp.charAt(0) - '0';
	}
	
	public int getStateOfFarmer() {
		return stateOfFarmer;
	}

	public int getStateOfWolf() {
		return stateOfWolf;
	}

	public int getStateOfSheep() {
		return stateOfSheep;
	}

	public int getStateOfGrass() {
		return stateOfGrass;
	}

	public void changeStateOfFramer(){
		stateOfFarmer = (stateOfFarmer == 0 ? 1 : 0);
	}
	
	public void changeStateOfWolf(){
		stateOfWolf = (stateOfWolf == 0 ? 1 : 0);
	}
	
	public void changeStateOfSheep(){
		stateOfSheep = (stateOfSheep == 0 ? 1 : 0);
	}
	
	public void changeStateOfGrass(){
		stateOfGrass = (stateOfGrass == 0 ? 1 : 0);
	}
	//����״̬�ܷ����Ҫ��
	public boolean isStateAvaliable(){
		//�ǳ���
		if(stateOfFarmer != stateOfWolf && stateOfWolf == stateOfSheep)
			return false;
		//��Բ�
		if(stateOfFarmer != stateOfSheep && stateOfSheep == stateOfGrass)
			return false;
		return true;
	}
	//����ܷ����͸���Ʒ(���Ƿ�������ͬһ������)
	public boolean isWolfCanbeDeliver(){
		return stateOfWolf == stateOfFarmer;
	}
	
	public boolean isSheepCanbeDeliver(){
		return stateOfSheep == stateOfFarmer;
	}
	
	public boolean isGrassCanbeDeliver(){
		return stateOfGrass == stateOfFarmer;
	}
	//���ص�ǰ״̬��ʮ������
	public int getNumOfTheState(){
		int result = 0;
		if(stateOfFarmer == 1)
			result += 8;
		if(stateOfWolf == 1)
			result += 4;
		if(stateOfSheep == 1)
			result += 2;
		if(stateOfGrass == 1)
			result += 1;
		return result;
	}
	//���ص�ǰ״̬�Ķ�������
	public String toString(){
		return stateOfFarmer + ""
				+ stateOfWolf + ""
				+ stateOfSheep + ""
				+ stateOfGrass;
	}
	//���ﱱ��
	public String getNorth(){
		String result = "";
		if(stateOfFarmer == 1)
			result += "Farmer ";
		if(stateOfWolf == 1)
			result += "Wolf ";
		if(stateOfSheep == 1)
			result += "Sheep ";
		if(stateOfGrass == 1)
			result += "Grass ";
		for(int i = result.length();i <= 25;i++)
			result += " ";
		return result;
	}
	//�����ϰ�
	public String getSouth(){
		String result = "";
		if(stateOfFarmer == 0)
			result += "Farmer ";
		if(stateOfWolf == 0)
			result += "Wolf ";
		if(stateOfSheep == 0)
			result += "Sheep ";
		if(stateOfGrass == 0)
			result += "Grass ";
		for(int i = result.length();i <= 25;i++)
			result += " ";
		return result;
	}
	//���Ʋ����ظ�״̬
	public Object clone(){
		State obj = new State();
		obj.stateOfFarmer = this.stateOfFarmer;
		obj.stateOfGrass = this.stateOfGrass;
		obj.stateOfSheep = this.stateOfSheep;
		obj.stateOfWolf = this.stateOfWolf;
		return obj;
	}
	//�Ƚ�����״̬�Ƿ���ͬ
	public int compareTo(State s) {
		if(this.stateOfFarmer == s.stateOfFarmer
				&& this.stateOfGrass == s.stateOfGrass
				&& this.stateOfSheep == s.stateOfSheep
				&& this.stateOfWolf == s.stateOfWolf)
			return 0;
		return -1;
	}
}