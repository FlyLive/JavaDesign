package PersonMessage;

import java.io.*;

public class Test2 {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Person[] persons = new Person[10];
		creatPerson(persons);
		printMessages(persons);
		/*try(ObjectOutputStream output = new ObjectOutputStream(new
				FileOutputStream("D:/Important/EXE/"
						+ "PersonMessage/txt/PostGraduate.dat",true));
			){
				output.writeObject(new Staff());
			}
		Student s = new Student("����","����","789543212.com","74563216");
		s.writeToFile(new File("D:/Important/EXE/"
						+ "PersonMessage/txt/Person.dat"));*/
	}
	
	public static void creatPerson(Person[] p) throws FileNotFoundException, IOException{
		for(int i = 0;i < p.length;i++){
			int n = (int)(Math.random() * 3);
			if(n == 0){
				p[i] = new Faculty
					("zj","��������ѧ","Baidu.com","74533406");
				p[i].writeToFile(new File("Faculty.dat"));
			}
			else if(n == 1){
				p[i] = new Staff
					("lzz","����������","360.com","74533450");
				p[i].writeToFile(new File("Staff.dat"));
			}
			else if(n == 2){
				p[i] = new Student
					("lf","��������ѧ�����Ķ�09-09","158.com","18325066357");
				p[i].writeToFile(new File("Student.dat"));
			}
		}
	}
	
	public static void printMessages(Person[] persons){		//����̨�����Ա��Ϣ
		for(Person o: persons){
			System.out.println(o.toString());
		}
	}
}