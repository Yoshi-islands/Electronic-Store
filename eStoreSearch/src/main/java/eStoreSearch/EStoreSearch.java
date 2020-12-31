
/**
* The eStoreSearch package -> The EStoreSearch class will 
* actually help us store the book and electronic objects and allow us to perform 
* various search operations on them
*
* @author  Maaz Syed
* @version 1.01
* @since   11/9/2020
*/
package eStoreSearch;

import java.util.ArrayList;
import java.util.HashMap;

public class EStoreSearch {

    // Instance variables for the book and electronic objects
    private ArrayList<Product> productList;
    private ArrayList<Integer> yearListIndex;
    private HashMap<String, ArrayList<Integer>> hash;

    /**
     * Constructor to initialize in potential of other eStoreSearches
     * 
     * 
     */
    public EStoreSearch() {

        this.productList = new ArrayList<Product>();
        this.yearListIndex = new ArrayList<Integer>();
        this.hash = new HashMap<String, ArrayList<Integer>>();

    }

    /**
     * 
     * This is a setter which will add a Product object to the product list
     * 
     * @param product , which is the product object
     */
    public void setProduct(Product product) {
        productList.add(product);
    }

    /**
     * 
     * This is a setter which will add a an integer to the index of the integer
     * array
     * 
     * @param integer , which is the product object
     */
    public void setIndex(int index) {
        yearListIndex.add(index);
    }

    /**
     * 
     * This function will test the hashmap from the junti file
     * 
     * @param string1, the first string to be assigned into the hash
     * @param string2  ,the secnd string to be assigned into the hash
     * 
     */
    public void testHash(String string1, String string2) {

        hash.put(string1, new ArrayList<Integer>());
        hash.put(string2, new ArrayList<Integer>());

        return;

    }

    /**
     * 
     * This function will test the hashmap from the junti file
     * 
     * @param index1,  the first index to be added
     * @param index2,  the second index to be added
     * @param string1, the first string to be assigned into the hash
     * @param string2  ,the secnd string to be assigned into the hash
     * 
     */
    public void addHash(int index1, int index2, String string1, String string2) {

        hash.get(string1).add(index1);
        hash.get(string2).add(index2);

        return;

    }

