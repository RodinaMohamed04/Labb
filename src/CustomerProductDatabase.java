import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records;
    private String filename;

    private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //constructor
    public CustomerProductDatabase(String filename) {
       /* this.filename = filename;
        this.records = new ArrayList<>();*/
        setFilename(filename);
        setRecords(new ArrayList<>());
    }

    //getters & setters
    public ArrayList<CustomerProduct> getRecords() {
        return records;
    }
    public void setRecords(ArrayList<CustomerProduct> records) {
        this.records = records;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    //method 1 ---> read customer product data from file and store in records
    public void readFromFile() throws IOException {
        /*records.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine())!= null) {
                records.add(createRecordFrom(line));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }*/
        File file = new File(filename+ ".txt");
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                CustomerProduct cp = createRecordFrom(line);
                records.add(cp);
            }
        }
    }

    //method 2 ---> create a CustomerProduct object from a line of text
    public CustomerProduct createRecordFrom(String line){
        String[] parts=line.split(",");
        String customerSSN=parts[0];
        String productID=parts[1];
        LocalDate date=LocalDate.parse(parts[2],formatter);
        boolean paid=Boolean.parseBoolean(parts[3]);
        CustomerProduct cp=new CustomerProduct(customerSSN, productID, date);
        cp.setPaid(paid);
        return cp;
    }
    //method 3 ---> return all records
    public ArrayList<CustomerProduct> returnAllRecords(){
        return records;
    }

    //method 4 ---> check if a record with the given key exists
    public boolean contains(String key){
        for (CustomerProduct cp : records){
            if(cp.getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    //method 5 ---> get a record by key
    public CustomerProduct getRecord(String key){
        for (CustomerProduct cp : records){
            if (cp.getSearchKey().equals(key)){
                return cp;
            }
        }
        return null;
    }

    //method 6 ---> insert a new record
    public void insertRecord(CustomerProduct record){
        if (!contains(record.getSearchKey())){
            records.add(record);
            saveToFile();
        }
        else {
            throw new IllegalArgumentException("Record with the same search key already exists."+ record.getSearchKey());
        }
    }

    //method 7 ---> delete a record by key
    public void deleteRecord(String key) {
       /* for (int i=0;i<records.size();i++){
            if (records.get(i).getSearchKey().equals(key)){
                records.remove(i);
                break;
            }
        }*/
        CustomerProduct remove = null;
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                remove = cp;
                break;
            }

        }
        if (remove != null) {
            records.remove(remove);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Record with key" + key + " not found.");
        }
    }
    //method 8 ---> save all records to file
    public void saveToFile(){
        try(java.io.FileWriter fileWriter=new java.io.FileWriter(filename + ".txt")) {
            for (CustomerProduct cp : records){
                fileWriter.write(cp.lineRepresentation()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}