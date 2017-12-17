package p5; 

/*Author: Nina Nguyen
Filename: P5.java
11/24/2017
Version 1
Project: P5
CPSC 5011

~Description: This program will use 7 words and 7 guessed words to test seven
			  Sequence type objects. 3 SequenceEnum, 2 SpasEnum, 2 SeqExtract.
~Data Structure used: Arrays to hold the list of test words, guessed words,
					  and Sequence type objects. 
~Functionality used: mod(), toString(), invert(), hashCode(), equals(), 
					 guessWord(), isActive(), isInvertState(), and getIndex()
~Anticipated use: To create Sequence type objects and modify a word, guessing
				  will be allowed. But for the purpose of this assignment, all
				  words and test words will be provided.
~Value ranges: see Legal/Illegal inputs
~Source of input/output: No input at this time, output will be printed out to
						 the console.
~Legal/Illegal inputs: All words should be in lower case and has 3 or more 
					   letters. Spaces, numbers, and symbols will crash the
					   program. 
~Errors handling: All classes use Programming by Contract; therefore, if an 
				  invalid input is entered, that will violate the contract.
				  No defensive programming is in place.
~Design and intent: A loop is used to test most functionality within the 
					hierarchy to keep it streamline. Only invert() and equals()
					will be tested outside of the loop. generateArray()
					is used as helper method. invert() and mod() from SpasEnum
					was done outside the loop to demonstrate how the it 
					affected the state.
					testInvert() was created to test the invert() function.
~Assumptions: Test words used are - hello, seattle, hello, blank, space, 
									wildest, and dreams
			  Guessed words are - helloo, seaattle, helllo, blank, sce,
			  					  wildestwi, dreamsams

 */

public class P5 {
	
	//Description: Short intro that prints out into the console explaining 
	//			   what the program does.
	private static void intro(){
		System.out.println("Welcome to the Sequence Project!\n\n" +
		"This program will demonstrate the uses of abstract classes, "
		+ "interface, and inheritance.\n" +
		"The following three words will be used to create 3 SequenceEnum "
		+ "objects 1 - 3: \"hello\", \"seattle\", and \"hello\"\n" +
		"The following two words will be used to create 2 SeqExtract "
		+ "objects 4 - 5: \"blank\", and \"space\" \n" +
		"The following two words will be used to create 2 SpasEnum objects "
		+ "6 - 7: \"wildest\", and \"dreams\"");
		System.out.println();
		System.out.println("Different functions will be called for those "
		+ "7 objects.\nIn addition to that, functions wills be called to "
		+ "invert a word and compare two objects.\nLet's get started!");
		System.out.println();
	}

	//Description: Create 7 sequence type objects to use in main
	private static Sequence[] generateArray(){
		String testingWords[] = {"hello", "seattle", "Hello", "blank",
				"space", "wildest", "dreams"};
		Sequence obs[] = {new SequenceEnum(testingWords[0]),
							new SequenceEnum(testingWords[1]),
							new SequenceEnum(testingWords[2]),
							new SeqExtract(testingWords[3]),
							new SeqExtract(testingWords[4]),
							new SpasEnum(testingWords[5]),
							new SpasEnum(testingWords[6])};

		return obs;
	}
	
	//Description: method is called in main(). Test the invert() method on 
	//			   object 4, word number 5. 
	public static void testInvert(String star, Sequence[] se){
		System.out.println(star);
		Sequence objects[] = se;
		System.out.println("The word \"" + objects[4].wordFromUser + "\"" 
		+ " is going to be inverted.");
		System.out.println("State before inverting: " + objects[4].isInvertState());
		System.out.println("Inverted word: " + objects[4].invert());
		System.out.println("Index used to invert: " + objects[4].getIndex());
		System.out.println("State after inverting: " + objects[4].isInvertState());
		System.out.println(star);
		System.out.println();
	}

	//Description: Use the array generated from generateArray() method to test
	//  		   the functionality of the Sequence type objects. A for loop
	//			   is used to iterate all the objects and run the methods. 
	//			   invert(), spasEnum mod(), and equals() will be tested 
	//			   outside of the loop.
	public static void main(String[] args) {
		String guessWord[] = {"helloo", "seaattle", "helllo", "blank", "sce",
				"wildestwi", "dreamsams"};
		String star = 
			"****************************************************************";
		intro();
		
		Sequence objects[] = generateArray();
		testInvert(star, objects);
		
		for (int i = 0; i < objects.length; i++){
			System.out.println("Testing for Object #" + (i + 1));
			System.out.println("State of Sequence object before modifying: " 
					+ objects[i].isActive());
			objects[i].mod();
			System.out.println("State of object after modifying: " 
					+ objects[i].isActive());
			System.out.println("Guess word used is: " + guessWord[i]);
			System.out.println("Was the guess word correct? (True for yes and "
					+ "False for no): " + objects[i].guess(guessWord[i]));
			System.out.println(objects[i].toString());
			System.out.println("Hash code: " + objects[i].hashCode());
			System.out.println(star);
			System.out.println();
		}

		System.out.println("This test SpasEnum mod() function when the state "
				+ "is \"not active\".");
		System.out.println("Modified called again on object #7");
		objects[6].mod();
		System.out.println(objects[6].toString());
		System.out.println(star);
		System.out.println();
		
		System.out.println("Is object 1 and object 2 equal? "
				+ "(True for yes and False for no): " 
				+ objects[0].equals(objects[1]));
		System.out.println("Is object 1 and object 3 equal? "
		   + "(True for yes and False for no): " 
				+ objects[0].equals(objects[2]));
	}
}
