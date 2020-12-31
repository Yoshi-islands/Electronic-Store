
package eStoreSearch;

public class Book extends Product {

    // Instance variables for each book
    private String author;
    private String publisher;

    /**
     * The constructor for initializing Book
     * 
     * @param ProductID   the identification for the products
     * @param description the description of the product
     * @param year        the year of the product
     * @param price       The price of the product
     * @param author      the author of the product
     * @param publisher   The publisher of the product
     */
    public Book(String productID, String description, int year, double price, String author, String publisher) {

        super(productID, description, year, price);

        if (inputCheck(author, publisher) == false) {
            this.author = author;
            this.publisher = publisher;

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
    public Book() {

        super();
        this.author = "<Enter user>";
        this.publisher = "<Enter publisher>";

    }

    /**
     * 
     * This is a getter which will return the author of the product
     *
     * @return author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * 
     * This is a getter which will return the publisher of the product
     * 
     * @return publisher
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * 
     * This is a a toString method which will display the object as a string
     *
     * @return String of the object in sentence format
     */
    @Override
    public String toString() {

        return super.toString() + this.author + "\n" + this.publisher + "\n";

    }

    public static boolean inputCheck(String author, String publisher) {

        return author == null && publisher == null;

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

            Book book = (Book) parentObject;
            return super.equals(book) && author.equals(book.author) && publisher.equals(book.author);

        }

    }

}
