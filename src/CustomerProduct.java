import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
    private String  customerSSN; //SSN of the customer who has purchased one or more units of the product
    private String productID; //ID of the purchased product
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        /*this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid=false;*/
        setCustomerSSN(customerSSN);
        setProductID(productID);
        setPurchaseDate(purchaseDate);
        this.paid = false;

    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Getters and Setters
    public String getCustomerSSN() {
        return customerSSN;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

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

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean getPaid() {
        return paid;
    }

    public String lineRepresentation(){
         return getCustomerSSN() + "," + getProductID() + "," + getPurchaseDate().format(formatter) + "," + getPaid();
    }
    public boolean isPaid(){
        if(paid){
            return true;
        } else {
            return false;
        }
    }


    public String getSearchKey(){
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
    
}
