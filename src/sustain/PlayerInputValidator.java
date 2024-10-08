package sustain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks user input only contains letters (i.e. not numbers, punctuation marks
 * or other symbols), using regex methods.
 */
public class PlayerInputValidator {

	/**
	 * Outputs check on player names to console.
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		String playerName1 = "JohnDoe"; // valid player name
//		String playerName2 = "Jo!hn"; // invalid player name
//
//		System.out.println(playerName1 + " is valid: " + isValidPlayerName(playerName1));
//		System.out.println(playerName2 + " is valid: " + isValidPlayerName(playerName2));
//	}

	/**
	 * Determines whether player name is valid or invalid.
	 * 
	 * @param playerName name to check.
	 * @return true if valid; false if invalid.
	 */
	public static boolean isValidPlayerName(String playerName) {
		// regex string for pattern
		String pattern = "^[a-zA-Z]+$";

		// create pattern from regex
		Pattern r = Pattern.compile(pattern);

		// create matcher from regex pattern with playerName input
		Matcher m = r.matcher(playerName);

		// returns true if match is found between matcher object and input string
		return m.matches();
	}
}
