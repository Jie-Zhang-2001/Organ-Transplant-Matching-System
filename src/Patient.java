import java.io.Serializable;
/**
 * The Patient class creates an instance of
 * the Patient object
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */

public class Patient implements Comparable, Serializable{
	private String name;
	private String organ;
	private int age;
	private BloodType bloodType;
	private int ID;
	private boolean isDonor;
	private int numCon;

/**
 * No-arg constructor for Patient class
 * Returns an instance of the Patient object
 */
	public Patient() {
		
	}
	
/**
 * Returns an instance of Patient class with initialized values for fields
 * @param ID
 * 		the ID of the Patient object
 * @param name
 * 		the name of the Patient object
 * @param age
 * 		the age of the Patient object
 * @param organ
 * 		the organ of the Patient object
 * @param type
 * 		the blood type of the Patient object
 * @param isDonor
 * 		whether this Patient object is a donor or recipient
 */
	public Patient(int ID, String name, int age, String organ, BloodType type, 
	  boolean isDonor) {
		this.ID = ID;
		this.name = name;
		this.organ = organ;
		this.bloodType = type;
		this.age = age;
		this.isDonor = isDonor;
	}

/**
 * Returns the number of connections compatible to this Patient object
 * @return
 * 		the value of the numCon variable
 */
	public int getNum() {
		return numCon;
	}
	
/**
 * Increment the numCon variable by num if num is not 0,
 * set numCon to 0 otherwise
 * @param num
 * 		the integer value to be incremented to numCon variable	
 */
	public void setNum(int num) {
		if(num == 0) {
			numCon = 0;
		}else{
			numCon += num;
		}
	}

/**
 * Compares the ID of two Patient objects
 */
	  public int compareTo(Object o) { 
		  if(this.ID > ((Patient)o).getID()) {
			  return 1;
		  }else if(this.ID < ((Patient)o).getID()){
			  return -1;
		  }else {
			  return 0;
		  }
	  }
	 
/**
 * Sets the ID of this object to num
 * @param num
 * 		the integer value to be set to ID
 */
	public void setID(int num) {
		ID = num;
	}
	
/**
 * Returns a string representation of organ
 * @return
 * 		the string representation of organ variable
 */
	public String getOrgan() {
		return organ;
	}
	
/**
 * Returns the ID of this Patient object
 * @return
 * 		the integer value of ID variable
 */
	public int getID() {
		return ID;
	}

/**
 * Returns the blood type of this Patient object
 * @return
 * 		the BloodType object of bloodType
 */
	public BloodType getBlood() {
		return bloodType;
	}
	
/**
 * Returns the name of this Patient object
 * @return
 * 		the string representation of name variable
 */
	public String getName() {
		return this.name;
	}
	
/**
 * Returns the string representation of this Patient object in a neatly 
 * formatted table
 */
	public String toString() {
		return String.format("%-7s%-2s%-15s%-2s%6s%-2s%11s%-2s%6s%-2s", "   " 
	      + this.ID,"|", this.name,"|", this.age,"|", this.organ,"  |", this.
	      bloodType.getType(),"     |");
		
	}
}
