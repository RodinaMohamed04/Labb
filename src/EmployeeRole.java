import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() throws IOException {
        productsDatabase = new ProductDatabase("Products");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts");
        try {
            productsDatabase.readFromFile();
            customerProductDatabase.readFromFile();
        } catch (Exception e) {
            message("Error:", "Failed to read databases: " + e.getMessage());
        }
       // productsDatabase.readFromFile();
       // customerProductDatabase.readFromFile();
    }

    // method--->1  to add a new product in file products.txt
    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity) {
        if (productsDatabase.getRecord(productID) != null) {
            message("Warning:", "Product already exists!");
            return;
        }

        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0F);
        productsDatabase.insertRecord(p);
        productsDatabase.saveToFile();
        message("Success:", "Product added successfully!");
    }
    // method--->2 to get list of all products in the file products.txt
    public Product[] getListOfProducts() throws IOException {
        productsDatabase.readFromFile();
        ArrayList<Product> all = productsDatabase.returnAllRecords();
        return all.toArray(new Product[0]);
    }
    // method--->3 to get list of all purchasing operations in the file CustomersProducts.txt
    public CustomerProduct[] getListOfPurchasingOperations() throws IOException {
       customerProductDatabase.readFromFile();
        ArrayList<CustomerProduct> all = customerProductDatabase.returnAllRecords();
        return all.toArray(new CustomerProduct[0]);

    }
    // method--->4 to purchase a product
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) throws IOException {
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
    // method--->5 to return a product
    public double returnProduct(String customerSSN, String productID,
                                LocalDate purchaseDate, LocalDate returnDate) throws IOException {

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

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) throws IOException {
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
