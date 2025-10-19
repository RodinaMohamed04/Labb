
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomerProductDatabase extends Database {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //constructor
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    //method 2 ---> create a CustomerProduct object from a line of text
    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");
        String customerSSN = parts[0];
        String productID = parts[1];
        LocalDate date = LocalDate.parse(parts[2], formatter);
        boolean paid = Boolean.parseBoolean(parts[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, date);
        cp.setPaid(paid);
        return cp;
    }
}
