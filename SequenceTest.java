package p5;
/*
Author: Nina Nguyen
Filename: SequenceTEST.java
12/6/2017
Version 1
Project: P5
CPSC 5011
*/

import static org.junit.Assert.*;

import org.junit.*;

public class SequenceTest {


	@Test
	public void testHashCode() {
		String test1 = "hello";
		String test2 = "world";
		Sequence se1 = new SequenceEnum(test1);
		Sequence se2 = new SequenceEnum(test2);
		se1.mod();
		se2.mod();
		
		//Testing not equal since the modified word is random, it will be
		//hard to get two objects to have the same modified word.
		assertNotSame(se1.hashCode(), se2.hashCode());
	}

	@Test
	public void testEqualsObject() {
		String test1 = "hello";
		String test2 = "world";
		Sequence se1 = new SequenceEnum(test1);
		Sequence se2 = new SequenceEnum(test2);
		se1.mod();
		se2.mod();
		
		//Testing not equal since the modified word is random, it will be
		//hard to get two objects to have the same modified word.
		assertNotEquals(true, se1.equals(se2));
		assertNotEquals(true, se2.equals(se1));
	}


	@Test
	public void testToString() {
		String test1 = "hello";
		Sequence se1 = new SequenceEnum(test1); 
		se1.mod();
		String expected = "Original word: ";
		String expected2 = "Modified word: ";
		assertTrue(se1.toString().contains(expected));
		assertTrue(se1.toString().contains(expected2));
	}

}
