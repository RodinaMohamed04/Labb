import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//responsible for accessing the file of the employees (reading from and writing to), accessing,
// and manipulating (adding to and removing from)
// the list of EmployeeUser objects that hold the data read from the file

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    //constructor
    public EmployeeUserDatabase(String filename) {
       /* this.filename = filename;
        this.records = new ArrayList<>();*/
        setFilename(filename);
        setRecords(new ArrayList<>());
    }

    //getter for records
    public ArrayList<EmployeeUser> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<EmployeeUser> records) {
        this.records = records;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    //method 1 ---> read employee data from file and store in records
    public void readFromFile() throws IOException {
      /*  File file = new File(filename + ".txt");
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            EmployeeUser employee = createRecordFrom(line);
            records.add(employee);
        }
        scan.close();*/
        records.clear();
        File file = new File(filename+ ".txt");
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                EmployeeUser employee = createRecordFrom(line);
                records.add(employee);
            }
        }

    }

    //method 2 ---> create EmployeeUser object from a line of text
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid record format");
        }
        return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
    //method 3 ---> return all records
    public ArrayList<EmployeeUser> returnAllRecords() {
        return new ArrayList<>(records);
    }
    //method 4 ---> check if a record with the given key exists
    public boolean contains(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    //method 5 ---> get a record by key
    public EmployeeUser getRecord(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return employee;
            }
        }
        return null;
    }
    //method 6 ---> insert a new record
    public void insertRecord(EmployeeUser record) {
        if (!contains(record.getSearchKey())){
            records.add(record);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Record with the same Employee ID already exists." + record.getSearchKey());
        }
    }
    //method 7 ---> delete a record by key
    public void deleteRecord(String key) {
        EmployeeUser remove = null;
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                remove = employee;
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
    //method 8 ---> save records to file
    public void saveToFile() {
       /*java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter(filename + ".txt");
            for (EmployeeUser employee : records) {
                String line = employee.getEmployeeId() + "," +
                        employee.getName() + "," +
                        employee.getEmail() + "," +
                        employee.getAddress() + "," +
                        employee.getPhoneNumber() + "\n";
                fileWriter.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filename + ".txt")) {
            for (EmployeeUser employee : records) {
               fileWriter.write(employee.lineRepresentation() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
