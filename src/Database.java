
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database {

    private ArrayList<Record> records;
    private String filename;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public abstract Record createRecordFrom(String line);

    public void readFromFile() throws IOException {

        records.clear();
        File file = new File(filename + ".txt");
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Record x = createRecordFrom(line);
                records.add(x);
            }
        }

    }
    //method 3 ---> return all records

    public ArrayList<Record> returnAllRecords() {
        return new ArrayList<>(records);
    }

    //method 4 ---> check if a record with the given key exists
    public boolean contains(String key) {
        for (Record employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    //method 5 ---> get a record by key
    public Record getRecord(String key) {
        for (Record employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return employee;
            }
        }
        return null;
    }

    //method 6 ---> insert a new record
    public void insertRecord(Record record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Record with the same Employee ID already exists." + record.getSearchKey());
        }
    }

    //method 7 ---> delete a record by key
    public void deleteRecord(String key) {
        Record remove = null;
        for (Record employee : records) {
            if (employee.getSearchKey().equals(key)) {
                remove = employee;
                break;
            }
        }
        if (remove != null) {
            records.remove(remove);
            saveToFile();
        } else {
            System.out.println("Record with Employee ID " + key + " not found.");
        }

    }

    //method 8 ---> save records to file
    public void saveToFile() {

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filename + ".txt")) {
            for (Record employee : records) {
                fileWriter.write(employee.lineRepresentation() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
