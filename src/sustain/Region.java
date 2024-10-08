package sustain;

import java.util.List;

/**
 * This class represents a region and contains fields for name, owner and a list
 * of the areas within it.
 */
public class Region {

	/**
	 * The name of the region.
	 */
	private String name;

	/**
	 * A list of the areas with the region.
	 */
	private List<Area> areasWithinRegion;

	/**
	 * The owner of the region, which can be null.
	 */
	private Player owner;

	/**
	 * Default constructor setting fields to null values on object creation.
	 */
	public Region() {
	}

	/**
	 * Constructor with arguments setting fields to assigned values on object
	 * creation.
	 * 
	 * @param name
	 * @param areasWithinRegion
	 * @param owner
	 */
	public Region(String name, List<Area> areasWithinRegion, Player owner) {
		this.name = name;
		this.areasWithinRegion = areasWithinRegion;
		this.owner = owner;
	}

	/**
	 * Gets name.
	 * 
	 * @return name of region.
	 */
	public String getName() {
		return name;
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
	 * Gets list of areas within region.
	 * 
	 * @return list of areas within region.
	 */
	public List<Area> getAreasWithinRegion() {
		return areasWithinRegion;
	}

	/**
	 * Sets list of areas within region.
	 * 
	 * @param areasWithinRegion
	 */
	public void setAreasWithinRegion(List<Area> areasWithinRegion) {
		this.areasWithinRegion = areasWithinRegion;
	}

	/**
	 * Gets owner of region.
	 * 
	 * @return owner of region.
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets owner of region.
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Outputs areas within region to console.
	 */
	public void displayAreasWithinRegion() {
		System.out.println("Areas within : " + this.getName());
		for (Area area : areasWithinRegion) {
			System.out.println(area.toString());
		}
	}

	/**
	 * Returns details of each Region object as String.
	 */
	@Override
	public String toString() {
		return "Region [name=" + name + ", areasWithinRegion=" + areasWithinRegion + ", owner=" + owner + "]";
	}

	
	
	
	
}
