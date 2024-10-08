package sustain;

/**
 * This class represents a square on the board that is not an area, extending to
 * the Square class, and contains getters/setters for name field in super
 * class (is this necessary?)
 */
public class NeutralArea extends Square {

	/**
	 * Constructor with arguments to set fields to assigned values
	 * 
	 * There are no fields – could make delete NeutralArea class and not make
	 * Square class abstract anymore, using it as class for start square and
	 * nothing square?
	 * 
	 * @param name
	 * @param region
	 */
	public NeutralArea(String name) {
		super(name);
	}
	
	String areaFunction;

	public String getAreaFunction() {
		return areaFunction;
	}

	public void setAreaFunction(String areaFunction) {
		this.areaFunction = areaFunction;
	}

	/**
	 * Gets name, overriding superclass method.
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	/**
	 * Sets name, overriding superclass method.
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	protected String getBoardDetails() {
		StringBuilder sb = new StringBuilder();
		
		String displayName = this.getBoardDisplayName();
		
		String displayFunctions = this.getAreaFunction();
		
		
		sb.append("neutral," + displayName +", "+ displayFunctions);
		
		
		return sb.toString();
	}
	
	

}
