import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity) {
        if (productsDatabase.getRecord(productID) != null) {
            message("Warning:", "Product already exists!");
            return;
        }

        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity);
        productsDatabase.insertRecord(p);
        productsDatabase.saveToFile();
        message("Success:", "Product added successfully!");
    }

    public Product[] getListOfProducts() {
        productsDatabase.readFromFile();
        ArrayList<Product> all = productsDatabase.returnAllRecords();
        return all.toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        customerProductDatabase.readFromFile();
        ArrayList<CustomerProduct> all = customerProductDatabase.returnAllRecords();
        return all.toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();

        Product prod = productsDatabase.getRecord(productID);
        if (prod == null) {
            message("Error:", "Product not found!");
            return false;
        }

        if (prod.getQuantity() == 0) {
            message("Error:", "Out of stock.");
            return false;
        }

        prod.setQuantity(prod.getQuantity() - 1);
        productsDatabase.saveToFile();

        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(cp);
        customerProductDatabase.saveToFile();

        message("Success:", "Purchase completed successfully!");
        return true;
    }

    public double returnProduct(String customerSSN, String productID,
                                LocalDate purchaseDate, LocalDate returnDate) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (!isReturnDateValid(purchaseDate, returnDate)) {
            message("Error:", "Return date cannot be before purchase date!");
            return -1;
        }

        productsDatabase.readFromFile();
        Product prod = productsDatabase.getRecord(productID);
        if (prod == null) {
            message("Error:", "Product not found!");
            return -1;
        }

        String key = customerSSN + "," + productID + "," + purchaseDate.format(fmt);
        customerProductDatabase.readFromFile();
        if (!customerProductDatabase.contains(key)) {
            message("Error:", "Purchase record not found!");
            return -1;
        }

        if (!isWithinReturnPeriod(purchaseDate, returnDate)) {
            message("Error:", "Cannot return after more than 14 days!");
            return -1;
        }

        prod.setQuantity(prod.getQuantity() + 1);
        productsDatabase.saveToFile();

        customerProductDatabase.deleteRecord(key);
        customerProductDatabase.saveToFile();

        message("Success:", "Return completed successfully. Refunded: " + prod.getPrice());
        return prod.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        customerProductDatabase.readFromFile();

        for (CustomerProduct cp : customerProductDatabase.returnAllRecords()) {
            if (cp.getCustomerSSN().equals(customerSSN)
                    && cp.getPurchaseDate().equals(purchaseDate)) {

                if (!cp.isPaid()) {
                    cp.setPaid(true);
                    customerProductDatabase.saveToFile();
                    message("Success:", "Payment applied successfully!");
                    return true;
                } else {
                    message("Warning:", "This purchase is already paid!");
                    return false;
                }
            }
        }

        message("Error:", "No matching purchase found!");
        return false;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        message("Info:", "Changes saved successfully.");
    }

    private boolean isReturnDateValid(LocalDate purchaseDate, LocalDate returnDate) {
        return !returnDate.isBefore(purchaseDate);
    }

    private boolean isWithinReturnPeriod(LocalDate purchaseDate, LocalDate returnDate) {
        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        return days <= 14;
    }

    private void message(String type, String text) {
        System.out.println(type + " " + text);
    }
}
