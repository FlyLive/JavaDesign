package PersonMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student extends Person implements Serializable{
	public static final String grade = "¥Û“ª";

	public Student(){
		
	}
	
	public Student(String name,String address,String email,String phoneNumber){
		super.setName(name);
		super.setAddress(address);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
	}
	
	public static String getGrade() {
		return grade;
	}
	
	public void writeToFile(File f) throws FileNotFoundException, IOException{
		try(ObjectOutputStream output = new ObjectOutputStream(new
			FileOutputStream(f,true));
		){
			output.writeObject(this);
		}
	}

	@Override
	public String toString(){
		
		return "Student " + super.toString() + " and grade " + getGrade();
	}
}