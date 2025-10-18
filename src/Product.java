public class Product {

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
        ////////law take 1 >>-1///law return 1>>+1///lw n==0>>sold out
       
        this.quantity = quantity;
    }

    public String lineRepresentation() {
        return getProductID() + "," + getProductName() + "," + getManufacturerName() + "," + getSupplierName() + "," + getQuantity() + "," + getPrice();
    }

    public String getSearchKey() {
        return getProductID();
    }
 /*public static void main(String[] args) {
        Product p = new Product("P1001","Laptop","Apple","Tradeline",10,1500.0f);
       

        System.out.println("ID: " + p.getProductID());
        System.out.println("Name: " + p.getProductName());
        System.out.println("Quantity: " + p.getQuantity());
        System.out.println("Line Representation: " + p.lineRepresentation());

        // simulate selling 1 product
        p.setQuantity(p.getQuantity() - 1);
        System.out.println("After selling one: " + p.getQuantity());}*/
    

    
}

    

