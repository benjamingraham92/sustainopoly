package sustain;

/**
 * This abstract class represents a square on the game board.
 */
public abstract class Square {

	/**
	 * The name of the square.
	 */
	private String name;
	
	
	private String boardDisplayName;
	
	

	/**
	 * Constructor with arguments to set fields to assigned values.
	 * 
	 * @param name
	 * @param region
	 */
	public Square(String name) {
		this.name = name;
	}

	/**
	 * Gets name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	

	/**
	 * Sets name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	protected abstract String getBoardDetails();

	public String getBoardDisplayName() {
		return boardDisplayName;
	}

	public void setBoardDisplayName(String boardDisplayName) {
		this.boardDisplayName = boardDisplayName;
	}

	
	
	
}