    /**
     * 
     * This function perform the search and then display the search results to the
     * screen
     * 
     * @param newSearch,    this is the application object which will store
     *                      instances of the year variable
     * @param splitStrings, this is the user keywords that will be searched for
     * @param productBool,  this is the boolean for if a productId was entered
     * @param keyBool,      this is the boolean if keywords were entered
     * @param yearBool,     this is the param for if the year was entered
     * 
     */
    public void searchFor(App newSearch, String splitStrings[], String productID, boolean productBool, boolean keyBool,
            boolean yearBool) {

        // Matches for the productID's
        int productListIndex = 0;
        boolean productSearchList = false;
        boolean keySearchList = false;

        // Matches for the year
        boolean yearSearchList = false;

        String[] objectSplit = {}; // initialize to empty not blank
        String[] anotherObjectSplit = {};

        // If the productID is not empty then we need to search both lists
        if (productBool == true) {

            // For the prouct List
            for (int i = 0; i < productList.size(); i++) {
                // if the productID searched for is found within the list
                if (productID.equalsIgnoreCase(productList.get(i).getProductid())) {
                    productListIndex = i; // index of the object found
                    productSearchList = true;
                }
            }

        }

        // If the key words are true then we search both of the lists for the keywords
        if (keyBool == true) {

            // Initialize the hashmap arrayLists
            for (int i = 0; i < splitStrings.length; i++) {
                hash.put(splitStrings[i], new ArrayList<Integer>());
            }

            // Iterating through the list
            for (int i = 0; i < productList.size(); i++) {

                // Each object's description needs to be split
                objectSplit = App.splitString(productList.get(i).getDescription());

                // Iterate for the length of the keywords first
                for (int j = 0; j < splitStrings.length; j++) {

                    for (int k = 0; k < objectSplit.length; k++) {

                        if (splitStrings[j].equalsIgnoreCase(objectSplit[k])
                                && hash.get(splitStrings[j]).contains(i) == false) {

                            hash.get(splitStrings[j]).add(i);
                            keySearchList = true;

                        }
                    }

                }

            }

        }

        System.out.println();

        // if the user has entered the year
        if (yearBool == true) {

            // Get the specific years from the app object in the main class
            int specificYear = newSearch.getSpecificYear();
            int yearAfter = newSearch.getYearAfter();
            int yearBefore = newSearch.getYearPrevious();
            int year1 = newSearch.getYear1();
            int year2 = newSearch.getYear2();

            for (int i = 0; i < productList.size(); i++) {

                // Since the default setting will be zero
                if (specificYear != 0) {

                    if (specificYear == productList.get(i).getYear() && yearListIndex.contains(i) == false) {
                        yearListIndex.add(i);
                        yearSearchList = true;

                    }
                } else if (yearAfter != 0) { // Search the years after the given year

                    if (productList.get(i).getYear() >= yearAfter && yearListIndex.contains(i) == false) {
                        yearListIndex.add(i);
                        yearSearchList = true;

                    }

                } else if (yearBefore != 0) {
                    if (productList.get(i).getYear() <= yearBefore && yearListIndex.contains(i) == false) {
                        yearListIndex.add(i);
                        yearSearchList = true;

                    }
                } else if ((year1 != 0 && year2 != 0)) {

                    if ((productList.get(i).getYear() >= year1) && (productList.get(i).getYear() <= year2)
                            && yearListIndex.contains(i) == false) {
                        System.out.println();
                        yearListIndex.add(i);
                        yearSearchList = true;

                    }

                }

            }

        } // endo of year search

        // Addressing all of the various cases
        if (productBool == true && keyBool == false && yearBool == false) {

            printListIndex(productSearchList, productListIndex);

        }

        if (productBool == true && keyBool == true && yearBool == false) {

            // Remember for multiple cases that each of the indexes we receive should be
            // equal along with the corresponding boolean

            if (productSearchList && keySearchList && productKeyIntersection(splitStrings, productListIndex) == true) {

                printListIndex(true, productListIndex);

            } else {
                System.out.println("Sorry, there are no search results matching this criteria");

            }
        }

        if (productBool == false && keyBool && yearBool == false) {

            // Case where we need to print all occurrences of the keywords that are found
            if (keySearchList) {

                // Print all the indices for the keyword matches
                for (int i = 0; i < splitStrings.length; i++) {

                    // for each of those keywords we want to print each of the indices that are
                    // mapped too
                    for (int j = 0; j < hash.get(splitStrings[i]).size(); j++) {
                        printListIndex(true, hash.get(splitStrings[i]).get(j)); // pass in the index from the ith
                                                                                // iteration of the arraylist mapped to
                                                                                // keyword
                    }

                }

            } else {
                System.out.println();
                System.out.println("Sorry, there are no search results matching this criteria");

            }
        }

        if (productBool == false && keyBool == false && yearBool == false) {

            // Print out information for all of the lists
            for (int i = 0; i < productList.size(); i++) {

                printListIndex(true, i);

            }

        }

        if (productBool && keyBool == false && yearBool) {

            if ((compareYearIndex(productListIndex) == true && yearSearchList)) {
                printListIndex(productSearchList, productListIndex);
            } else {

                System.out.println();
                System.out.println("Sorry, there are no search results matching this criteria");

            }
        }

        if (productBool == false && keyBool == true && yearBool == true) {

            ArrayList<Integer> intersectionSet = new ArrayList<Integer>();

            // Only perform union if both searches yield true
            if (keySearchList && yearSearchList) {

                // create the intersection between the year and keywords that were found
                intersectionSet = keyWordYearIntersection(splitStrings);

                // print this list
                for (int i = 0; i < intersectionSet.size(); i++) {
                    printListIndex(true, intersectionSet.get(i));
                }

            }

            if (!keySearchList && !yearSearchList) {

                System.out.println();
                System.out.println("Sorry, there are no search results matching this criteria");
            }

            // Remove everything from the intersectionSet
            intersectionSet.removeAll(intersectionSet);

        }

        if (productBool && keyBool && yearBool) {

            if ((productSearchList && keySearchList && yearSearchList
                    && fullIntersection(splitStrings, productListIndex)) == true) {

                printListIndex(true, productListIndex);

            } else {
                System.out.println();
                System.out.println("Sorry, there are no search results matching this criteria");

            }

        }

        if (!productBool && !keyBool && yearBool) {

            if (yearSearchList) {
                for (int i = 0; i < yearListIndex.size(); i++) {
                    printListIndex(true, yearListIndex.get(i));
                }
            }

            if (!yearSearchList) {
                System.out.println();
                System.out.println("Sorry, there are no search results matching this criteria");
            }

        }

        clearLists();

        return;
    }

