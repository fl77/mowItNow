package mowitnow.com.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mowitnow.com.Pelouse;
import mowitnow.com.Tondeuse;
import mowitnow.exception.IllegalCardinalException;
import mowitnow.exception.IllegalPositionException;

public class MowerControllerTest {

	Pelouse pelouse;
	
	@Before
	public void setUp() throws Exception {
		Pelouse pelouse = Pelouse.getInstance();
		pelouse.init(5, 5);
	}

	/**
	 * Test le resultat donnée comme dans l'example du teste tecnique
	 * @throws IllegalPositionException 
	 * @throws IllegalCardinalException 
	 */
	@Test
	public void myTechnicalTest() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t1 = new Tondeuse();
		t1.setCurrentPosition(1, 2, "N");
		t1.executeInstructionLine ("GAGAGAGAA");
		Assert.assertEquals("1 3 N", t1.getCurrentPositionString());
		
		Tondeuse t2 = new Tondeuse();
		t2.setCurrentPosition(3, 3, "E");
		t2.executeInstructionLine ("AADAADADDA");
		Assert.assertEquals("5 1 E", t2.getCurrentPositionString());
		
	}
	
	@Test
	public void negativeYTest() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t3 = new Tondeuse();
		t3.setCurrentPosition(0, 0, "E");
		t3.executeInstructionLine ("AADAADADDA");
		Assert.assertEquals("2 0 E", t3.getCurrentPositionString());
	}
	
	/**
	 * Quand x et y sont negatives, elle doit rester dans le coin bas, gauche
	 * @throws IllegalPositionException 
	 * @throws IllegalCardinalException 
	 */
	@Test
	public void negativeTest() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t4 = new Tondeuse();
		t4.setCurrentPosition(0, 1, "W");
		t4.executeInstructionLine ("AAAGAADAAA");
		Assert.assertEquals("0 0 W", t4.getCurrentPositionString());
	}
	
	@Test
	public void nullTest() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t5 = new Tondeuse();		
		t5.setCurrentPosition(2, 3, "W");
		t5.executeInstructionLine(null);
		Assert.assertEquals("2 3 W", t5.getCurrentPositionString());
		t5.executeInstructionLine("");
		Assert.assertEquals("2 3 W", t5.getCurrentPositionString());
	}
	
	
	/**
	 * For a cardinal point different then expected an exception is thown
	 * @throws IllegalPositionException 
	 * @throws IllegalCardinalException 
	 */
	@Test(expected = IllegalCardinalException.class)
	public void wrongCardinalTest() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t3 = new Tondeuse();
		t3.setCurrentPosition(2, 2, "F");
		t3.executeInstructionLine ("AADAADADDA");		
	}

	/**
	 * For a cardinal point different then expected an exception is thown
	 * @throws IllegalPositionException 
	 * @throws IllegalCardinalException 
	 */
	@Test (expected = IllegalPositionException.class)
	public void wrongInitPoint() throws IllegalCardinalException, IllegalPositionException {
		Tondeuse t3 = new Tondeuse();
		t3.setCurrentPosition(15, 16, "N");
		t3.executeInstructionLine ("AADAADADDA");		
	}
		

}
