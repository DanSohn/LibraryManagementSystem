package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.FacultyWindow;

import static org.junit.Assert.assertEquals;


public class FacultyWindowTest {

    @Before
    public void initializeValues(){
        String name = "John";
        String email = "john.smith@staff.ca";
        FacultyWindow student = new FacultyWindow(name, email);
    }

    @After
    public void testAfter(){
        System.out.println("All FacultyWindow Tests completed");
    }

    @Test
    public void testSetID(){
        String testEmail = "john.smith@staff.ca";
        assertEquals("30087345", FacultyWindow.getID(testEmail));
        testEmail = "dave@floyd.com";
        assertEquals("30012985", FacultyWindow.getID(testEmail));

        testEmail = "invalidemail";
        assertEquals(null, FacultyWindow.getID(testEmail));

        testEmail = "";
        assertEquals(null, FacultyWindow.getID(testEmail));

        testEmail = "123";
        assertEquals(null, FacultyWindow.getID(testEmail));

    }

    /*
    @Test
    public void testRenewBook(){}

    @Test
    public void testGetBooks() {

    }

    @Test
    public void testListBooks() {
    }

    @Test
    public void testGetBookName() {
    }

     */
}
