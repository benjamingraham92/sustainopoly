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

/**
 *  A class to JUnit test Board Class
 */
class BoardTest {
	
	Board board;
	
	private static class TestableSquare extends Square{ //concrete subclass of square. allows tester to instantiate square object
		
		private String name;
		
		public TestableSquare(String name) {
			
			super(name);
			this.name=name;
			
		}
		
		public String getName() {
			
			return name;
		}

		@Override
		protected String getBoardDetails() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	
	List<Region> regions;
	
	List<Square> areas;
	
	ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		regions = new ArrayList<>();
	    areas = new ArrayList<>();

	    Region region1 = new Region();
	    region1.setName("Africa");

	    Area area1 = new Area("Area 1", 0, 100, List.of(10, 20, 30, 40, 50), null, region1);
	    Area area2 = new Area("Area 2", 0, 100, List.of(10, 20, 30, 40, 50), null, region1);
	    
	    areas.add(area1);
	    areas.add(area2);
	    
	    region1.setAreasWithinRegion(List.of(area1, area2));

	    regions.add(region1);

	    board = new Board(areas, regions);
		
	}
	
	
	


	/**
	 * Test method for {@link sustain.BoardTest#Board(java.util.List)}.
	 */
	@Test
	void testBoardConstructorAllArgumennts() {
		
		assertNotNull(board);
	    assertEquals(regions, board.getRegionList());
	    assertNotNull(board.getSquaresOrderedByRegion());
	    assertEquals(2, board.getSquaresOrderedByRegion().size()); 
	}

	/**
	 * Test method for {@link sustain.BoardTest#getRegionList()}.
	 */
	@Test
	void testGetSetRegionList() {
		
		List<Region> newRegionList = new ArrayList<>();
		board.setRegionList(newRegionList);
		assertEquals(newRegionList, board.getRegionList());
		
	}

	/**
	 * Test method for {@link sustain.BoardTest#getOrderedAreaList()}.
	 */
	@Test
	void testGetSetOrderedAreaList() {
		
		List<Square> newSquaresOrderedByRegion = new ArrayList<>();
		board.setSquaresOrderedByRegion(newSquaresOrderedByRegion);
		assertEquals(newSquaresOrderedByRegion, board.getSquaresOrderedByRegion());
		
	}
	
	/**
	 * Test method for {@link sustain.BoardTest#generateOrderedAreaList()}.
	 */
	@Test
	void testGenerateOrderedAreaList() {
	
		Region r1 = new Region();

		Area r1a1 = new Area("r1a1", 100, 260,
				List.of(22, 110, 330, 800, 975, 1150), null, r1);
		Area r1a2 = new Area("r1a1", 100, 280,
				List.of(24, 120, 360, 850, 1025, 1200), null, r1);

		r1.setName("r1");
		r1.setAreasWithinRegion(List.of(r1a1, r1a2));
		r1.setOwner(null);
		
		
		Region r2 = new Region();

		Area r2a1 = new Area("r2a1", 100, 260,
				List.of(22, 110, 330, 800, 975, 1150), null, r2);
		Area r2a2 = new Area("r2a1", 100, 280,
				List.of(24, 120, 360, 850, 1025, 1200), null, r2);

		r2.setName("r2");
		r2.setAreasWithinRegion(List.of(r2a1, r2a2));
		r2.setOwner(null);
		
		
		List<Square> expectedOutput = new ArrayList<Square>(List.of(r1a1, r1a2, r2a1, r2a2));
		
		Board testBoard = new Board(List.of(r1a1, r1a2, r2a1, r2a2), List.of(r1, r2));
		
		List<Square> outputFromGenerateFunction = testBoard.generateBoardWithAreasGroupedInRegions(List.of(r1a1, r1a2, r2a1, r2a2));
		
		assertEquals(expectedOutput, outputFromGenerateFunction);
		
		System.out.println(expectedOutput);
		System.out.println("--------------");
		System.out.println(outputFromGenerateFunction);
		
		Board testBoardBad = new Board(List.of(r1a2, r2a1, r2a1, r1a1), List.of(r2, r1));
		
		assertNotEquals(expectedOutput, testBoardBad.generateBoardWithAreasGroupedInRegions(outputFromGenerateFunction));
		
		System.out.println("negative test");
		System.out.println(expectedOutput);
		System.out.println("---------------");
		System.out.println(testBoardBad.generateBoardWithAreasGroupedInRegions(List.of(r1a1, r1a2, r2a1, r2a2)));
		
		
	}
	
	
	
