package PersonMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Employee extends Person implements Serializable{
	private String office = "001";
	private double pay;
	private MyDate date = new MyDate();
	
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
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
		
		return super.toString() + " and pay is " + getPay() +
				" and office is " + getOffice() + " and date is " +
				date.toString();
	}
}