import java.util.*;
/**
 * The TransplantDriver class acts as a test to test for all the other classes
 * and methods.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
import java.io.*;
import java.io.Serializable;
public class TransplantDriver{

/**
 * Acts as a test to test for all the classes and methods
 * @param args
 * @throws IOException
 * 		thrown when file is not found to initialize the TransplantGraph
 * @throws MaxPatientException
 * 		thrown when the TransplantGraph object contains more than 100 Patients
 * 		either donor or recipients
 */
	public static void main(String[] args) throws IOException,
	  MaxPatientException{
		TransplantGraph hosp = new TransplantGraph();
		try {
			FileInputStream files1 = new FileInputStream("transplant.obj");
			ObjectInputStream fin  = new ObjectInputStream(files1);        
			hosp = (TransplantGraph) fin.readObject();
			System.out.println("Loading data from transplant.obj...");
		}catch(ClassNotFoundException ex5) {
			System.out.println("hello");
		}catch(IOException ex6) {
			System.out.println("transplant.obj does not exist. Creating new "
			  + "TransplantGraph object...\nLoading data from 'donors.txt'...\n"
			  + "Loading data from 'recipients.txt'...");
			hosp = TransplantGraph.buildFromFiles("donors.txt", "recipients"
			  + ".txt");
		}
		boolean quit = false;
		Scanner stdin = new Scanner(System.in);
		String option,name,organ,blood;
		int age;
		try {
		do {
			System.out.println("\nMenu:");
			System.out.println("    (LR) - List all recipients\n    (LO) - "
			  + "List all donors\n"
			  + "    (AO) - Add new donor\n    (AR) - Add new recipient\n    "
			  + "(RO) - Remove"
			  + "donor\n    (RR) - Remove recipient\n    (SR) - Sort recipients"
			  + "\n    (SO) - Sort donors\n    (Q) - Quit");
			System.out.print("\nPlease select an option: ");
			option = stdin.nextLine().toUpperCase();
			System.out.println();
			switch(option) {
			case "LR":
				hosp.printAllRecipients();
				break;
			case "LO":
				hosp.printAllDonors();
				break;
			case "AO":
				System.out.print("Please enter the organ donor name: ");
				name = stdin.nextLine();
				System.out.print("Please enter the organs " + name +
				  " is donating: ");
				organ = stdin.next().toUpperCase();
				organ = organ.substring(0,1) + organ.substring(1).toLowerCase();
				System.out.print("Please enter the blood type of " + name 
				  + ": ");
				blood = stdin.next();
				System.out.print("Please enter the age of " + name + ": ");
				age = stdin.nextInt();
				Patient d = new Patient(hosp.donorSize(), name, age, organ, 
				  new BloodType(blood), true);
				hosp.addDonor(d);
				stdin.nextLine();
				System.out.println("\nThe organ donor with ID " + 
				  (hosp.donorSize()-1) + " was successfully added to the donor "
				  + "list!");
				break;
			case "AR":
				System.out.print("Please enter the new recipient's name: ");
				name = stdin.nextLine();
				System.out.print("Please enter the recipient's blood type: ");
				blood = stdin.next();
				System.out.print("Please enter the recipient's age: ");
				age = stdin.nextInt();
				System.out.print("Please enter the organ needed: ");
				organ = stdin.next().toUpperCase();
				organ = organ.substring(0,1) + organ.substring(1).toLowerCase();
				Patient r = new Patient(hosp.donorSize(), name, age, organ, 
				  new BloodType(blood), false);
				r.setID(hosp.recipSize());
				hosp.addRecipient(r);
				stdin.nextLine();
				System.out.println("\n" + name + " is now on the organ "
				  + "transplant waitlist!");
				break;
			case"RO":
				System.out.print("Please enter the name of the organ donor "
				  + "to remove: ");
				name = stdin.nextLine();
				hosp.removeDonor(name);
				break;
			case"RR":
				System.out.print("Please enteer the name of the recipient "
				  + "to remove: ");
				name = stdin.nextLine();
				hosp.removeRecipient(name);
				break;
			case"SR":
				boolean quitSort = false;
				do {
					System.out.println("     (I) Sort by ID\n     "
					  + "(N) Sort by Number of Donors\n     "
					  + "(B) Sort by Blood Type"
					  + "\n     (O) Sort by Organ Needed\n     "
					  + "(Q) Back to Main Menu\n");
					System.out.print("Please select an option: ");
					String choiceSort = stdin.next();
					System.out.println();
					switch(choiceSort.toUpperCase()){
					case "I":
						Collections.sort(hosp.getRecipList());
						hosp.updateConnection();
						hosp.printAllRecipients();
						System.out.println();
						break;
					case"B":
						Collections.sort(hosp.getRecipList(), new 
						  BloodTypeComparator());
						hosp.updateConnection();
						hosp.printAllRecipients();
						System.out.println();
						break;
					case"O":
						Collections.sort(hosp.getRecipList(), 
						  new OrganComparator());
						hosp.updateConnection();
						hosp.printAllRecipients();
						System.out.println();
						break;
					case"N":
						Collections.sort(hosp.getRecipList(), 
						  new NumConnectionComparator());
						hosp.updateConnection();
						hosp.printAllRecipients();
						System.out.println();
						break;
					case"Q":
						quitSort = true;
						stdin.nextLine();
						Collections.sort(hosp.getRecipList());
						hosp.updateConnection();
						System.out.println("Returning to main menu.");
						break;
					default:
						System.out.println("Enter a valid choice!");
						break;
					}
				}while(!quitSort);
				break;
			case"SO":
				boolean quitSortO = false;
				do {
					System.out.println("     (I) Sort by ID\n     "
					  + "(N) Sort by Number of Recipients\n     "
					  + "(B) Sort by Blood Type"
					  + "\n     (O) Sort by Organ Donated\n     "
					  + "(Q) Back to Main Menu\n");
					System.out.print("Please select an option: ");
					String choiceSort = stdin.next();
					System.out.println();
					switch(choiceSort.toUpperCase()){
					case "I":
						Collections.sort(hosp.getDonorList());
						hosp.updateConnection();
						hosp.printAllDonors();
						System.out.println();
						break;
					case"B":
						Collections.sort(hosp.getDonorList(), 
						  new BloodTypeComparator());
						hosp.updateConnection();
						hosp.printAllDonors();
						System.out.println();
						break;
					case"O":
						Collections.sort(hosp.getDonorList(), 
						  new OrganComparator());
						hosp.updateConnection();
						hosp.printAllDonors();
						System.out.println();
						break;
					case"N":
						Collections.sort(hosp.getDonorList(), 
						  new NumConnectionComparator());
						hosp.updateConnection();
						hosp.printAllDonors();
						System.out.println();
						break;
					case"Q":
						quitSortO = true;
						stdin.nextLine();
						Collections.sort(hosp.getDonorList());
						hosp.updateConnection();
						System.out.println("Returning to main menu.");
						break;
					default:
						System.out.println("Enter a valid choice!");
						break;
					}
				}while(!quitSortO);
				break;
			case "Q":
				quit = true;
				System.out.println("Writing data to transplant.obj...\n");
						System.out.println("Program terminating normally...\n");
						FileOutputStream files = new FileOutputStream
						  ("transplant.obj");
						ObjectOutputStream fout = new ObjectOutputStream(files);
						fout.writeObject(hosp);
						fout.close();
				break;
			default:
				System.out.println("\nEnter a valid choice!");
				break;
			}
		}while(!quit);
		stdin.close();
		}catch(MaxPatientException ex1) {
			System.out.println(ex1.getMessage());
		}
	

		
		
	}
}
