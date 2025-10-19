
public class Product implements Record {

    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public float getPrice() {
        return price;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    @Override
    public String lineRepresentation() {
        return getProductID() + "," + getProductName() + "," + getManufacturerName() + "," + getSupplierName() + "," + getQuantity() + "," + getPrice();
    }

    @Override
    public String getSearchKey() {
        return getProductID();
    }

}
