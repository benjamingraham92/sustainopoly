 package sustain;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
 
/**
* 
* Test Class for neutral Area
*/
class NeutralAreaTest {
 
	String name;
	NeutralArea neutralArea;
 
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
 
		name = "Neutral";
		neutralArea = new NeutralArea(name);
	}
	
	/**
	 * 
	 */
	@Test
	void testNeutralAreaConstructor() {
		
		NeutralArea neutralArea = new NeutralArea(name);
		
		assertEquals(name, neutralArea.getName());
		
	}
 
	/**
	 * 
	 * Test GetSetName
	 * Test method for {@link sustain.NeutralArea#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetName() {
	
		neutralArea.setName(name);
		assertEquals(name, neutralArea.getName());
 
	}

}
