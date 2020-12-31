
/**
* The eStoreSearch package -> The Electronic class will 
* help initialize the electronic objects which will be stored in EStoreSearch class
*
* @author  Maaz Syed
* @version 1.01
* @since   11/9/2020
*/
package eStoreSearch;

public class Electronics extends Product {

    // Instance variables for each of the eletronics
    private String maker;

    /**
     * The constructor for initializing Electronics
     * 
     * @param ProductID   the identification for the products
     * @param description the description of the product
     * @param year        the year of the product
     * @param price       The price of the product
     * @param maker       the maker of the product
     */
    public Electronics(String productID, String description, int year, double price, String maker) {

        super(productID, description, year, price);

        if (inputCheck(maker) == false) {

            this.maker = maker;
        } else {

            System.out.println("Something went wrong with initialization");
            System.exit(0);
        }

    }

    /**
     * Default constructor for testing purposes non parameter type
     * 
     *
     */
    public Electronics() {

        super();
        this.maker = "<Enter the maker>";

    }

    /**
     * 
     * This is a getter which will return the maker
     * 
     * @return maker
     */
    public String getMaker() {
        return this.maker;
    }

    /**
     * 
     * This is a a toString method which will display the object as a string
     *
     * @return String of the object in sentence format
     */
    public String toString() {
        return super.toString() + this.maker + "\n";
    }

    /**
     * 
     * This is a an inputCheck method which will check the input for the parameters
     * passed into the constructor
     * 
     * @param maker the identification for the maker of the electronic
     * 
     * @return Boolean to ensure if the check was successful or not
     */
    public static boolean inputCheck(String maker) {
        return maker == null;
    }

    /**
     * This method will test the equality of the Student type object
     * 
     * @param parentObject, which is the parent object for ancestor of all objects
     * 
     * @return boolean value indicating the equality
     */
    @Override
    public boolean equals(Object parentObject) {

        if (parentObject == null) {
            return false;
        } else if (getClass() != parentObject.getClass()) {
            return false;
        } else {

            Electronics electronic = (Electronics) parentObject;
            return super.equals(electronic) && maker.equals(electronic.maker);

        }

    }

}
