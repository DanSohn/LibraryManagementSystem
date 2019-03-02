package enums;

public enum ResourceType {
	
	/*
	 * 
	 * ENUM TYPES
	 * 
	 */
	
	BOOK(true, new ResourceField[] {
			ResourceField.AUTHOR,
			ResourceField.LOCATION
	}),
	DVD(true, new ResourceField[] {
			ResourceField.PRODUCER,
			ResourceField.LOCATION
	}),
	
	ONLINE(false, new ResourceField[] {
			ResourceField.URL
	});
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final boolean IS_PHYSICAL;
	
	private final ResourceField[]	STARTING_FIELDS	= {
			ResourceField.ID,
			ResourceField.TITLE,
			ResourceField.TYPE
	};
	private final ResourceField[]	EXTRA_FILEDS;
	private final ResourceField[]	ENDING_FIELDS	= {
			ResourceField.STATUS,
			ResourceField.HOLDER,
			ResourceField.QUEUE
	};
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a resource type.
	 * 
	 * @param isPhysical  Whether the resource type is physical or not.
	 * @param extraFields Any extra fields that the resource type has.
	 */
	private ResourceType(boolean isPhysical, ResourceField[] extraFields) {
		
		this.IS_PHYSICAL	= isPhysical;
		this.EXTRA_FILEDS	= extraFields;
		
		
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Finds the index of a specified field in a resource string.
	 * 
	 * @param field The field to search for.
	 * @return The index of the desired field. -1 if the field is not found.
	 */
	public int indexOfField(ResourceField field) {
		
		int i = 0;
		
		for (ResourceField rf : this.STARTING_FIELDS) {
			if (field == rf) {
				return i;
			}
			i++;
		}
		for (ResourceField rf : this.EXTRA_FILEDS) {
			if (field == rf) {
				return i;
			}
			i++;
		}
		if (this.IS_PHYSICAL) {
			for (ResourceField rf : this.ENDING_FIELDS) {
				if (field == rf) {
					return i;
				}
				i++;
			}
		}
		
		return -1;
		
	}
	
}
