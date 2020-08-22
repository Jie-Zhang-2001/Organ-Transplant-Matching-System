import java.util.*;
/**
 * The NumConnectionComparator class creates an instance of
 * the NumConnectionComparator object for sorting Patient
 * objects in according to number of connections
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class NumConnectionComparator implements Comparator<Patient>{ 

/**
 * Compares Patient object p1 and p2 by number of connections
 */
	public int compare(Patient p1, Patient p2) {
		if(p1.getNum() > p2.getNum()) {
			  return 1;
		  }else if(p1.getNum() < p2.getNum()){
			  return -1;
		  }else {
			  return 0;
		  }
	} 
}
 
