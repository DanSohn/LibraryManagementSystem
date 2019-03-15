import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Io
{
    public static String[] fileToStringArray(String filename) throws IOException 
    {
    	try {
	        FileReader fr = new FileReader(filename);
	         
	        BufferedReader br = new BufferedReader(fr);
	        List<String> lines = new ArrayList<String>();
	        String line = null;
	         
	        while ((line = br.readLine()) != null) {
	            lines.add(line);
	        }
	         
	        br.close();
	        return lines.toArray(new String[lines.size()]);
    	} catch (IOException e) {
    		System.out.println("failed to read file");
    		//return null;
    		throw new IOException();
    	}
    }
    
    // given a file name and a string array;
    // will write every entry in String[] arr to a new line in1 a text file
    public static void save(String db_filename, String[] arr) throws IOException {
    	  BufferedWriter writer = null;
    	  writer = new BufferedWriter(new FileWriter(db_filename));
    	  
    	  for (int i = 0; i < arr.length; i++) {
    	    writer.write(arr[i]);
    	    
    	    writer.newLine();
    	  }
    	  writer.flush(); 
    	  writer.close();  
    }
    
    public static int findLine(String[] arr, int start, String query) {
    	for (int i = start; start < arr.length; i++) {
    		if (arr[i].indexOf(query) != -1) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    
}
