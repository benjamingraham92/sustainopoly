package sustain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents squares on the board that are areas and contains fields
 * for level of development, price, rent cost and upgrade costs.
 */
public class Area extends Square {

	/**
	 * The level of development of the area (initially set to 0).
	 */
	private int developmentLevel;

	/**
	 * The price of the area.
	 */
	private int purchasePrice;

	/**
	 * The rent costs per development level(?).
	 */
	private List<Integer> rentDevLevel;

	/**
	 * The cost to upgrade development levels(?).
	 */
	private static final List<Integer> UPGRADE_COSTS = new ArrayList<Integer>(List.of(50, 100, 150, 200, 250));

	/**
	 * The owner of the area (initially set to null).
	 */
	private Player owner;
	
	/**
	 * The region the square pertains to.
	 */
	private Region region;	
	
	private static final double UNSUSTAINABLE_PROFIT_MULTIPLER = 2;
	private SustainabilityType sustainableDevelopmentType = SustainabilityType.NOT_SET;
	private int burnDown = 2;
	
	public int getBurnDown() {
		return burnDown;
	}




	protected boolean progressBurnDown() {
		if (burnDown > 0) {
			this.burnDown--;
			return true;
		} else {

			return false;
		}

	}
	
	private static final List<String> SUSTAINABLE_DEVELOPMENTS = new ArrayList<String>(List.of(
			"Establish eco-friendly hiking trails with interpretive signs highlighting the importance of mountain ecosystems.",
			"Establish wind farms to harness renewable energy while minimizing impact on habitats. ",
			"Integrate solar energy with energy storage systems to provide reliable and resilient power supply for communities.",
			"Collaborate with local communities to create responsible ecotourism ventures, supporting livelihoods while preserving habitats.",
			"Establish national parks in the area – ensuring the area’s natural beauty will be protected for generations"));

	private static final List<String> UNSUSTAINABLE_DEVELOPMENTS = new ArrayList<String>(List.of(
			"Allow overfishing/overfarming causing depletion of animal levels and damaging ecosystems.",
			"Construct luxury chalets and golf courses, disrupting mountain ecosystems and natural landscapes.",
			"Permit large-scale logging operations without reforestation efforts, causing deforestation and habitat fragmentation.",
			"Allow mining activities for minerals like coal or rare earth metals, resulting in habitat destruction and water pollution.",
			"Introduce fracking operations for natural gas extraction, risking water contamination and seismic instability in mountain regions."));

	
	
	

	/**
	 * Constructor with arguments to set fields to assigned values on object
	 * creation.
	 * 
	 * @param name
	 * @param developmentLevel
	 * @param purchasePrice
	 * @param rentDevLevel
	 * @param owner
	 * @param region
	 */
	public Area(String name, int developmentLevel, int purchasePrice, List<Integer> rentDevLevel,
			Player owner, Region region) {
		super(name);
		this.developmentLevel = developmentLevel;
		this.purchasePrice = purchasePrice;
		this.rentDevLevel = rentDevLevel;
		this.owner = owner;
		this.region = region;
	}





	/**
	 * Gets the level of development.
	 * 
	 * @return level of development
	 */
	public int getDevelopmentLevel() {
		return developmentLevel;
	}

	/**
	 * Sets the level of development.
	 * 
	 * @param developmentLevel
	 */
	public void setDevelopmentLevel(int developmentLevel) {
		this.developmentLevel = developmentLevel;
	}

	/**
	 * Gets the price.
	 * 
	 * @return price
	 */
	public int getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Sets the price.
	 * 
	 * @param purchasePrice
	 */
	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Gets the rent cost per development level.
	 * 
	 * @return rent costs
	 */
	public List<Integer> getRentDevLevel() {
		return rentDevLevel;
	}

