
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDatabase extends Database{


    public ProductDatabase(String filename) {
      super(filename);
    }
   
    //method to create Product object from a line in the file
    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid record format");
        }
        int quantity = Integer.parseInt(parts[4]);
        float price = Float.parseFloat(parts[5]);
        return new Product(parts[0], parts[1], parts[2], parts[3], quantity, price);
    }
}
