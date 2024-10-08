package sustain;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
class DiceTest {
 
	private Dice dice;
private final int numberOfSides = 2;
 
	
	/**
	 * 
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dice = new Dice(numberOfSides);
	}

	/**
	 * Testing default constructor
	 * 
	 */
	@Test
	void testDiceConstructor() {
 
		assertEquals(numberOfSides, dice.getNumberOfSides());
 
	}

	/**
	 * Testing number of sides of the dice. The test is confirming that methods
	 * modify the state of an object as expected. By using different values for the
	 * initial state and the state after the call to setNumberOfSides this ensures
	 * that the method functions correctly across a range of valid inputs.
	 * 
	 */
	@Test
	void testGetSetNumberOfSides() {
 
		int newNumberOfSides = 6;
		dice.setNumberOfSides(newNumberOfSides);
		assertEquals(newNumberOfSides, dice.getNumberOfSides());
 
	}


 
	/**
	 * Tests the roll method to ensure it produces a result within the expected
	 * range. Since Dice.roll() should return a value between 1 and the number of
	 * sides on the dice, this test repeatedly rolls the dice 1000 times, checking
	 * that each result is within this range. The test passes if all results are
	 * valid, confirming that roll() adheres to the rules of dice rolling.
	 */
	@Test
	void testRoll() {
 
		int rollResult;
		for (int i = 0; i < 1000; i++) {
 
			rollResult = dice.roll();
			assertTrue(rollResult >= 1 && rollResult <= numberOfSides);
			
		}
	}
	
}
 
 