	/**
	 * Sets the rent cost per development level.
	 * 
	 * @param rentDevLevel
	 */
	public void setRentDevLevel(List<Integer> rentDevLevel) {
		this.rentDevLevel = rentDevLevel;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * Gets region
	 * 
	 * @return region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Sets regions
	 * 
	 * @param region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * Gets the cost to upgrade level of development.
	 * 
	 * @return cost
	 */
	public static List<Integer> getUpgradeCosts() {
		return UPGRADE_COSTS;
	}
	
	/**
	 * Gets the current rent from a given area at a given development level.
	 * 
	 * @return rent.
	 */
	public int getCurrentRent() {
		int toReturn = 0;

		if(this.getSustainableDevelopmentType() == SustainabilityType.UNSUSTAINABLE && burnDown ==0) {
			return 0;
		}else if (this.getSustainableDevelopmentType() == SustainabilityType.UNSUSTAINABLE && burnDown > 0) {

			toReturn = (int) (this.getRentDevLevel().get(developmentLevel) * UNSUSTAINABLE_PROFIT_MULTIPLER);
			return toReturn;
		} else {
			return this.getRentDevLevel().get(developmentLevel);
		}
	}

	/**
	 * Outputs values of each field to console.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String newLine = "\n";

		sb.append("area: " + this.getName());
		sb.append(newLine);

		Player owner = this.getOwner();
		if(owner != null) {
			sb.append("owner: " + this.getOwner().getName());
		} else {
			sb.append("owner: not owned");
		}
		
		sb.append(newLine);

		sb.append("devlevel : " + this.developmentLevel);
		sb.append(newLine);

		sb.append("sustainability type : " + this.sustainableDevelopmentType);
		sb.append(newLine);
		
		sb.append("region : " + this.getRegion().getName());
		sb.append(newLine);
		
		

		return sb.toString();
	}

	public static double getUnsustainableProfitMultipler() {
		return UNSUSTAINABLE_PROFIT_MULTIPLER;
	}

	public SustainabilityType getSustainableDevelopmentType() {
		return sustainableDevelopmentType;
	}

	public void setSustainableDevelopmentType(SustainabilityType sustainableDevelopmentType) {
		this.sustainableDevelopmentType = sustainableDevelopmentType;
	}




	public static List<String> getUnsustainableDevelopments() {
		return UNSUSTAINABLE_DEVELOPMENTS;
	}




	public static List<String> getSustainableDevelopments() {
		return SUSTAINABLE_DEVELOPMENTS;
	}




	public List<String> getDevelopmentLevelStrings() {

		List<String> developmentStrings = new ArrayList<String>();

		if (this.getSustainableDevelopmentType() == SustainabilityType.UNSUSTAINABLE) {
			for (int i = 0; i < this.getDevelopmentLevel(); i++) {
				String dev = UNSUSTAINABLE_DEVELOPMENTS.get(i);

				developmentStrings.add(dev);
			}

		} else {
			for (int i = 0; i < this.getDevelopmentLevel(); i++) {
				String dev = SUSTAINABLE_DEVELOPMENTS.get(i);

				developmentStrings.add(dev);
			}

		}

		return developmentStrings;
	}




	@Override
	protected String getBoardDetails() {
		StringBuilder sb = new StringBuilder();
		
		String boardDisplayName;
		String owner;
		String rent;
		String devLevel;
		String purchasePrice;
		
		
		// board display name
		boardDisplayName = this.getBoardDisplayName();
		
		// owner
		
		if(this.getOwner() == null) {
			owner = "Not Owned";
		} else {
			owner = this.getOwner().getName();
		}
		
		// rent
		
		int currentRent = this.getCurrentRent();
		
		rent = "Rent: $" + currentRent + "M";
		
		// dev level
		
		int devLevelInt = this.getDevelopmentLevel();
		
		if(this.getBurnDown() == 0) {
			devLevel = "DevLvl: --";
		} else {
			devLevel = "DevLvl: " +devLevelInt; 
		}
		
		// purchase price
		int purchasePriceInt = this.getPurchasePrice();
		purchasePrice = "Price: $" + purchasePriceInt + "M";
		
		sb.append("area, "+ boardDisplayName+ ", "+ owner+ ", "+rent + ", " + devLevel+ ", " +purchasePrice);
		
		return sb.toString();
	}

}
