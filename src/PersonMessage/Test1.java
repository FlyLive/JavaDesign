package PersonMessage;

public class Test1 {
	public static void main(String[] args){
		Person[] persons = new Person[10];
		creatPerson(persons);
		printMessages(persons);
	}
	
	public static void creatPerson(Person[] p){
		for(int i = 0;i < p.length;i++){
			int n = (int)(Math.random() * 3);
			if(n == 0){
				p[i] = new Faculty
					("zj","��������ѧ","Baidu.com","74533406");
			}
			else if(n == 1){
				p[i] = new Staff
					("lzz","����������","360.com","74533450");
			}
			else if(n == 2){
				p[i] = new Student
					("lf","��������ѧ�����Ķ�09-09","158.com","18325066357");
			}
		}
	}
	
	public static void printMessages(Person[] persons){
		for(Person o: persons){
			System.out.println(o.toString());
		}
	}
}