	/**
	 * 
	 * Test method for generateBoardWithAreasGroupedInRegions
	 */
	@Test
	void testGenerateBoardWithAllTypesOfSquares() {
		
		List<Square>inputSquares=List.of(
				
			    new TestableSquare("Area 1"),
				new TestableSquare("World Climate Change Summit"),
				new TestableSquare("Just Stop Oil Protest"),
				new TestableSquare("Area 2")
				
				);
				
		List<Square> orderedSquares= board.generateBoardWithAreasGroupedInRegions(inputSquares);
		
		assertEquals("World Climate Change Summit",orderedSquares.get(0).getName());
		assertEquals("Just Stop Oil Protest",orderedSquares.get(1).getName());
	}
	
	/**
	 * 
	 * Test method for SquareOnBoard(empty list)
	 */
	@Test
	void testSetGetSquaresOnBoardEmptyList() {
		
		List<Square>emptyList = new ArrayList<>();
		board.setSquaresOnBoard(emptyList);
		
		assertTrue(board.getSquaresOnBoard().isEmpty());
		
		
	}
	
	
	/**
	 * Test method for SquareOnBoard (populated list)
	 * 
	 */
	@Test
	void testSetGetSqauresOnBoardPopulatedList() {
	
	
		
	    List<Square>squares=new ArrayList<>();
	    squares.add(new TestableSquare("Square 1"));
	    squares.add(new TestableSquare("Square 2"));
	    board.setSquaresOnBoard(squares);
	    
	    
	    
	    assertNotNull(board.getSquaresOnBoard());
	    assertEquals(2,board.getSquaresOnBoard().size());
	    
	    assertEquals("Square 1", ((TestableSquare)board.getSquaresOnBoard().get(0)).getName());
        assertEquals("Square 2", ((TestableSquare)board.getSquaresOnBoard().get(1)).getName());
		
		
	}
	
	
	
	/**
	 * Test method for {@link sustain.BoardTest#displayAreasOrderedByRegion()}.
	 */
	@Test
	void testDisplayOrderedAreaList() {
		
	System.setOut(new PrintStream(outputStreamCaptor));

		Region r2 = new Region();

		Area r2a1 = new Area("r2a1", 100, 260, List.of(22, 110, 330, 800, 975, 1150), null, r2);

		r2.setName("r2");
		r2.setAreasWithinRegion(List.of(r2a1,r2a1));
		r2.setOwner(null);

		Board testBoard = new Board(List.of(r2a1, r2a1), List.of(r2));

		
		testBoard.displayAreasOrderedByRegion();	
		

		String altExpected = r2a1.toString().trim();
		
	
		StringBuilder sb = new StringBuilder();
		String newLine = "\n";

		sb.append("area: " + "r2a1");
		sb.append(newLine);

		sb.append("owner: " + "null");
		sb.append(newLine);

		sb.append("devlevel : " + "100");
		sb.append(newLine);
		
		sb.append("sustainability type : NOT_SET");
		sb.append(newLine);

		sb.append("region : " + "r2");
		sb.append(newLine);
		
		sb.append(newLine);
		// second
		sb.append("area: " + "r2a1");
		sb.append(newLine);

		sb.append("owner: " + "null");
		sb.append(newLine);

		sb.append("devlevel : " + "100");
		sb.append(newLine);
		
		sb.append("sustainability type : NOT_SET");
		sb.append(newLine);

		sb.append("region : " + "r2");
		
		
		String expected = sb.toString();
		
		assertEquals(expected.trim().replaceAll("\\r\\n", "\n"), outputStreamCaptor.toString().trim().replaceAll("\\r\\n", "\n"));
		
		
	}
	
	
}
