package PersonMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable{
	private String name = "";
	private String address = "";
	private String phoneNumber = "";
	private String Email = "";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
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
		
		return getName() + "'s address is " +
				getAddress() + " and Email is " +
				getEmail() + " and PhoneNumber is " + getPhoneNumber();
	}
}