
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record {

    private String customerSSN; //SSN of the customer who has purchased one or more units of the product
    private String productID; //ID of the purchased product
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {

        setCustomerSSN(customerSSN);
        setProductID(productID);
        setPurchaseDate(purchaseDate);
        this.paid = false;

    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Getters and Setters
    //method 1 ---> get and set customerSSN
    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    //method 2 ---> get and set productID
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    //method 5 ---> update paid status
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean getPaid() {
        return paid;
    }

    //method 3 ---> returns the data of the customer product comma separated
    @Override
    public String lineRepresentation() {
        return getCustomerSSN() + "," + getProductID() + "," + getPurchaseDate().format(formatter) + "," + getPaid();
    }

    //method 4 ---> check if the product is paid
    public boolean isPaid() {
        return paid;
    }

    //method 6 ---> returns a string key composed of customerSSN, productID, and purchaseDate
    @Override
    public String getSearchKey() {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }

}
