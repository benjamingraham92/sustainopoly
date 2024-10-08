/**
 * 
 */
package sustain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sustain.Square;
import sustain.NeutralArea;
import sustain.Player;
import sustain.Area;
import sustain.Region;

/**
 * A class to JUnit test Region Class
 */
class RegionTest {

	// Test data

	Region region;
	Region region2;
	List<Area> areasWithinRegion;
	Player owner;

	private String endOfName;
	private String colour;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		region = new Region();
		region.setName("Africa");
		region2 = new Region();
		region2.setName("Europe");
		areasWithinRegion = new ArrayList<>();
		areasWithinRegion.add(new Area("Area1", 10, 220, List.of(18, 90, 250, 700, 875, 1050), null, region));
		// areasWithinRegion.add(new NeutralArea("Area2"));
		region.setAreasWithinRegion(areasWithinRegion);
		owner = new Player("TestOwner", "\u001B[33m", 1000, 5);
		region.setOwner(owner);

		colour = "red";

	}

	/**
	 * Test method for default constructor.
	 */
	@Test
	void testRegionDefaultConstructor() {
		region = new Region();
		assertNotNull(region);

	}

	/**
	 * Test method for constructor with arguments.
	 */
	@Test
	void testRegionConstructorAllArguments() {

		String name = "TestRegion";
		List<Area> areasWithinRegion = new ArrayList<>();
		Player owner = new Player("TestOwner", "\u001B[33m", 1000, 5);

		region = new Region(name, areasWithinRegion, owner);

		assertEquals(name, region.getName());
		assertEquals(areasWithinRegion, region.getAreasWithinRegion());
		assertEquals(owner, region.getOwner());

	}

	/**
	 * Test method for name getter/setter {@link sustain.Region#getName()}.
	 */
	@Test
	void testGetSetName() {

		region.setName("New Name");
		assertEquals("New Name", region.getName());

	}

	/**
	 * Test method for {@link sustain.Region#getAreasWithinRegion()}.
	 */
	@Test
	void testGetSetAreasWithinRegion() {

		List<Area> newAreas = new ArrayList<>();
		region.setAreasWithinRegion(newAreas);
		assertEquals(newAreas, region.getAreasWithinRegion());

	}

	/**
	 * Test method for {@link sustain.Region#getOwner()}.
	 */
	@Test
	void testGetSetOwner() {

		Player newOwner = new Player("Alice", "red", 2000, 10);
		region.setOwner(newOwner);
		assertEquals(newOwner, region.getOwner());

	}

	/**
	 * Test method for {@link sustain.Region#displayAreasWithinRegion()}.
	 */
	@Test
	void testDisplayAreasWithinRegion() {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		region.displayAreasWithinRegion();

		String printedOutput = outputStream.toString().trim();

		System.setOut(System.out);

		String expectedOutput = "Areas within : Africa\n" 
				+ "area: Area1\n"
				+ "owner: null\ndevlevel : 10\nsustainability type : NOT_SET\nregion : Africa";

		assertEquals(expectedOutput.replaceAll("\\r\\n", "\n"), printedOutput.replaceAll("\\r\\n", "\n"));
	}

	/**
	 * Test method for {@link sustain.Region#displayAreasWithinRegion()}.
	 */
	@Test
	void testToString() {
		String expectedName = "Africa";
		String expectedArea1String = "area: Area1\nowner: null\ndevlevel : 10\nregion : Africa\n";
		String expectedOwnerString = "Player [name=TestOwner, balance=1000, currentSquare=5]";
		// tests missing?
		
		String expected = "Region [name=Africa, areasWithinRegion=[area: Area1\r\n"
				+ "owner: null\r\n"
				+ "devlevel : 10\r\n"
				+ "sustainability type : NOT_SET\r\n"
				+ "region : Africa\r\n"
				+ "], owner=Player [name=[33mTestOwner[0m, balance=1000, currentSquare=5, ownedAreas=0, ownedRegions=0]]";
		
		String actual = region.toString();
		
		assertEquals(expected.replaceAll("\\r\\n", "\n"), actual.replaceAll("\\r\\n", "\n"));
		
	}
}
