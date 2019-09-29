package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

import ui.*;


public class StudentWindowTest {

    @Before
    public void initializeValues(){
        String name = "Daniel";
        String email = "junghyun.sohn@ucalgary.ca";
        StudentWindow student = new StudentWindow(name, email);
    }

    @After
    public void testAfter(){
        System.out.println("All StudentWindow Tests completed");
    }

    @Test
    public void testSetID(){
        String testEmail = "junghyun.sohn@ucalgary.ca";
        assertEquals("30097623", StudentWindow.getID(testEmail));
        testEmail = "jacob.cuke@ucalgary.ca";
        assertEquals("30030179", StudentWindow.getID(testEmail));

        testEmail = "invalidemail";
        assertEquals(null, StudentWindow.getID(testEmail));

        testEmail = "";
        assertEquals(null, StudentWindow.getID(testEmail));

        testEmail = "123";
        assertEquals(null, StudentWindow.getID(testEmail));

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
