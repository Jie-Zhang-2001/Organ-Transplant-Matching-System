import java.util.*;
/**
 * The OrganComparator class creates an instance of
 * the OrganComparator object for sorting Patient
 * objects in according to organ
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class OrganComparator implements Comparator<Patient>{

/**
 * Compares Patient object p1 and p2 by organ needed/donated
 */
	public int compare(Patient p1, Patient p2) {
		return (p1.getOrgan().compareTo(p2.getOrgan()));
	}
}
