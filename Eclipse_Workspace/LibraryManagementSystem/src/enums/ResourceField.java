package enums;

//	Defines the enums to be used in the 
//	library management software for describing
//	the available resources
public enum ResourceField {
	
	ID,
	TITLE,
	TYPE,
	
	AUTHOR,
	PRODUCER,
	LOCATION,
	
	STATUS,
	HOLDER,
	QUEUE;
	
	public static ResourceField[] searchables() {
		ResourceField[] rfa = {ResourceField.ID, ResourceField.TITLE, ResourceField.AUTHOR};
		return rfa;
	}
	
}
