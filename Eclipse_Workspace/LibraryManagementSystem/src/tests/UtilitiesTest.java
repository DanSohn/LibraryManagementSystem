package tests;

import org.junit.After;
import org.junit.Test;
import ui.StudentWindow;
import utils.Utilities;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class UtilitiesTest {

    @After
    public void testAfter(){
        System.out.println("All Utilities Tests completed");
    }

    @Test
    public void getDate(){
        String email = "jacob.cuke@ucalgary.ca";
        String[] items = {"30327717032019", "40927622072019", "10044326042019"};
        assertEquals("17032019", Utilities.getDate(email, items[0]));
        assertEquals("22072019", Utilities.getDate(email, items[1]));
        assertEquals("26042019", Utilities.getDate(email, items[2]));

    }


}
