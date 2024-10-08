package sustain;

import java.util.Random;

/**
 * This class represents the dice that the players roll at the start of each
 * turn, containing a field for the number of sides and method for rolling.
 */
public class Dice {

	/**
	 * The number of sides the dice has.
	 */
	private int numberOfSides;

	/**
	 * Constructor with argument to set field to assigned value on object creation.
	 * 
	 * @param numberOfSides
	 */
	public Dice(int numberOfSides) {

		this.numberOfSides = numberOfSides;
	}

	/**
	 * Gets number of sides.
	 * 
	 * @return number of sides.
	 */
	public int getNumberOfSides() {
		return numberOfSides;
	}

	/**
	 * Sets number of sides.
	 * 
	 * @param numberOfSides
	 */
	public void setNumberOfSides(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}

	/**
	 * Simulates rolling the dice by generating a random number based on the number
	 * of sides the dice has and the values represented on each side.
	 * 
	 * @return the result of the dice roll
	 */
	protected int roll() {

		Random rd = new Random();

		int random = rd.nextInt(numberOfSides);

		return random + 1;

	}
}
