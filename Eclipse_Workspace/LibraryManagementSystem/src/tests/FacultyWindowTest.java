package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.FacultyWindow;
import ui.StudentWindow;

import static org.junit.Assert.assertEquals;


public class FacultyWindowTest {

    @Before
    public void initializeValues(){
        String name = "John";
        String email = "john.smith@staff.ca";
        FacultyWindow faculty = new FacultyWindow(name, email);
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
    public void testGetBookName() {
        String[] testBookID = {"750284", "200528", "122098", "0"};

        assertEquals("The Bourne Identity", FacultyWindow.getBookName(testBookID[0]));
        assertEquals("A Clash of Kings", FacultyWindow.getBookName(testBookID[1]));
        assertEquals("A Storm of Swords", FacultyWindow.getBookName(testBookID[2]));
        assertEquals(null, FacultyWindow.getBookName(testBookID[3]));
    }
*/
    @Test
    public void testListBooks(){
        FacultyWindow faculty = new FacultyWindow("John", "john.smith@staff.ca");
        assertEquals("No books currently out", faculty.listBooks());

        FacultyWindow faculty2 = new FacultyWindow("Marty", "marty.friedman@megadeth.com");
        assertEquals("No books currently out", faculty2.listBooks());
    }

    @Test
    public void testGetDate(){
        FacultyWindow faculty = new FacultyWindow("John", "john.smith@staff.ca");
        assertEquals(null, faculty.getDate("0"));

    }
}
