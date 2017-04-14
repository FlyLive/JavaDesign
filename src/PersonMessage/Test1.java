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
					("zj","重庆理工大学","Baidu.com","74533406");
			}
			else if(n == 1){
				p[i] = new Staff
					("lzz","重庆龙兴镇","360.com","74533450");
			}
			else if(n == 2){
				p[i] = new Student
					("lf","重庆理工大学宿舍四栋09-09","158.com","18325066357");
			}
		}
	}
	
	public static void printMessages(Person[] persons){
		for(Person o: persons){
			System.out.println(o.toString());
		}
	}
}