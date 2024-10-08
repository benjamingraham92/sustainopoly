package sustain;

public enum SustainabilityType {
	NOT_SET, SUSTAINABLE, UNSUSTAINABLE;
	
	
	@Override
	public String toString() {
		
		switch(this) {
		
		case NOT_SET:
			return "Not Set";
		case SUSTAINABLE:
			return "Sustainable";
		case UNSUSTAINABLE:
			return "Unsustainable";
		default:
			return "Not known";
		
		}
		
		
	}
	
}
