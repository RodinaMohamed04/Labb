//the role of the admin
import java.util.ArrayList;

public class AdminRole {

    private EmployeeUserDatabase database; 

    // creates the database object and read employees from file
    public AdminRole() {
        database = new EmployeeUserDatabase("Employees");
        try {
            database.readFromFile(); 
        } catch (Exception e) {
            System.out.println("Error reading file."); // show error if file not found or empty
        }
    }
    // method 1: add a new employee
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser newEmp = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(newEmp); // add the employee to the list in the database
        System.out.println("Employee added successfully!");
    }

    // method 2: get list of all employees in the file
    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> list = database.returnAllRecords();  // get all employees from the database (ArrayList)
        EmployeeUser[] array = new EmployeeUser[list.size()];
        array = list.toArray(array); // convert ArrayList to array
        return array;
    }

    // method 3: remove employee using employee id
    public void removeEmployee(String key) {
        database.deleteRecord(key);
        System.out.println("Employee removed successfully!");
    }

    // method 4: save all changes to file (logout)
    public void logout() {
        try {
            database.saveToFile(); 
            System.out.println("All data saved successfully. Logging out...");
        } catch (Exception e) {
            System.out.println("Error saving data to file.");
        }
    }
}