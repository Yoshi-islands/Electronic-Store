/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package eStoreSearch;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;

public class AppTest {

    @Test
    public void Test() {

        App application = new App();
        Book testBook = new Book("000001", "This is a test object for book", 2020, 100.25, "Maaz Syed", "Java");

        // Perform sequential checks for the book class along with the electronics
        // class, with its association for input
        // from various methods of the user input class
        /* REDUNDANCY TESTS NOT INCLUDED as they cover both objects */
        assertNotNull("Book should not return any null values", testBook.toString());
        assertNotNull("Getting any values should not result in null", testBook.getProductid());
        assertNotNull("Getting any values should not result in null", testBook.getDescription());
        assertNotNull("Getting any values should not result in null", testBook.getAuthor());
        assertNotNull("Getting any values should not result in null", testBook.getPublisher());
        assertNotEquals(0.00, testBook.getPrice());
        assertFalse(0.00 == testBook.getPrice());
        assertTrue(testBook.getYear() > 1000 && testBook.getYear() < 9999);
        String author = testBook.getAuthor();
        String publisher = testBook.getPublisher();
        assertFalse(Book.inputCheck(author, publisher));

        assertTrue(App.isDigit(testBook.getProductid()));
        assertFalse(App.isDigit("000F01"));
        assertTrue(App.yearCheck("-2020"));
        assertTrue(App.yearCheck("2020-2021"));
        assertFalse(App.yearCheck("2020--"));

        // compare parsed years against one another
        String year1 = "2020";
        String year2 = "1990";

        int parseYear1 = Integer.parseInt(year1);
        int parseYear2 = Integer.parseInt(year2);

        assertFalse(App.parsedYearCheck(parseYear1, parseYear2));
        assertTrue(App.parsedYearCheck(parseYear2, parseYear1));

        // Test the default years against one another
        App comparisonApplication = new App();
        assertTrue(application.equals(comparisonApplication));

        assertTrue(App.checkAdd("Add", "ADD"));
        assertFalse(App.checkAdd("Add", "Search"));

        Electronics testElectronic = new Electronics("000002", "This is a test object for electronics", 2020, 100.25,
                "Java");
        assertNotNull("Electronic should not return any null values", testElectronic.toString());
        assertNotNull("Getting any values should not result in null", testElectronic.getMaker());
        assertNotNull("Getting any values should not result in null", testElectronic.getProductid());
        assertNotNull("Getting any values should not result in null", testElectronic.getDescription());
        assertNotEquals(0.00, testElectronic.getPrice());
        assertFalse(0.00 == testElectronic.getPrice());
        assertTrue(testElectronic.getYear() > 1000 && testElectronic.getYear() < 9999);
        assertTrue(App.isDigit(testElectronic.getProductid()));
        assertFalse(Electronics.inputCheck(testElectronic.getMaker()));

        // create testable parent object
        Product testProduct = new Product("000009", "This is a test object for parent product class", 2020, 100.25);
        assertNotNull("Electronic should not return any null values", testProduct.toString());
        assertNotNull("Getting any values should not result in null", testProduct.getDescription());
        assertNotNull("Getting any values should not result in null", testProduct.getProductid());

        assertNotEquals(0.00, testProduct.getPrice());
        assertFalse(0.00 == testProduct.getPrice());
        assertTrue(testProduct.getYear() > 1000 && testProduct.getYear() < 9999);
        assertTrue(App.isDigit(testProduct.getProductid()));
        assertFalse(
                Product.inputCheck(testProduct.getProductid(), testProduct.getDescription(), testProduct.getYear()));

        EStoreSearch newSearch = new EStoreSearch();
        Book searchBook1 = new Book("000010", "Code complete", 1990, 300.50, "Micheal Ferguson", "unknown inc.");
        Electronics searchElectronic1 = new Electronics("000020", "iphone XR 3.36ghz", 2018, 1000.0, "Apple inc");

        newSearch.setProduct(searchBook1);
        newSearch.setProduct(searchElectronic1);

        // Test the reading and writing from the app.java and then commence the tests
        // after completion
        String toTest = "book//" + searchBook1.getProductid() + "//" + searchBook1.getDescription() + "//"
                + searchBook1.getPrice() + "//" + searchBook1.getYear() + "//" + searchBook1.getAuthor() + "//"
                + searchBook1.getPublisher();

        PrintWriter printWriter = null;

        try {
            App.writeToFile(printWriter, toTest, "junittest.txt");
        } catch (IOException e) {
            printWriter.close();
            System.exit(0);
        }
        if (printWriter != null) {
            printWriter.close();
        }

        // Read this object and comapre it to the string read from the file
        Scanner inputStream = null;
        String test = null;

        try {
            inputStream = new Scanner(new FileInputStream("junittest.txt"));

            if (inputStream.hasNextLine()) {
                test = inputStream.nextLine();
            }
        } catch (IOException e) {
            System.exit(0);
        }

        inputStream.close();

        assertEquals(toTest, test);

        // Continue for testing the searching function
        App yearsSearch = new App(0, 0, 0, 0, 1990);
        String splitStrings[] = {};
        newSearch.searchFor(yearsSearch, splitStrings, "000010", true, false, true);
        newSearch.setIndex(0);
        assertTrue(newSearch.compareYearIndex(0));

        // Creating a false search option
        App yearsSearch2 = new App(0, 0, 0, 0, 2015);
        EStoreSearch newSearch2 = new EStoreSearch();
        newSearch2.searchFor(yearsSearch2, splitStrings, "000010", true, false, true);
        // no indexes here newSearch2.setIndex();
        assertFalse(newSearch2.compareYearIndex(0));

        App yearsSearch3 = new App(0, 0, 0, 0, 2018);
        EStoreSearch newSearch3 = new EStoreSearch();
        newSearch3.searchFor(yearsSearch3, splitStrings, "000020", true, false, true);
        newSearch3.setIndex(1);
        assertTrue(newSearch3.compareYearIndex(1));

        // Continue the test for the hashing with the search
        App yearsSearch4 = new App(0, 0, 0, 0, 1990); // no years here only keywords test
        EStoreSearch hashSearch = new EStoreSearch();
        hashSearch.searchFor(yearsSearch4, splitStrings, "00000", false, true, true);
        hashSearch.testHash("Code", "iphone");
        hashSearch.addHash(0, 1, "Code", "iphone");
        hashSearch.setIndex(0);
        String newSplitString[] = { "Code", "iphone" };
        ArrayList<Integer> intersectionTest = new ArrayList<Integer>();
        intersectionTest = hashSearch.keyWordYearIntersection(newSplitString);
        assertTrue(hashSearch.compareYearIndex(0));

    }

}
