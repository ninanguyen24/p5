package p5;

/*Author: Nina Nguyen
Filename: SeqExtract.java
11/24/2017
Version 1
Project: P5
CPSC 5011*/

/*
~Description: This class extends the abstract Sequence class. It override the
			  abstract method mod() from Sequence to create a subsequence at
			  random indexed. A helper method is called from mod() to take in 
			  the the subsequence as a parameter and will extract the word 
			  without the subsequence string in it. 
			  Inverter state does not affect mod().
~Anticipated Use: To create subsequence string if any and extract the original
				  word without the subsequence string. 
~Data structure used: array to manipulate string
~Utility Functions: util.Random to generate random number.
~Dependencies: Relies on client program to validate legal inputs. Also relies 
 			   the parent class to remain unchanged and stable
~Design: Will inherit all public and protected variables and functionalities 
		 from Sequence. subString() will be added to SeqExtract to help the 
		 class generate a subsequence string if any.
		 State to remain consistent with parent's class.
		 mod() abstract method from Sequence will be overriden
~Legal States: "active" (true) or "not active" (false)
~State transition: mod() will change state from "active" to "not active"
~Legal/Illegal input: A single string with 3 letters or more in all lower case.
					  Numbers, symbols, and spaces will crash the program.
~Error Response: Programming by contract, driver is expected to input data
				 in the correct format.
~Assumptions: The class will accept words less than three letters, spaces, 
		      integers, or symbols but results will not be guaranteed.
~Functionality: See description and Design
~Data processed: String passed into constructor
~Assumptions: The class will accept words less than three letters, spaces, 
			  integers, or symbols but results will not be guaranteed.
~Output given: return modified word if extracted or return original word if none

*/
import java.util.Random;

public class SeqExtract extends Sequence{
	
	//Holds the random subsequence string generated by the class
	private String subsequence;

	
	//Description: Constructor, calls the super's constructor. Generate a
	//			   subsequence string and store it in class variable
	//Pre: None
	//Post: State becomes "active" and "not inverted"
	public SeqExtract(String word) {
		super(word);
		Random rand = new Random();
		int end = Math.abs(rand.nextInt()) % wordFromUser.length();
		int begin = Math.abs(rand.nextInt()) % wordFromUser.length();
		int difference = end - begin;

		int sub = (Math.abs(rand.nextInt(100)) + 1) % 2;
		if (sub == 0) { //False - no subsequence generated
			subsequence = "";
		} else {
			if (begin < end && difference >= 2) { //substring is 2 or longer
				subsequence = wordFromUser.substring(begin, end);
			} else {
				subsequence = wordFromUser.substring(0, 2);
			}
		}
	}
	
	//Description: override from parent's class
	//Pre: In an "active" state and may or may not be "inverted"
	//Post: State becomes "not active", invert state does not change
	public String mod()
	{
		if (isActive()){
			classModWord = subString(subsequence);
			state = false;
		} else {
			System.out.println("This violates the current state.");
		}
		return classModWord;
	}


	//Description: helper class, takes in a parameter and extract the
	//			   the subsequence string out of the original word and return
	//			   the new modified word.
	//Pre: is called by the mod() method.
	//Post: None
	private String subString(String sub)  
	{
		String getSub = sub;
		String temp = "";

		int subStringLength = getSub.length();
		if (!getSub.isEmpty()) {
			if (wordFromUser.contains(getSub)) {
				while (subStringLength != 0) {
					temp = wordFromUser.replace(getSub, "");
					subStringLength--;
				}
			} else {
				temp = wordFromUser;
			}
		} else {
			temp = wordFromUser;
		}
		return temp;
	}

}