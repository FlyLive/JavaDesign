package PersonMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Staff extends Employee implements Serializable{
	private String workName = "clear";
	
	public Staff(){
		
	}
	
	public Staff(String name,String address,String email,String phoneNumber){
		super.setName(name);
		super.setAddress(address);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
	}
	
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
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
		
		return "Staff " + super.toString() + " and workName is " +
				getWorkName();
	}
}