import database.DatabaseUtils;
import enums.ResourceType;

public class GHYSDiAH {
	
	public static void main(String[] args) {
		
		DatabaseUtils.addNewResource(132, "Amazing Book", ResourceType.BOOK, "E.E.Cummings", "Shelf on floor");
		
	}
	
}
