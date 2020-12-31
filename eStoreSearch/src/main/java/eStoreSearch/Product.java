
/**
 * The Product class -> This class serves as the parent class for the electronic
 * and for the book classes. It has all of the common elements as a parent
 * supercalass
 *
 * @author Maaz Syed
 * @version 1.01
 * @since 11/9/2020
 */

package eStoreSearch;

public class Product {

    protected String productID;
    protected String description;
    protected double price;
    protected int year;

    /**
     * The constructor for initializing the products
     * 
     * @param ProductID   the identification for the products
     * @param description the description of the product
     * @param year        the year of the product
     * @param price       The price of the product
     *
     */
    public Product(String productID, String description, int year, double price) {

        if (inputCheck(productID, description, year) == false) {

            this.productID = productID;
            this.description = description;
            this.year = year;
            this.price = price;

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
    public Product() {

        this("000000", "<Enter description>", 2000, 0.0);

    }

    /**
     * 
     * This is a getter which will return the product Id
     * 
     * @return productID
     */
    public String getProductid() {
        return this.productID;
    }

    /**
     * 
     * This is a getter which will return the description
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 
     * This is a getter which will return the price
     * 
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * 
     * This is a getter which will return the year
     * 
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * 
     * This is a a toString method which will display the object as a string
     * 
     * 
     *
     * @return String of the object in sentence format
     */
    public String toString() {
        return this.productID + "\n" + this.description + "\n" + this.price + "\n" + this.year + "\n";
    }

    /**
     * 
     * This is a an inputCheck method which will check the input for the parameters
     * passed into the constructor
     * 
     * @param ProductID   the identification for the products
     * @param description the description of the product
     * @param year        the year of the product
     * 
     * @return Boolean to ensure if the check was successful or not
     */
    public static boolean inputCheck(String productID, String description, int year) {

        return (productID == null || description == null || year <= 0);

    }

    /**
     * This method will test the equality of the Product type object
     * 
     * @return boolean value indicating the equality
     */
    public boolean equals(Object parentObject) {

        if (parentObject == null) {
            return false;
        } else if (getClass() != parentObject.getClass()) { // Checking to make sure the objects are created from same
                                                            // class
            return false;
        } else {

            Product productObject = (Product) parentObject; // Downcast now that we know we are dealing with the product
                                                            // class here
            return (productID.equals(productObject.productID) && description.equals(productObject.description)
                    && year == productObject.year && price == productObject.price);

        }

    }

}
