package sustain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the game board and contains a field for the squares and
 * regions in the form of a list.
 */
public class Board {

	/**
	 * The squares for on the game board.
	 */
	private List<Square> squaresOnBoard;

	/**
	 * The squares on the board ordered by grouping all the areas within a region
	 * beside each other.
	 */
	private List<Square> squaresOrderedByRegion;

	/**
	 * The list of regions.
	 */
	private List<Region> regionList;

	/**
	 * Constructor with an argument to set list of regions to assigned values and a
	 * call to another method to set list of areas on object creation.
	 * 
	 * @param squaresOnBoard
	 */
	public Board(List<Square> squaresOnBoard, List<Region> regionsOnBoard) {
		this.squaresOnBoard = squaresOnBoard;
		this.regionList = regionsOnBoard;
		this.squaresOrderedByRegion = generateBoardWithAreasGroupedInRegions(squaresOnBoard);
	}

	/**
	 * Gets the squares on the board.
	 * 
	 * @return squares on the board.
	 */
	public List<Square> getSquaresOnBoard() {
		return squaresOnBoard;
	}

	/**
	 * Sets the squares on the board.
	 * 
	 * @param squaresOnBoard
	 */
	public void setSquaresOnBoard(List<Square> squaresOnBoard) {
		this.squaresOnBoard = squaresOnBoard;
	}

	/**
	 * Gets list of regions.
	 * 
	 * @return list of regions
	 */
	public List<Region> getRegionList() {
		return regionList;
	}

	/**
	 * Sets region list.
	 * 
	 * @param regionList
	 */
	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	/**
	 * Gets squares ordered by region.
	 * 
	 * @return
	 */
	public List<Square> getSquaresOrderedByRegion() {
		return squaresOrderedByRegion;
	}

	/**
	 * Sets squares ordered by region.
	 * 
	 * @param squaresOrderedByRegion
	 */
	public void setSquaresOrderedByRegion(List<Square> squaresOrderedByRegion) {
		this.squaresOrderedByRegion = squaresOrderedByRegion;
	}

	/**
	 * Generates ordered list of areas.
	 * 
	 * @return ordered list of areas
	 */
	public List<Square> generateBoardWithAreasGroupedInRegions(List<Square> squaresOnBoard) {
	    List<Square> orderedSquaresOnBoard = new ArrayList<>();
	    
	  Square startSquare = null;
	    Square justStopOilSquare = null;
	    List<Area> areas = new ArrayList<>();

	    // Separate the squares into start square, corner square, and areas
	    for (Square square : squaresOnBoard) {
	        if (square.getName() == "World Climate Change Summit") {
	            startSquare = square;
	        } else if (square.getName() == "Just Stop Oil Protest") {
	            justStopOilSquare = square;
	        } else if (square instanceof Area) {
	            areas.add((Area) square);
	        }
	    }

	    // Add start square
	    if (startSquare != null) {
	        orderedSquaresOnBoard.add(startSquare);
	    }

	    // Add corner square (Assuming 7th square as corner)
	    if (justStopOilSquare != null) {
	        orderedSquaresOnBoard.add(justStopOilSquare);
	    }

	    // Group areas by region and add to the list
	    for (Region region : regionList) {
	        for (Area area : areas) {
	            if (area.getRegion() == region) {
	                orderedSquaresOnBoard.add(area);
	            }
	        }
	    }

	    return orderedSquaresOnBoard;
	}

	/**
	 * Outputs ordered area list to console.
	 */
	public void displayAreasOrderedByRegion() {
		for (Square square : squaresOrderedByRegion) {
			System.out.println(square.toString());
		}
	}

}
