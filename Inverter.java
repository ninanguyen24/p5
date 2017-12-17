package p5;

/*Author: Nina Nguyen
Filename: Inverter.java
11/24/2017
Version 1
Project: P5
CPSC 5011

~Description: This is an interface. Any class that implements this class 
			  needs to implement the two method. Inverter has an index that 
			  should be declared by the implement class.
              The Inverter class will swap a letter at index with the letter 
              at index + 1.
~Functionality: invert() and isInvertState() are both method signatures.
~Legal State: None, implement class will define state.
~Dependencies: Relies on implemented class.
~Anticipated use: To be implemented by another class. Cannot be instantiated.
~State: Defined by implemented class.
 */

public interface Inverter {
	
	//Description: This method will be implemented in the class that uses 
	//				this interface. Method should swap the letter at index
	//   		 	and index + 1.
    public String invert();
    
    //Description: This method return the current state of the inverter
    //			   in classes that uses the interface.
    public boolean isInvertState();

}
