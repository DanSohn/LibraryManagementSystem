package resources;

public enum ResourceType {
	
	/*
	 * 
	 * ENUM TYPES
	 * 
	 */
	
	BOOK(true),
	DVD(true),
	CD(true),
	
	EBOOK(false),
	ONLINE_ARTICLE(false);
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final boolean IS_PHYSICAL;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a resource type.
	 * 
	 * @param isPhysical Whether the resource type is physical or not.
	 */
	private ResourceType(boolean isPhysical) {
		
		this.IS_PHYSICAL = isPhysical;
		
	}
	
}
