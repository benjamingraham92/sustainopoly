package sustain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PlayerInputValidatorTest extends PlayerInputValidator {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testValidPlayerName() {
        assertTrue(PlayerInputValidator.isValidPlayerName("JohnDoe"));
        assertTrue(PlayerInputValidator.isValidPlayerName("JaneDoe"));
        assertTrue(PlayerInputValidator.isValidPlayerName("Alice"));
        assertTrue(PlayerInputValidator.isValidPlayerName("Bob"));
    }

    @Test
    public void testInvalidPlayerName() {
        assertFalse(PlayerInputValidator.isValidPlayerName("Jo!hn"));
        assertFalse(PlayerInputValidator.isValidPlayerName("123"));
        assertFalse(PlayerInputValidator.isValidPlayerName("!@#$%"));
        assertFalse(PlayerInputValidator.isValidPlayerName(""));
        assertFalse(PlayerInputValidator.isValidPlayerName(" "));
    }

   
}
