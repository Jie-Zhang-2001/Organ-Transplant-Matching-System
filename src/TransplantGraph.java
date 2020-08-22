import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * The TransplantGraph class creates an instance of
 * the TransplantGraph object
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 7 R09
 *
 */
public class TransplantGraph implements Serializable{
	private static final int MAX_PATIENTS = 100;
	private ArrayList<Patient> donors = new ArrayList<Patient>(MAX_PATIENTS);
	private ArrayList<Patient> recipients = new ArrayList<Patient>(MAX_PATIENTS)
	  ;
	private boolean [][] connections = new boolean[100][100];

/**
 * No-arg constructor for TransplantGraph class
 * Returns an instance of TransplantGraph object
 */
	public TransplantGraph() {
		
	}
	
/**
 * Returns the number of donors in the TransplantGraph object
 * @return
 * 		the integer value of the size of the ArrayList donors
 */
	public int donorSize() {
		return donors.size();
	}
	
/**
 * Returns the number of recipients in the TransplantGraph object
 * @return
 * 		the integer value of the size of the ArrayList recipients
 */
	public int recipSize() {
		return recipients.size();
	}

/**
 * Builds the TransplantGraph object with the files given
 * @param donorFile
 * 		the file to get the information of donors 
 * @param recipientFile
 * 		the file to get the information of recipients
 * @return
 * 		an TransplantGraph object with initialized donors and recipients
 * @throws FileNotFoundException
 * 		thrown when files specified are not found
 * @throws MaxPatientException
 * 		thrown when the number of Patients exceed the limit
 */
	public static TransplantGraph buildFromFiles(String donorFile, 
	  String recipientFile) throws FileNotFoundException, MaxPatientException {
		TransplantGraph g = new TransplantGraph();
		int ID,age;
		String name="", organ,type;
		Scanner donor = new Scanner(new File(donorFile));
		while(donor.hasNextLine()) {
			ID = Integer.parseInt(donor.next().replaceAll(",$","")); 
			name = donor.next();
			if(!name.endsWith(",")) {
				name += " " + donor.next().replaceAll(",$", "");
			}else {
				name = name.replaceAll(",$", "");
			}
			age = Integer.parseInt(donor.next().replaceAll(",$", ""));
			organ = donor.next().replaceAll(",$", "").toUpperCase();
			organ = organ.substring(0,1) + organ.substring(1).toLowerCase();
			type = donor.next().replaceAll(",$", "");
			BloodType dt = new BloodType(type);
			Patient d = new Patient(ID,name,age,organ,dt,true);
			g.addDonor(d);
		}
		Scanner recipient = new Scanner(new File(recipientFile));
		while(recipient.hasNextLine()) {
			ID = Integer.parseInt(recipient.next().replaceAll(",$","")); 
			name = recipient.next();
			if(!name.endsWith(",")) {
				name += " " + recipient.next().replaceAll(",$", "");
			}else {
				name = name.replaceAll(",$", "");
			}
			age = Integer.parseInt(recipient.next().replaceAll(",$", ""));
			organ = recipient.next().replaceAll(",$", "");
			type = recipient.next().replaceAll(",$", "");
			BloodType dt = new BloodType(type);
			Patient d = new Patient(ID,name,age,organ,dt,false);
			g.addRecipient(d);
		}
		g.updateConnection();
		return g;
	}
	
/**
 * Removes a donor from the ArrayList donors
 * @param name
 * 		the name of the donor to be removed
 */
	public void removeDonor(String name) {
		int origSize = donors.size();
		for(int i = 0; i < donors.size(); i++) {
			if(donors.get(i).getName().equals(name)) {
				donors.remove(i);
				for(int k = i; k < donors.size(); k++) {
					donors.get(k).setID(k);
				}
				updateConnection();
				break;
			}
		}
		if(donors.size()<origSize) {
			System.out.println("\n" + name + " was removed from the organ donor"
			  + " list.");
		}else {
			System.out.println("\nPatient not found in the list.");
		}
		
	}
	
/**
 * Removes a recipient from the ArrayList recipients
 * @param name
 * 		the name of the recipient to be removed
 */
	public void removeRecipient(String name) {
		int origSize = recipients.size();
		for(int i = 0; i < recipients.size(); i++) {
			if(recipients.get(i).getName().equals(name)) {
				recipients.remove(i);
				for(int k = i; k < recipients.size(); k++) {
					recipients.get(k).setID(k);
				}
				updateConnection();
				break;
			}
		}
		if(recipients.size()<origSize) {
			System.out.println("\n" + name + " was removed from the organ "
			  + "transplant waitlist.");
		}else {
			System.out.println("\nPatient not found in the list.");
		}
	}
	
/**
 * Updates the connections graph according to compatibility and 
 * Patient's organ demanded
 */
	public void updateConnection() {
		for(int c = 0; c < recipients.size(); c++) {
			for(int r = 0; r < donors.size(); r++) {
				if(BloodType.isCompatible(recipients.get(c).getBlood(), 
				  donors.get(r).getBlood())&& recipients.get(c).getOrgan().
				  equals(donors.get(r).getOrgan())) {
					connections[r][c] = true;
					recipients.get(c).setNum(1);
					donors.get(r).setNum(1);
				}else {
					connections[r][c] = false;
				}
			}
		}
	}

/**
 * Adds a recipient to the ArrayList recipients
 * @param patient
 * 		the Patient object to be added to recipients
 * @throws MaxPatientException
 * 		thrown when the ArrayList exceeds the max limit
 */
	public void addRecipient(Patient patient) throws MaxPatientException{
		if(recipients.size() >= MAX_PATIENTS) {
			throw new MaxPatientException();
		}
		recipients.add(patient);
		updateConnection();
	}
	
/**
 * Adds a donor to the ArrayList donors
 * @param patient
 * 		the Patient object to be added to donors
 * @throws MaxPatientException
 * 		thrown when the ArrayList exceeds the max limit
 */
	public void addDonor(Patient patient) throws MaxPatientException {
		if(donors.size() >= MAX_PATIENTS) {
			throw new MaxPatientException();
		}
		donors.add(patient);
		updateConnection();
	}

/**
 * Returns the patient ID of the Patient objects that are
 * compatible
 * @param c
 * 		the position of the recipient object in the ArrayList recipients
 * @return
 * 		a string representation of all the related patients
 */
	public String donorIDs(int c) {
		String donorID = "";
		for(int i = 0; i < donors.size(); i++) {
			if(connections[i][c]) {
				if(donorID.equals("")){
					donorID += i;
				}else {
					donorID += ", " + i;
				}
			}
		}
		return donorID;
	}

/**
 * Returns the recipients array list
 * @return
 * 		the recipients ArrayList
 */
	public ArrayList<Patient> getRecipList(){
		return this.recipients;
	}
	
/**
 * Returns the donors array list
 * @return
 * 		the donors ArrayList
 */
	public ArrayList<Patient> getDonorList(){
		return this.donors;
	}
	
/**
 * Returns the patient ID of the Patient objects that are
 * compatible
 * @param r
 * 		the position of the donor object in the ArrayList donors
 * @return
 * 		a string representation of all the related patients
 */
	public String recipIDs(int r) {
		String recipIDs = "";
		for(int i = 0; i < recipients.size(); i++) {
			if(connections[r][i]) {
				if(recipIDs.equals("")){
					recipIDs += i;
				}else {
					recipIDs += ", " + i;
				}
			}
		}
		return recipIDs;
	}
	
/**
 * Prints all the Patient objects in the recipients ArrayList in a neatly
 * formatted table
 */
	public void printAllRecipients() {
		System.out.println(String.format("%-7s%-2s%-15s%-2s%6s%-2s%11s%-2s%6s%"
		  + "-2s%-10s","Index","|", "Recipient Name","|", "Age","|", 
		  "Organ Needed ","|", "Blood Type","|", "Donor ID"));
		System.out.println("================================================="
		  + "=====================");
		for(int i = 0; i < recipients.size(); i++) {
			System.out.println(recipients.get(i).toString() + donorIDs(i));
		}
	}
	
/**
 * Prints all the Patient objects in the donors ArrayList in a neatly
 * formatted table
 */
	public void printAllDonors() {
		System.out.println(String.format("%-7s%-2s%-15s%-2s%6s%-2s%11s%-2s%6s"
		  + "%-2s%-10s","Index","|", "Donor Name","|", "Age","|", 
		  "Organ Donated","|", "Blood Type", "|", "Recipient ID"));
		System.out.println("================================================"
		  + "==========================");
		for(int i = 0; i < donors.size(); i++) {
			System.out.println(donors.get(i).toString() + recipIDs(i));
		}
	}
}
