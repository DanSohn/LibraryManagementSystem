package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import java.io.*;
import java.util.ArrayList;

import ui.*;
import utils.Utilities;


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
                            "Book: Harry Potter and the Goblet of Fire Due date: 22/07/2019   ID:409276_" +
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
        assertEquals("22072019", student2.getDate("409276"));
        assertEquals("26042019", student2.getDate("100443"));
    }

    @Test
    public void testRenewBook(){
        StudentWindow student = new StudentWindow("Jacob", "jacob.cuke@ucalgary.ca");
        String id = student.getID(student.getEmail());
        String[] bookID = {"30327717032019", "10044326042019"};

        // test wise, if renew succeeds, then the bookID and the id's received from the database should differ
        // test purposes I chose ones that would be different, since to test something that I can never revert to the
        // base case easily would be too difficult to test in a junit test. Example: a book would come in 10042019. If
        // i renew it, then it'll be two weeks. Every time i do the test, it'll incremenet two weeks. I would never be
        // able to get a consistent EXPECTED assert...
        student.renewBook(bookID[0]);
        student.renewBook(bookID[1]);


        ArrayList<String> itemLines	= Utilities.readTextFile("ItemDatabase.txt");

        for (String line : itemLines) {
            String[] bits = line.split("\\*");

            // Person found, looking for his books now
            if(bits[0].equals(id)){
                String[] booksOut = bits[6].split(",");
                //books found, looking for a match now
                for (int i = 0; i < booksOut.length; i++) {
                    if (booksOut[i].startsWith(bookID[0])) {
                        assertEquals("Book shouldn't be renewed", bookID[0], booksOut[i]);
                    }
                    if (booksOut[i].startsWith(bookID[1])) {
                        assertEquals("Book shouldn't be renewed", bookID[1], booksOut[i]);
                    }

                }
            }
        }
    }

}
