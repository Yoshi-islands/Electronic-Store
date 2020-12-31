
/**
* The eStoreSearch package -> App class will simply
* allow for user input in the creation of the Book
* and Electronic objects which will be dealt with 
* in their respective classes 
* 
*
* @author  Maaz Syed
* @version 1.01
* @since   11/9/2020
*/

package eStoreSearch;

import java.util.Scanner;
import java.util.regex.*;

import jdk.jshell.spi.ExecutionControl.ExecutionControlException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class App {

    private int yearRanges1;
    private int yearRanges2;
    private int yearPrevious;
    private int yearAfter;
    private int specificYear;

    /**
     * The constructor for initializing the main application to branch off
     * 
     * @param year1        the year in the first range
     * @param year2        the year in the second range
     * @param yearPrevious the previous year
     * @param yearAfter    the year after
     * @param specificYear the specific year
     */

    public App(int year1, int year2, int yearPrevious, int yearAfter, int specificYear) {

        this.yearRanges1 = year1;
        this.yearRanges2 = year2;
        this.yearPrevious = yearPrevious;
        this.yearAfter = yearAfter;
        this.specificYear = specificYear;

    }

    /** Default constructor */
    public App() {

        this.yearRanges1 = 0;
        this.yearRanges2 = 0;
        this.yearPrevious = 0;
        this.yearAfter = 0;
        this.specificYear = 0;
    }

    /**
     * 
     * This is an equals method which will check for Apps equality with another
     * object of type app
     * 
     * @param anotherApp the object to comapare with
     * @return boolean, i
     */
    public boolean equals(App anotherApp) {
        return this.yearRanges1 == anotherApp.yearRanges1 && this.yearRanges2 == anotherApp.yearRanges2
                && this.yearPrevious == anotherApp.yearPrevious && this.yearAfter == anotherApp.yearAfter
                && this.specificYear == anotherApp.specificYear;
    }

    /**
     * 
     * This is a getter which will return year1
     * 
     * @return yearRanges1
     */
    public int getYear1() {
        return this.yearRanges1;
    }

    /**
     * 
     * This is a getter which will return year1
     * 
     * @return yearRanges1
     */
    public int getYear2() {
        return this.yearRanges2;
    }

    /**
     * 
     * This is a getter which will return year1
     * 
     * @return yearRanges1
     */
    public int getYearPrevious() {
        return this.yearPrevious;
    }

    /**
     * 
     * This is a getter which will return year1
     * 
     * @return yearRanges1
     */
    public int getYearAfter() {
        return this.yearAfter;
    }

    /**
     * 
     * This is a getter which will return year1
     * 
     * @return yearRanges1
     */
    public int getSpecificYear() {
        return this.specificYear;
    }

    /**
     * 
     * This function will print the menu of options availible
     * 
     * 
     * 
     */
    public static void printOptions() {

        System.out.println();
        System.out.println("Welcome to the eBookStore, enter any of the following commands ");
        System.out.println("1: add");
        System.out.println("2: search");
        System.out.println("3: quit");
        System.out.println();

        return;

    }

    /**
     * 
     * This function will print the types of inputs that the user may enter
     * 
     * 
     * 
     */
    public static void printProducts() {
        System.out.println();
        System.out.println("(1) Book ");
        System.out.println("(2) Electronic ");
        System.out.println();
    }

    /**
     * 
     * This function will check user input to make sure the correct command is
     * entered, and what variations of it
     * 
     * @param command,       the command that is being meant to be entered
     * @param forComparison, the command that we are comparing it too
     * 
     * @return boolean, which indicates if the input is allowed or not
     * 
     */
    public static boolean checkAdd(String command, String forComparison) {

        // Check for if the command is empty, or ignoring case, or
        if ((command.isEmpty() == true || command.equalsIgnoreCase(forComparison) == false)) {

            return false;
        } else {
            return true;
        }

    }

    /**
     * 
     * This function will check if the string entered contains digits or not
     * 
     * @param productIdentification
     * 
     * @return boolean, which indicates if the input is a digit or not
     * 
     */
    public static boolean isDigit(String productIdentification) {

        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex); // compile the regex

        if (productIdentification == null) {
            return false;
        }

        Matcher m = p.matcher(productIdentification);

        return m.matches(); // if the regex matches

    }

    /**
     * 
     * This function will split a string based on the delims for object classes
     * 
     * @param inputString, this is the string to be split
     * 
     * @return splitString, the string that has been split
     * 
     */
    public static String[] splitString(String inputString) {

        String delimiters = "[,.!;:? ]+";
        String[] splitString = inputString.split(delimiters);

        return splitString;

    }

    /**
     * 
     * This function will check if the year the user entered is of correct length
     * 
     * @param inputString, the inputstring in the form of the year
     * 
     * @return boolean, which indicates if the input is allowed or not
     * 
     */
    public static boolean yearCheck(String inputString) {

        if (inputString.length() == 5 || inputString.length() == 9) {
            return true;
        }

        return false;
    }

    /**
     * 
     * This function will check user input to make sure the first year is larger
     * than the second year
     * 
     * @param yearStart, the first year that is passed in
     * @param yearEnd,   the last year that is passed in
     * 
     * @return boolean, which indicates if the input is allowed or not
     * 
     */
    public static boolean parsedYearCheck(int yearStart, int yearEnd) {

        if (yearStart > yearEnd) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 
     * This function will write to the file that is entered on the command line
     * 
     * @param printWriter, externally declared printWriter for writing to the actual
     *                     file
     * @param toWrite,     the string that will be written to the file in the
     *                     appropriate format
     * @param fileName,    the name of the file that is being passed into the method
     * 
     *                     @return, in the odd case will throw an exception that
     *                     needs to be handled elsewhere
     * 
     */
    public static void writeToFile(PrintWriter printWriter, String toWrite, String fileName) throws IOException {

        // Append this object to the end of the file
        // menutype = 1 here since we have book so we are writing a book object to the
        // same file
        FileWriter fileWriter = new FileWriter(fileName, true);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(toWrite);

        printWriter.close();

        return;
    }

    public static void main(String[] args) {

        // Variables
        boolean remain = true;
        Scanner userInput = new Scanner(System.in);
        String command, description, productID, menuOption, year, author, publisher, maker, price, keyWords;
        command = description = productID = menuOption = year = author = publisher = maker = keyWords = "";
        String[] splitStrings = {};

        boolean addCommand, quitCommand, searchCommand, productBool, keyBool, yearBool, specificYear, yearsAfter,
                yearsBefore, yearRange;
        addCommand = quitCommand = searchCommand = productBool = keyBool = yearBool = specificYear = yearsAfter = yearsBefore = yearRange = false;

        int menu, IDparse, yearParse, tempYear, objectType;
        yearParse = menu = IDparse = tempYear = 0;
        double priceParse = 0.0;
        EStoreSearch lists = new EStoreSearch();

        // Load any objects from the file through the command line
        if (args.length != 1) {
            System.out.println("Error <filename.extension> should be entered");
            System.out.println("usage: gradle run --args <filename.extension> ");
            System.out.println();
            System.exit(0);
        }

        // Verify the correct file name and open the file
        Scanner inputStream = null;
        String fileName = args[0];
        fileName = fileName.replaceAll(" ", "");

        // Open the file
        try {
            inputStream = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " does not exist or could not be found");
            System.exit(0);
        }

        // Split the fileds based on these delims
        String fileDelims = "[/]+";

        try {

            // Continue looping until theres no more lines within the file
            while (inputStream.hasNextLine()) {

                String tempString = inputStream.nextLine();
                String splitFields[] = tempString.split(fileDelims);

                // parse all of the common fields
                if (splitFields[0].equalsIgnoreCase("book")) {
                    objectType = 0;
                } else {
                    objectType = 1;
                }

                // The common fields
                productID = splitFields[1];
                description = splitFields[2];
                priceParse = Double.parseDouble(splitFields[3]);
                yearParse = Integer.parseInt(splitFields[4]);

                // Continue the different parsing based on the different objects
                // for the book object
                if (objectType == 0) {

                    author = splitFields[5];
                    publisher = splitFields[6];

                    // Create this object and then set it
                    Book book = new Book(productID, description, yearParse, priceParse, author, publisher);
                    lists.setProduct(book);

                } else {

                    maker = splitFields[5];
                    Electronics electronic = new Electronics(productID, description, yearParse, priceParse, maker);
                    lists.setProduct(electronic);
                }

            }

        } catch (Exception e) {
            System.out.println("Error something went wrong");
            System.out.println("Error : file might contain un-needed blank lines");
            inputStream.close();
            System.exit(0);

        }
        inputStream.close();

        while (remain) { // while remain is true

            // Command loop maintaining the commands of add, search and quit
            printOptions();

            System.out.print("| Enter the name of the command : ");

            command = userInput.nextLine();

            // Check for the validity of the user input for each of the commands, if nothing

            // Search command
            if (((checkAdd(command, "Add") == false) && checkAdd(command, "a") == false)

                    && ((checkAdd(command, "Search") == false) && checkAdd(command, "S") == false)

                    && ((checkAdd(command, "Quit") == false) && checkAdd(command, "q") == false)
                    || command.isEmpty() == true) {

                System.out.println();
                System.out.println("Error, something went wrong with the input");
                continue;
            } else {

                // Check for what command was selected
                if ((checkAdd(command, "Add") == true) || checkAdd(command, "a") == true) {
                    addCommand = true;
                } else if ((checkAdd(command, "Search") == true) || checkAdd(command, "s") == true) {
                    searchCommand = true;
                } else if ((checkAdd(command, "Quit") == true) || checkAdd(command, "q") == true) {
                    quitCommand = true;

                }
            }

            /* ADD COMMAND */
            if (addCommand) {

                try {
                    // Display the product menu
                    printProducts();
                    System.out.print("Enter a number: ");
                    menuOption = userInput.nextLine();

                    if ((menuOption.isEmpty() == false) && (isDigit(menuOption) == true)) {
                        menu = Integer.parseInt(menuOption);
                    }

                    while (menu < 1 || menu > 2 || (menuOption.isEmpty() == true) || menuOption == null
                            || (isDigit(menuOption) == false)) {
                        System.out.println();
                        System.out.println("Error, invalid menuOption <try again> :");
                        menuOption = userInput.nextLine();

                        if (menuOption.isEmpty() == false) {
                            menu = Integer.parseInt(menuOption);
                        }

                    }

                    // get the required field for both of the objects
                    System.out.println();
                    System.out.println("| Enter the product ID :");
                    productID = userInput.nextLine(); // get the intger as a string
                    productID = productID.replaceAll(" ", "");

                    while ((productID.length() != 6) || (productID.isEmpty() == true) || (isDigit(productID) == false)
                            || lists.allIdCheck(productID) == true) {
                        System.out.println();

                        // Separate statement for the product ID
                        if (lists.allIdCheck(productID) == false) {
                            System.out.println("Error, invalid input <try again> :");
                        } else {
                            System.out.println("Error, there is a duplicate ProductID <try again>: ");
                        }
                        productID = userInput.nextLine();

                    }

                    // Get the product description
                    System.out.println();
                    System.out.println("| Enter the description : ");
                    description = userInput.nextLine();

                    while (description.isEmpty()) {
                        System.out.println();
                        System.out.println("Error, invalid input <try again> :");
                        description = userInput.nextLine();
                    }

                    // Get price
                    System.out.println();
                    System.out.println("| Enter the price: ");
                    price = userInput.nextLine();
                    price = price.replaceAll(" ", ""); // Replace any occcurences of spaces to nothing

                    // Only want to convert the price into a double if its a digit
                    if ((price.isEmpty() == false)) {
                        priceParse = Double.parseDouble(price);
                    } else {
                        priceParse = 0.00;
                    }

                    while (priceParse < 0) {
                        System.out.println();
                        System.out.println("Error, invalid input <try again> :");

                        price = userInput.nextLine();

                        if (price.isEmpty() == false) {
                            priceParse = Double.parseDouble(price);
                        } else {
                            priceParse = 0.00;
                        }

                    }

                    // Get the year
                    System.out.println();
                    System.out.println("| Enter the year: ");
                    year = userInput.nextLine();

                    if ((year.isEmpty() == false) && (isDigit(year))) {
                        yearParse = Integer.parseInt(year);
                    }

                    while ((yearParse < 1000) || (yearParse > 9999) || (year.length() > 4) || (year.isEmpty() == true)
                            || (isDigit(productID) == false)) {
                        System.out.println("Error, invalid input <try again>: ");
                        year = userInput.nextLine();
                        if (year.isEmpty() == false) {
                            yearParse = Integer.parseInt(year);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Something went wrong with the program");
                }

                // Perform the next creations according to the menu variable, 1 = Book class, 2
                // = Electronics class
                if (menu == 1) {

                    System.out.println();
                    System.out.println("Additional information about your book");
                    System.out.print(" | Enter the author : ");
                    author = userInput.nextLine();

                    if (author.isEmpty()) {
                        author = "";
                    }

                    System.out.println();
                    System.out.println("| Enter the publisher :");
                    publisher = userInput.nextLine();

                    if (publisher.isEmpty()) {
                        publisher = "";
                    }

                    Book book = new Book(productID, description, yearParse, priceParse, author, publisher);
                    lists.setProduct(book);

                    String toWrite = "book//" + productID + "//" + description + "//" + priceParse + "//" + yearParse
                            + "//" + author + "//" + publisher;

                    PrintWriter printWriter = null;

                    try {
                        writeToFile(printWriter, toWrite, fileName);
                    } catch (IOException e) {

                        System.out.println();
                        System.out.println("Error something went wrong locating the file");
                        printWriter.close();
                        System.exit(0);
                    }

                } else if (menu == 2) {

                    System.out.println();
                    System.out.println("Additional information  about your electronic");
                    System.out.println(" | Enter the maker: ");
                    maker = userInput.nextLine();

                    if (maker.isEmpty()) {
                        maker = "";
                    }

                    Electronics electronic = new Electronics(productID, description, yearParse, priceParse, maker);
                    lists.setProduct(electronic);

                }

                System.out.println();

                // the add command will now be false on next iteration
                addCommand = false;

            } else if (searchCommand) {

                // Book book = new Book(productID, description, yearParse, priceParse, author,
                // publisher);

                if (lists.isListEmpty()) {
                    System.out.println();
                    System.out.println("Error, both of the lists seem to be empty search cannot be used");
                    continue;
                }

                // User input where any of the fields can be empty
                System.out.println();
                System.out.println("| Enter a productID to search for <leave empty if not> :");
                productID = userInput.nextLine();
                productID = productID.replaceAll(" ", "");

                if (productID.isEmpty() == false) {

                    // Only need to check value if its not empty
                    while ((productID.length() != 6) || (isDigit(productID) == false)) {
                        System.out.println();
                        System.out.println("Error, invalid input  <try again>: ");
                        productID = userInput.nextLine();
                        productID = productID.replaceAll(" ", "");

                        // Break so that the user can exit the loop when needed
                        if (productID.isEmpty()) {
                            break;
                        }

                    }

                    productBool = true;
                } // else will default to being false

                // User input for keywords
                System.out.println();
                System.out.println("| Enter keywords to search for <leave empty if not> :");
                keyWords = userInput.nextLine();

                if (keyWords.isEmpty() == false) {

                    // We then want to split the keyword and store it inside of itself
                    splitStrings = splitString(keyWords);
                    keyBool = true;
                } // else will default to being false

                // Get the user input for the year
                while (true) {

                    try {

                        System.out.println();
                        System.out.println("| Enter the year to search for <leave empty if not> : ");
                        year = userInput.nextLine();
                        year = year.replaceAll(" ", "");

                        if (year.isEmpty() == false) {
                            yearBool = true;

                            // For this input check for the valid cases and break if needed
                            if (year.length() != 4) { // for the non specific year cases
                                if (yearCheck(year) == true && ((year.charAt(4) == '-' || year.charAt(0) == '-'))) {

                                    // For -year and year-
                                    if (((year.charAt(4) == '-' || year.charAt(0) == '-') && year.length() == 5)) {

                                        if ((year.charAt(4) == '-')) {

                                            yearsAfter = true;
                                            break;

                                        } else if ((year.charAt(0) == '-')) {

                                            yearsBefore = true;
                                            break;
                                        }

                                        // for the range year-year
                                    } else if (year.charAt(4) == '-' && year.length() == 9) {

                                        yearRange = true;
                                        break;
                                    }

                                }

                            } else if ((year.length() == 4) && (year.length() == 4 && year.contains("-") == false)) { // specific
                                                                                                                      // year
                                                                                                                      // case

                                specificYear = true;
                                break;

                            }

                            System.out.println();
                            System.out.println("| Error invalid input, <please try again>");

                        }

                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Error, something went wrong with the program");
                        continue;
                    }

                    break;

                }

                System.out.println();
                System.out.println("<-----------New Search Initiated------------>");

                // Parse information so that it is availble for the object
                if (specificYear) {

                    yearParse = Integer.parseInt(year);
                    App newSearch = new App(0, 0, 0, 0, yearParse);
                    lists.searchFor(newSearch, splitStrings, productID, productBool, keyBool, yearBool);

                } else if (yearsAfter) {

                    year = year.replace("-", "");
                    yearParse = Integer.parseInt(year);
                    System.out.println("Searching for the years after :" + yearParse);
                    App newSearch = new App(0, 0, 0, yearParse, 0);
                    lists.searchFor(newSearch, splitStrings, productID, productBool, keyBool, yearBool);

                } else if (yearsBefore) {

                    year = year.replace("-", "");
                    yearParse = Integer.parseInt(year);
                    System.out.println("Searching for the years before : " + yearParse);
                    App newSearch = new App(0, 0, yearParse, 0, 0);
                    lists.searchFor(newSearch, splitStrings, productID, productBool, keyBool, yearBool);

                } else if (yearRange) {

                    String yearDelims = "[-]+";
                    String yearRangeSplit[] = year.split(yearDelims);
                    int year1 = Integer.parseInt(yearRangeSplit[0]);
                    int year2 = Integer.parseInt(yearRangeSplit[1]);
                    System.out.println("Searching for the years between :" + year1 + " and " + year2);

                    // Do a year check here, to make sure that year1 is not greater than year2
                    if (parsedYearCheck(year1, year2) == false) {

                        System.out.println();
                        System.out.println("The first year entered is more than the second");
                        System.out.println("Ex: 2021-1998, the year ranges have been swapped");
                        tempYear = year1;
                        year1 = year2;
                        year2 = tempYear;

                    }

                    App newSearch = new App(year1, year2, 0, 0, 0);
                    lists.searchFor(newSearch, splitStrings, productID, productBool, keyBool, yearBool);

                }

                // Check for the other commands, here the keybool being true or false doesn't
                // matter
                if (yearBool == false) {

                    App newSearch = new App();
                    lists.searchFor(newSearch, splitStrings, productID, productBool, keyBool, yearBool);

                }

                // all the end of the search command reset command arg and other arguments
                searchCommand = specificYear = yearsAfter = yearsBefore = yearRange = yearBool = productBool = keyBool = false;

            } else if (quitCommand) {
                System.out.println();
                System.out.println("Thank you for using the program ");
                System.exit(0);
            } else {
                System.out.println("Fatal Error has occured");
            }

        } // while loop
    } // main method

}
// App class
