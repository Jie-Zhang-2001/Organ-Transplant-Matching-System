import java.util.*;
/**
 * The BloodTypeComparator class creates an instance of
 * the BloodTypeComparator object for sorting Patient
 * objects in according to blood type
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class BloodTypeComparator implements Comparator<Patient> {
	
/**
 * Compares Patient object p1 and p2 by blood type
 */
	public int compare(Patient p1, Patient p2) {
		return(p1.getBlood().getType().compareTo(p2.getBlood().getType()));
	}
}