    /**
     * 
     * This function will clear all the lists that are needed for the next iteration
     * of the search
     * 
     *
     * 
     */
    private void clearLists() {

        // Clear the hashtable after using
        hash.clear();
        yearListIndex.removeAll(yearListIndex);

        return;

    }

    /**
     * 
     * This function will perform an intersection operation for the productID and
     * key words that are found
     * 
     * @param splitStrings,     the split strings that the user entered from App
     *                          class
     * @param productListIndex, the index of where the productID was found
     * 
     * @return boolean, which indicates if the intersection was found or not
     * 
     */
    public boolean productKeyIntersection(String splitStrings[], int productListIndex) {

        // Iterate through the split strings for the hashmap
        for (int i = 0; i < splitStrings.length; i++) {

            if (hash.get(splitStrings[i]).contains(productListIndex)) {
                return true; // Meaning the productID does indeed exist within the hash findings
            }

        }

        return false;

    }

    /**
     * 
     * This function will perform an intersection operation for all of the searching
     * fields
     * 
     * @param splitStrings,     the split strings that the user entered from App
     *                          class
     * @param productListIndex, the index of where the productID was found
     * 
     * @return boolean, which indicates if the intersection was found or not
     * 
     */
    public boolean fullIntersection(String splitStrings[], int productListIndex) {

        boolean continueIntersection = false;
        boolean fullIntersection = false;

        // Need to find the intersection for all 3 according to the unique productID
        continueIntersection = productKeyIntersection(splitStrings, productListIndex);

        // continue if theres an intersection between productID and the keywords
        if (continueIntersection) {

            // Do the intersection between the productID and the year
            fullIntersection = compareYearIndex(productListIndex);

            if (fullIntersection) {
                return true;
            }

        }

        return false;

    }

    /**
     * 
     * This function will perform an intersection operation for the keywords and the
     * years
     * 
     * @param splitStrings, the split strings that the user entered from App class
     * 
     * @return arrayList, the arrayList of the full common intersection indices
     * 
     */
    public ArrayList<Integer> keyWordYearIntersection(String splitStrings[]) {

        ArrayList<Integer> intersectionSet = new ArrayList<Integer>();

        // First iterate through the yearindexes
        for (int i = 0; i < yearListIndex.size(); i++) {

            for (int j = 0; j < splitStrings.length; j++) {

                for (int k = 0; k < hash.get(splitStrings[j]).size(); k++) {
                    if (yearListIndex.get(i) == hash.get(splitStrings[j]).get(k)
                            && intersectionSet.contains(yearListIndex.get(i)) == false) {
                        intersectionSet.add(yearListIndex.get(i));
                    }
                }

            }

        }

        return intersectionSet;

    }

    /**
     * 
     * This function will perform a comparison between the year and the indexes of
     * the years arrayList
     * 
     * @param index, the index that we will be searching for within the year
     *               arraylist
     * 
     * @return boolean, which indicates if the index was found or not
     * 
     */
    public boolean compareYearIndex(int index) {

        // Check if we can find the product ID within the indexes of the found years
        for (int i = 0; i < yearListIndex.size(); i++) {
            if (index == yearListIndex.get(i)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 
     * This function will print the list index that its given
     * 
     * @param productSearchlist, if the productid was found
     * @param productListIndex,  the index of where the productID was found
     * 
     *
     * 
     */
    public void printListIndex(boolean productSearchList, int productListIndex) {

        System.out.println();

        if (productSearchList) {
            System.out.println();
            System.out.println(productList.get(productListIndex).toString());
        }

        if (!productSearchList) {
            System.out.println();
            System.out.println("Sorry, there are no search results matching this criteria");
        }

        return;

    }

    /**
     * 
     * This function will perform a linear search looking for duplicate productID's
     * 
     * @param compareID, the productID that is from the user
     * 
     * @return boolean, which indicates if this was true or not
     */
    public boolean allIdCheck(String compareID) {

        if (productList.size() >= 1) {

            // Linear search the booklist to check for the ID
            for (int i = 0; i < productList.size(); i++) {
                if (compareID.equals(productList.get(i).getProductid())) {
                    return true;
                }
            }
        }

        return false;

    }

    public boolean isListEmpty() {
        if (productList.size() == 0) {
            return true;
        }
        return false;
    }

}
