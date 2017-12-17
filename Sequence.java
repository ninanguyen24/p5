package p5;

/*Author: Nina Nguyen
Filename: Sequence.java
11/24/2017
Version 1
Project: P5
CPSC 5011
Reference: SequenceEnum.cpp, mkyong.com for hashCode() override

~Description: This class is an abstract class that implement the Inverter 
			  interface and provides abstract methods that needs to be 
			  implemented by the child classes. The class itself can't be 
			  instantiated. The class will be the parent class for child 
			  class that takes in a word from the driver with 3 or more 
			  letters and manipulated in some way. The user can guess 
			  what the modified word is. 
~Functionality: invert() is implemented from Inverter interface(), mod() is 
				abstract equals() and hashCode() allows driver to compare 
				two objects.
~Legal States: active (true) or inactive (false) for Sequence, inverted (true)
 			   or not inverted (false).
~State transition: State is active when object is created. Will be inactive 
			   after mod() or reset() is called.
			   State is "not inverted" when object is first created, becomes 
			   "active" once invert() is called.
~Overlapping of state: invert() can only be called when the word has not been
 					   modified. Meaning, the state has to be "not inverted" 
 					   and "active". 
					   mod() can be called if the inverter state is "inverted" 
					   or "not inverted", but Sequence state has to be "active"
~Dependencies: Depends on the child classes to not violate state. And dependent
 			   on the interface Inverter. Total dependence on the driver class 
 			   to give all valid inputs. Abstract methods should be overriden. 
~Anticipated Use: To be the parent class for the child classes. Sequence will 
				  provide variables to hold the original and modified strings. 
				  Child classes will have all the implemented abstract methods 
				  from Sequence.
~Data processed: A class variable that holds the original word passed in by 
				 driver class. The string will be manipulated and assigned to 
				 another class variable that holds the modified word.
~Legal/Illegal input: The constructor takes in a string of 3 letters or more. 
					  It will accept strings with less than 3 letters, has spaces, 
					  numbers, or symbols. However, results are not guaranteed if 
					  inputs does not meet requirements. 
~Error Response: There are no try/catch blocks or defensive programming to catch 
				 user errors. So program will crash if it's misused. Users are 
				 expected to follow the contract set by Sequence class. 
				 guess(string) should only take a single string as argument,
				 string should be 3 letters or more. Program might not act
				 accordingly if input is invalid.
~Design: Implements the invert() and isInvertState() from the Inverter interface. 
		 The child classes will inherit these functions as well. Two protected 
		 strings will be used, one to hold the original and one to hold the word 
		 after mod() is called. They should remain protected.
~Utility Functions: Uses util.Random to generate random numbers
~Data structure used: Arrays to separate letters in strings into array of 
			    	  characters invert(). 
~Class invariants: The length of the string will be 3 or more. classModWord and
				   wordFromUser can't be empty strings.
				   Index generated should always be a positive integer and
				   index can't equal the length of the wordFromUser at 0
				   indexing. EX,if the word is "hello", index can't be 4.

~Assumptions:
The class will accept words less than three letters, spaces, integers, or symbols
but results will not be guaranteed. This class cannot be instantiated on it's
own. Sequence is meant to be a parent class, can be used to achieve polymorphism.
Output given: Class will print out original and modified string. 
All abstract methods should be overriden by child classes.
setState() should remain private and can be accessed through reset()
*/

import java.util.Random;

public abstract class Sequence implements Inverter {
	//Description: Holds the word from user in lowercase.
	protected String wordFromUser;

	//Description: Holds the word after mod() is called.
	protected String classModWord;

	//Description: Holds the state of the object. "True" is for active, 
	//"False" is for inactive.
	protected boolean state;

	//Description: Holds the state of the inverter object. "True: is for 
	//inverted, "False" is for not inverted.
	protected boolean invertState;
	
	//Description: Holds the index used by invert()
	protected int index;

	//Description: Constructor
	//Pre: None
	//Post: Sequence state becomes "active" (true) and inverter state becomes 
	//		"not inverted" (false).
	public Sequence(String word) {
		reset(word);
	}

