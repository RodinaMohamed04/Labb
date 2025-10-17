import java.io.FileReader;
import java.util.ArrayList;
//responsible for accessing the file of the employees (reading from and writing to), accessing,
// and manipulating (adding to and removing from)
// the list of EmployeeUser objects that hold the data read from the file

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    //constructor
    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
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

    //method 1
    public void readFromFile() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
            java.util.Scanner scanner = new java.util.Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                EmployeeUser employee = createRecordFrom(line);
                records.add(employee);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid record format");
        }
        return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public ArrayList<EmployeeUser> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getEmployeeId().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getEmployeeId().equals(key)) {
                return employee;
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.removeIf(employee -> employee.getEmployeeId().equals(key));
    }

    public void saveToFile() {
        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter(filename);
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
    }
}
