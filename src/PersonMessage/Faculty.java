package PersonMessage;

import java.io.*;

public class Faculty extends Employee implements Serializable{
	private String workTime = "7:30-12:00,14:00-17:30";
	private String level = "";


	public Faculty() {
		super();
	}

	public Faculty(String name,String address,String email,String phoneNumber){
		super.setName(name);
		super.setAddress(address);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
	}
	
	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
		
		return "Faculty " + super.toString() + " and workTime is " +
		getWorkTime() + " and level is " + getLevel();
	}
}