	//Description: Abstract method that should be implemented from the child
	//			   classes. Definition varies but should modify a word somehow.
	//Pre: None	
	//Post: None
	abstract String mod();

	//Description: This method sets the word passed in by the constructor
	//to the class variable only if the state is valid and the length 
	//of the word is three or more.
	private void setWord(String word) {
		//test for string size, state is valid, and string has no symbols
		//spaces or numbers.
		if (word.length() >= 3 && state == true  && word.matches("^[a-zA-Z0-9_]+$")
				&& (!word.matches(".*\\d.*"))){
			wordFromUser = word.toLowerCase();
		} else {
			System.out.println("INVALID INPUT. Please make sure the word is 3\n"
					+ "letters or more. No spaces, symbols or numbers.\n"
					+ "And should entered in all lower case.");
			System.out.println("Please rerun the program and try again.");
			throw new IllegalArgumentException();
		}
	}

	//Description: Resets the the state of the current object, allows user
	//			   to set a new word on the same object. 
	//Pre: State has to be not active (false), Inverter state can be
	// 	   "inverted" or true.
	//Post: State reset to active (true), Inverter state may or may not
	//	    change to "not inverted" (false)
	public void reset(String resetWord) {
		state = true;
		invertState = false;
		setWord(resetWord);
	}

	//Description: Return the state of the Sequence class
	//Pre: None
	//Post: None
	public boolean isActive() {
		return state;
	}

	//Description: Return the state of the Invert class. 
	//Pre: None
	//Post: None
	public boolean isInvertState(){
		return invertState;
	}

	//Description: Allow the user to guess what the modified word is
	//Pre: Sequence state has to be "not active" (false), inverter state may be
	//	   "inverted" or "not inverted"
	//Post: No state change
	public boolean guess (String userGuess)
	{
		if (userGuess.equalsIgnoreCase(classModWord)) {
			return true;
		}
		else {
			return false;
		}
	}

	//Description: Random index is generate in Sequence.java because Sequence
	//			   has the original word's length
	//Pre: Sequence has to be in an "active" and invert in "not inverted" state
	//Post: Will remain in an "active" state and becomes "inverted"
	public String invert(){
		if (state == true && invertState == false ) {
			Random rand = new Random();
			index = Math.abs(rand.nextInt(wordFromUser.length() - 1));
			char[] c = wordFromUser.toCharArray();

			char temp = c[index];
			c[index] = c[index + 1];
			c[index + 1] = temp;

			wordFromUser = new String(c);
			invertState = true;
		} else {
			System.out.println("The state is invalid, +"
					+ "you can't invert the word at this time.");
		}
		return wordFromUser;

	}

	//Description: Compares two objects to see if they are equal using
	//			  wordFromUser (original word) and classModWord (modified word)
	//Pre: Sequence state has to be "not active", invert state may or may not
	//	   "inverted"
	//Post: No change
	public boolean equals(Object o){
		if(o == null)
			return false;
		if (!(o instanceof Sequence))
			return false;
		Sequence s = (Sequence)o;
		return s.wordFromUser.equals(wordFromUser) 
				&& s.classModWord.equals(classModWord);
	}

	//Description: Override object's toString method to return original word
	//			   and modified word.
	//Pre: Sequence state has to be "not active", invert state may or may not
	//	   "inverted"
	//Post: None
	public String toString() {
		return "Original word: " + wordFromUser + "\nModified word: " 
				+ classModWord; }

	//Description: Override object's hashCode method
	//Pre: Sequence state has to be "not active", invert state may or may not
	//	   "inverted"
	//Post: No change
	//Code reference from 
	//		https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
	public int hashCode(){
		int result = 17;
		result = 31 * result + wordFromUser.hashCode();
		result = 31 * result + classModWord.hashCode();
		return result;
	}
	
	//Description: Return the index used to invert
	//Pre: Sequence state has to be "not active", invert in "inverted" state
	//Post: None
	public int getIndex(){
		return index;
	}
}