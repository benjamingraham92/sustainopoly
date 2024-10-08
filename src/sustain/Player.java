package sustain;

import java.util.Objects;

/**
 * This class represents a player in the game with fields for name, balance and
 * current position on the board.
 */
public class Player {

	/**
	 * The name of the player.
	 */
	private String name;
	
	/*
	 * Colour for the players name
	 */
	private String colour; 

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}



















	/**
	 * The balance of resources for each player.
	 */
	private int balance;

	/**
	 * The current square the player is positioned on.
	 */
	private int currentSquare;

	/**
	 * The number of areas owned by the player.
	 * 
	 */
	private int ownedAreas;

	/**
	 * The number of regions owned by the player.
	 */
	private int ownedRegions;

	/**
	 * Default constructor sets fields to null values on object creation.
	 */
	public Player() {
		initialiseAreasAndRegions();
	}

	/**
	 * Constructor with arguments sets fields to specific values on object creation.
	 * 
	 * @param name
	 * @param balance
	 * @param currentSquare
	 */
	public Player(String name, String colour, int balance, int currentArea) {
		this.name = name;
		this.colour = colour;
		this.balance = balance;
		this.currentSquare = currentArea;
		initialiseAreasAndRegions();
	}

	/**
	 * Ensures ownedAreas and ownedRegions fields start at 0 for each player at the
	 * start of each game.
	 */
	public void initialiseAreasAndRegions() {
		this.ownedAreas = 0;
		this.ownedRegions = 0;
		

	}

	/*
	 * Handles setting name colour 
	 */
	public void setNameWithColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Gets name + colour
	 * 
	 * !End string resets to default text or else game will be printed in that colour!
	 * 
	 * @return name
	 */
	
	public String getName() {
		return colour + name + "\u001B[0m"; 
	}

	/**
	 * Sets name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets balance.
	 * 
	 * @return balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Sets balance.
	 * 
	 * @param balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Gets current position on board.
	 * 
	 * @return current position on board
	 */
	public int getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * Sets current position on board.
	 * 
	 * @param currentSquare
	 */
	public void setCurrentSquare(int currentArea) {
		this.currentSquare = currentArea;
	}

	/**
	 * Gets the number of areas owned by the player.
	 * 
	 * @return number of areas the player owns.
	 */
	public int getOwnedAreas() {
		return ownedAreas;
	}

	/**
	 * Sets the number of areas owned by the player.
	 * 
	 * @param ownedAreas
	 */
	public void setOwnedAreas(int ownedAreas) {
		this.ownedAreas = ownedAreas;
	}

	/**
	 * Gets the number of regions owned by the player.
	 * 
	 * @return number of regions the player owns.
	 */
	public int getOwnedRegions() {
		return ownedRegions;
	}

	/**
	 * Sets the number of regions owned by the player.
	 * 
	 * @param ownedRegions
	 */
	public void setOwnedRegions(int ownedRegions) {
		this.ownedRegions = ownedRegions;
	}

	/**
	 * Increments the number of areas owned by the player by one.
	 */
	public void incrementOwnedAreas() {
		this.ownedAreas++;
	}

	/**
	 * Increments the number of regions owned by the player by one.
	 */
	public void incrementOwnedRegions() {
		this.ownedRegions++;
	}

	/**
	 * Outputs details of each player object to console as String.
	 */
	@Override
	public String toString() {
		return "Player [name=" + colour + name + "\u001B[0m" + ", balance=$" + balance + "M, currentSquare=" + currentSquare + ", ownedAreas="
				+ ownedAreas + ", ownedRegions=" + ownedRegions + "]";
	}

	/**
	 * Custom equals method to check players don't have the same name.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * Hash code method for custom equals method above.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}



	



	



	
	
	
	
	



	public String endGameToString() {
		String toReturn = this.getName() + " - BALANCE : $" + this.getBalance() + "M - AREAS : " + this.getOwnedAreas() + " - REGIONS : " + this.getOwnedRegions();
		return toReturn;
	}


}
