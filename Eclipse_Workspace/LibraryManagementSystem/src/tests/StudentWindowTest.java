package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import java.io.*;
import ui.*;


public class StudentWindowTest {

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
    public void testGetBookName() {
        String[] testBookID = {"750284", "200528", "122098", "0"};

        assertEquals("The Bourne Identity", StudentWindow.getBookName(testBookID[0]));
        assertEquals("A Clash of Kings", StudentWindow.getBookName(testBookID[1]));
        assertEquals("A Storm of Swords", StudentWindow.getBookName(testBookID[2]));
        assertEquals(null, StudentWindow.getBookName(testBookID[3]));
    }
     */

    @Test
    public void testListBooks(){
        StudentWindow student = new StudentWindow("Jacob", "jacob.cuke@ucalgary.ca");
        String expected =   "Book: A Dance with Dragons Due date: 17/03/2019   ID:303277_" +
                            "Book: Harry Potter and the Goblet of Fire Due date: 01/04/2019   ID:409276_" +
                            "Book: Software Engineering for Dummies Due date: 26/04/2019   ID:100443_";
        assertEquals(expected, student.listBooks());

        StudentWindow student2 = new StudentWindow("Daniel", "junghyun.sohn@ucalgary.ca");
        expected = "Book: A Storm of Swords Due date: 21/03/2019   ID:122098_";
        assertEquals(expected, student2.listBooks());
    }

    @Test
    public void testGetDate(){
        StudentWindow student = new StudentWindow("Daniel", "junghyun.sohn@ucalgary.ca");
        assertEquals("21032019", student.getDate("122098"));

        StudentWindow student2 = new StudentWindow("Jacob", "jacob.cuke@ucalgary.ca");
        assertEquals("17032019", student2.getDate("303277"));
        assertEquals("01042019", student2.getDate("409276"));
        assertEquals("26042019", student2.getDate("100443"));
    }

}
