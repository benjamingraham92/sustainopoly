package sustain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sustain.Player;
import sustain.Area;
import sustain.Region;

/**
 * Tests constructor and getters/setters from Area class.
 */
class AreaTest {

	// Test data structure
	private String testName;
	private int testDevelopmentLevel;
	private int testPurchasePrice;
	private List<Integer> testRentDevLevel;
	private List<Integer> newRentDevLevel;
	private Player testOwner;
	private Region testRegion;
	private Area area;

	private String colour;
	private String endOfName;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testName = "Area X";
		testDevelopmentLevel = 3;
		testPurchasePrice = 200;
		testOwner = new Player("Dave", "red", 500, 3);
		testRegion = new Region();
		testRentDevLevel = Arrays.asList(25, 50, 75, 100);
		newRentDevLevel = Arrays.asList(30, 60, 90);

		// create Area instance with test data
		area = new Area(testName, testDevelopmentLevel, testPurchasePrice, testRentDevLevel, testOwner, testRegion);

		colour = "red";
		endOfName = "[0m";
	}

	/**
	 * Test method for constructor with valid arguments
	 * {@link sustain.Area#PurchasableArea(java.lang.String, int, int, java.util.List, sustain.Player, sustain.Region)}.
	 */
	@Test
	void testPurchasableAreaConstructorValid() {
		assertEquals(testName, area.getName());
		assertEquals(testDevelopmentLevel, area.getDevelopmentLevel());
		assertEquals(testPurchasePrice, area.getPurchasePrice());
		assertEquals(testRentDevLevel, area.getRentDevLevel());
		assertEquals(testOwner, area.getOwner());
		assertEquals(testRegion, area.getRegion());
	}

	/**
	 * Test method for development level getter/setter
	 * {@link sustain.Area#setDevelopmentLevel(int)}.
	 */
	@Test
	void testGetSetDevelopmentLevel() {
		area.setDevelopmentLevel(testDevelopmentLevel);
		assertEquals(testDevelopmentLevel, area.getDevelopmentLevel());
	}

	/**
	 * Test method for purchase price getter/setter
	 * {@link sustain.Area#setPurchasePrice(int)}.
	 */
	@Test
	void testGetSetPurchasePrice() {
		area.setPurchasePrice(testPurchasePrice);
		assertEquals(testPurchasePrice, area.getPurchasePrice());
	}

	/**
	 * Test method for rent development level getter/setter
	 * {@link sustain.Area#setRentDevLevel(java.util.List)}.
	 */
	@Test
	void testGetSetRentDevLevel() {
		assertIterableEquals(testRentDevLevel, area.getRentDevLevel());
		area.setRentDevLevel(new ArrayList<>(newRentDevLevel));
		assertIterableEquals(newRentDevLevel, area.getRentDevLevel());
	}

	/**
	 * Test method for region getter/setter {@link sustain.Area#setRegion(Region)}
	 */
	@Test
	void testGetSetRegion() {
		area.setRegion(testRegion);
		assertEquals(testRegion, area.getRegion());
	}

	/**
	 * Test method for owner getter/setter
	 * {@link sustain.Area#setOwner(sustain.Player)}.
	 */
	@Test
	void testGetSetOwner() {
		Player newOwner = new Player("Alice", "red", 600, 1);
		area.setOwner(newOwner);

		assertEquals(newOwner, area.getOwner());
		assertEquals(colour + "Dave" + endOfName, testOwner.getName());
		assertEquals(500, testOwner.getBalance());
		assertEquals(3, testOwner.getCurrentSquare());

	}

	/**
	 * Test method for {@Link sustain.Region#toString()}.
	 */
	@Test
	void testToString() {
		String expected = "area: Area X\n" + "owner: Player [name=" + colour + "Dave" + endOfName
				+ ", balance=500, currentSquare=3" + ", ownedAreas=0, ownedRegions=0" + "]\n" + "devlevel : 3\n"
				+ "sustainability type : NOT_SET\n" + "region : null\n";
		String actual = area.toString();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link sustain.PurchasableArea#getCurrentRent()}.
	 */
	@Test
	void testGetCurrentRent() {
		assertEquals(100, area.getCurrentRent()); // Assuming testDevelopmentLevel is 3 and testRentDevLevel has been
													// updated accordingly
	}

	/**
	 * Test method for {@link sustain.PurchasableArea#getUpgradeCosts()}.
	 */
	@Test
	void testUpgradeCosts() {
		List<Integer> expectedUpgradeCosts = Arrays.asList(50, 100, 150, 200, 250);
		assertEquals(expectedUpgradeCosts, Area.getUpgradeCosts());
	}

	@Test
	void testGetUnsustainableProfiltMultiplier() {
		double expected = 2;

		double actual = Area.getUnsustainableProfitMultipler();

		assertEquals(expected, actual);
	}

	@Test
	void testGetUnsustainableDevelopments() {

		List<String> expectedDevs = new ArrayList<String>(List.of(
				"Allow overfishing/overfarming causing depletion of animal levels and damaging ecosystems.",
				"Construct luxury chalets and golf courses, disrupting mountain ecosystems and natural landscapes.",
				"Permit large-scale logging operations without reforestation efforts, causing deforestation and habitat fragmentation.",
				"Allow mining activities for minerals like coal or rare earth metals, resulting in habitat destruction and water pollution.",
				"Introduce fracking operations for natural gas extraction, risking water contamination and seismic instability in mountain regions."));

		List<String> actualDevs = Area.getUnsustainableDevelopments();

		assertEquals(expectedDevs, actualDevs);

	}

	@Test
	void testGetSustainableDevelopments() {
		List<String> expectedDevs = new ArrayList<String>(List.of(
				"Establish eco-friendly hiking trails with interpretive signs highlighting the importance of mountain ecosystems.",
				"Establish wind farms to harness renewable energy while minimizing impact on habitats. ",
				"Integrate solar energy with energy storage systems to provide reliable and resilient power supply for communities.",
				"Collaborate with local communities to create responsible ecotourism ventures, supporting livelihoods while preserving habitats.",
				"Establish national parks in the area – ensuring the area’s natural beauty will be protected for generations"));

		List<String> actualDevs = Area.getSustainableDevelopments();

		assertEquals(expectedDevs, actualDevs);

	}

	@Test
	void testGetBurnDown() {
		int actual = area.getBurnDown();
		int expected = 2;

		assertEquals(expected, actual);

	}

	@Test
	void testSetGetSustainableDevelopmentType() {

		List<SustainabilityType> susTypes = new ArrayList<SustainabilityType>(
				List.of(SustainabilityType.NOT_SET, SustainabilityType.SUSTAINABLE, SustainabilityType.UNSUSTAINABLE));

		for (SustainabilityType susType : susTypes) {
			area.setSustainableDevelopmentType(susType);
			assertEquals(susType, area.getSustainableDevelopmentType());
		}
	}

	@Test
	void testProgressBurnDown() {
		int initialBurnDown = area.getBurnDown();

		assertTrue(area.progressBurnDown());

		assertEquals(initialBurnDown - 1, area.getBurnDown());

		area.progressBurnDown();

		// burn down returns false after the area cannot be burned down any more
		assertFalse(area.progressBurnDown());

	}

	@Test
	void testGetDevelopmentLevelStrings() {
		area.setDevelopmentLevel(1);
		area.setSustainableDevelopmentType(SustainabilityType.SUSTAINABLE);

		List<String> susDevStrings = area.getDevelopmentLevelStrings();

		List<String> expectedSusDevStrings = new ArrayList<String>(List.of(
				"Establish eco-friendly hiking trails with interpretive signs highlighting the importance of mountain ecosystems."));
		assertEquals(expectedSusDevStrings, susDevStrings);

		area.setSustainableDevelopmentType(SustainabilityType.UNSUSTAINABLE);
		List<String> unsusDevStrings = area.getDevelopmentLevelStrings();

		List<String> expectedUnsusDevStrings = new ArrayList<String>(
				List.of("Allow overfishing/overfarming causing depletion of animal levels and damaging ecosystems."));
		
		assertEquals(expectedUnsusDevStrings, unsusDevStrings);
	}
	
	@Test
	void testGetCurrentRentUnsustainable() {
		area.setSustainableDevelopmentType(SustainabilityType.UNSUSTAINABLE);
		area.setRentDevLevel(newRentDevLevel);
		area.setDevelopmentLevel(1);
		
		int expectedRentNotBurnedDown =  2 * area.getRentDevLevel().get(1);
		
		int actualRentNotBurnedDown = area.getCurrentRent();
		
		assertEquals(expectedRentNotBurnedDown, actualRentNotBurnedDown);
		
		// burn down the unsustainable development
		
		while(area.progressBurnDown()) {
			// no implementation
		};
		
		assertFalse(area.progressBurnDown());
		
		assertEquals(0, area.getCurrentRent());
		
		
		
	}
	

}
