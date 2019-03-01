package resources;

import enums.ResourceType;

public class ResourceParser {
	
	public static String genDBStr(int id, String title, ResourceType type, String... params) {
		
		return String.join("*", Integer.toString(id), title, type.name()) + "*" + String.join("*", params);
		
	}
	
}
