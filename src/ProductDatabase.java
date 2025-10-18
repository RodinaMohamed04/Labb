
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDatabase {

    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase(String filename) {
        this.records = new ArrayList<>();
        this.filename = filename;
    }

    public void readFromFile() throws IOException {

        File file = new File(filename + ".txt");
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Product product = createRecordFrom(line);
                records.add(product);
            }
        }

    }

    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid record format");
        }
        int quantity = Integer.parseInt(parts[4]);
        float price = Float.parseFloat(parts[5]);
        return new Product(parts[0], parts[1], parts[2], parts[3], quantity, price);
    }

    public ArrayList<Product> returnAllRecords() {
        return new ArrayList<>(records);
    }

    public boolean contains(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Product getRecord(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return product;
            }
        }
        return null;
    }

    public void insertRecord(Product record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Record with the same Employee ID already exists." + record.getSearchKey());
        }
    }

    public void deleteRecord(String key) {
        Product remove = null;
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                remove = product;
                break;
            }
        }
        if (remove != null) {
            records.remove(remove);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Record with Employee ID " + key + " not found.");
        }

    }

    public void saveToFile() {

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filename + ".txt")) {
            for (Product product : records) {
                fileWriter.write(product.lineRepresentation() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        ProductDatabase db = new ProductDatabase("Products");
        try {
            db.readFromFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("--Existing Products--");
        for(Product prod : db.returnAllRecords()){
            System.out.println(prod.lineRepresentation());
        }
        Product newProduct = new Product ("P999","Tablet","Huawei","MobileHub",20,600.0f);
        if(!db.contains(newProduct.getSearchKey())){
            db.insertRecord(newProduct);
            System.out.println("new prod inserted:"+ newProduct.getProductID());
        }
        else{
           System.out.println("prod already exists:"+ newProduct.getProductID()); 
        }
        String searchID = "P8742";
        Product found = db.getRecord(searchID);
        if(found !=null)
            System.out.println("Product found "+ found.lineRepresentation());
        else System.out.println("Product not fount"+ searchID);
        String deleteID= "P1259";
        try {
            db.deleteRecord(deleteID);
            System.out.println("prod deleted"+deleteID );
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
         System.out.println("--Products After Changes--");
        for(Product prod : db.returnAllRecords()){
            System.out.println(prod.lineRepresentation());
        }
    }*/
}
