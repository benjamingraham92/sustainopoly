/**
 * 
 */
package sustain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to JUnit test Player Class
 */
class PlayerTest {

	// Test data

	Player p;

	String name;
	String colour;
	int balance;
	int currentSquare;
	int ownedAreas;
	int ownedRegions;

	String endOfName;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		p = new Player();

		name = "Fran";

		colour = "red";

		balance = 1000;

		currentSquare = 5;

		ownedAreas = 0;

		ownedRegions = 0;

		endOfName = "[0m";

	}

	/**
	 * Test method for {@link sustain.Player#Player()}.
	 */
	@Test
	void testPlayerDefaultConstructor() {

		assertNotNull(p);

	}

	/**
	 * Test method for
	 * {@link sustain.Player#Player(java.lang.String, String, int, int)}.
	 */
	@Test
	void testPlayerConstructorAllArguments() {

		Player p = new Player(name, colour, balance, currentSquare);
		assertEquals(colour + name + endOfName, p.getName());
		assertEquals(balance, p.getBalance());
		assertEquals(currentSquare, p.getCurrentSquare());
		p.initialiseAreasAndRegions();
		assertNotNull(p.getOwnedAreas());
		assertNotNull(p.getOwnedRegions());

	}

	/**
	 * Test method for {@link sustain.Player#getName()}.
	 */
	@Test
	void testGetSetName() {

		p.setName(name);
		p.setNameWithColour(name);
		assertEquals(name + name + endOfName, p.getName());

	}

	/**
	 * Test method for {@link sustain.Player#getBalance()}.
	 */
	@Test
	void testGetSetBalance() {

		p.setBalance(balance);
		assertEquals(balance, p.getBalance());

	}

	/**
	 * Test method for current area getter/setter
	 * {@link sustain.Player#getCurrentSquare()}.
	 */
	@Test
	void testGetSetCurrentArea() {

		p.setCurrentSquare(currentSquare);
		assertEquals(currentSquare, p.getCurrentSquare());

	}

	/**
	 * Test method for {@link sustain.Player#toString()}.
	 */
	@Test
	void testToString() {
		p.setName(name);
		p.setNameWithColour(colour);
		p.setBalance(balance);
		p.setCurrentSquare(currentSquare);
		String expectedToString = "Player [name=" + (colour + name + endOfName) + ", balance=" + balance
				+ ", currentSquare=" + currentSquare + ", ownedAreas=" + ownedAreas + ", ownedRegions=" + ownedRegions
				+ "]";
		assertEquals(expectedToString, p.toString());
	}

	/**
	 * Test method for {@link Player#hashCode()}.
	 */
	@Test
	void testHashCode() {
		Player player1 = new Player(name, colour, balance, currentSquare);
		Player player2 = new Player(name, colour, balance, currentSquare);
		Player player3 = new Player("Alice", colour, 100, 1);
		assertEquals(player1.hashCode(), player2.hashCode());
		assertNotEquals(player1.hashCode(), player3.hashCode());
	}

	/**
	 * Test method for {@link Player#equals(Object)}.
	 */
	@Test
	void testEquals() {
		
		// DON'T MESS WITH THESE - THESE ARE NEGATIVE TEST FOR THE .equals METHOD
		Player player1 = new Player(name, colour, balance, currentSquare);
		Player player2 = new Player(name, colour, balance, currentSquare);
		Player player3 = new Player("Alice", colour, 100, 1);
		Player nullPlayer = null;
		Object objectPlayer = new Object();
		assertTrue(player1.equals(player2));
		assertFalse(player1.equals(player3));
		assertFalse(player1.equals(nullPlayer));
		assertFalse(player1.equals(objectPlayer));

	}

	/**
	 * Test method for {@link Player#setOwnedAreas(int)}.
	 */
	@Test
	void testGetSetOwnedAreas() {
		p.setOwnedAreas(5);
		assertEquals(5, p.getOwnedAreas());
	}

	/**
	 * Test method for {@link Player#setOwnedRegions(int)}.
	 */
	@Test
	void testGetSetOwnedRegions() {
		p.setOwnedRegions(2);
		assertEquals(2, p.getOwnedRegions());
	}

	/**
	 * Test method for {@link Player#incrementOwnedAreas()}.
	 */
	@Test
	void testIncrementOwnedAreas() {
		p.incrementOwnedAreas();
		assertEquals(1, p.getOwnedAreas());
	}

	/**
	 * Test method for {@link Player#incrementOwnedRegions()}.
	 */
	@Test
	void testIncrementOwnedRegions() {
		p.incrementOwnedRegions();
		assertEquals(1, p.getOwnedRegions());
	}

	@Test
	void testEndGameToString() {
		p.setName(name);
		p.setNameWithColour(colour);

		System.out.println(p.endGameToString());

		String expected = colour + name + endOfName;

		expected += " - BALANCE : 0 - AREAS : 0 - REGIONS : 0";

		assertEquals(expected, p.endGameToString());

	}

}
