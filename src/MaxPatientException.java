/**
 * The MaxPatientException class creates an instance of 
 * MaxPatientException Exception.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class MaxPatientException extends Exception{
	public MaxPatientException() {
		super("\nFailed to add patient! Exceeding max limit!\n");
	}
}
