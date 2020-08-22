import java.io.Serializable;
/**
 * The BloodType class creates an instance of
 * the BloodType object
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class BloodType  implements Serializable{
	private String bloodType;
	
/**
 * No-arg constructor for BloodType class
 * returns an instance of BloodType object
 */
	public BloodType() {
		
	}
	
/**
 * Returns an instance of BloodType object with specified blood
 * type
 * @param type
 * 		the blood type used to initialize field bloodType
 */
	public BloodType(String type) {
		type = type.toUpperCase();
		this.bloodType = type;
	}

/**
 * Returns the string representation of field bloodType
 * @return
 * 		the string representation of field bloodType
 */
	public String getType() {
		return this.bloodType;
	}
	
/**
 * Sets the reference of field bloodType to type
 * @param type
 * 		the string to be set to field bloodType
 */
	public void setType(String type) {
		this.bloodType = type;
	}
	
/**
 * Checks to see if the blood type of two patients are compatible for
 * organ transplant	
 * @param r
 * 		the blood type of recipient
 * @param d
 * 		the blood type of donor
 * @return
 * 		the boolean value determining whether the blood types are
 * 		compatible, true if they are, false otherwise.
 */
	public static boolean isCompatible(BloodType r, BloodType d) {
		if(r.getType().equals(d.getType())) {
			return true;
		}
		if(d.getType().equals("O")) {
			return true;
		}
		if(r.getType().equals("AB")) {
			return true;
		}
		return false;
	}
}
