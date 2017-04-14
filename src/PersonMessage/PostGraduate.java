package PersonMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PostGraduate extends Student implements Serializable{
	private String teacher = "";
	private String target = "";
	
	public PostGraduate(){
		
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}

	public void writeToFile(File f) throws FileNotFoundException, IOException{
		try(ObjectOutputStream output = new ObjectOutputStream(new
			FileOutputStream(f,true));
		){
			output.writeObject(this);
		}
	}
}