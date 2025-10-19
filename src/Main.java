import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("EmployeeUser system test");
        Scanner input = new Scanner(System.in);
        EmployeeUserDatabase db = new EmployeeUserDatabase("Employees");
        try {
            db.readFromFile();
            System.out.println("Employee records loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading employee records: " + e.getMessage());
        }


        int choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("1. View all employees");
            System.out.println("2.Add new employee");
            System.out.println("3.Delete employee");
            System.out.println("4.Search employee by ID");
            System.out.println("5.Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Employee List:");
                    for (EmployeeUser emp : db.getRecords()) {
                        System.out.println(emp.lineRepresentation());
                    }
                    break;
                case 2:
                    System.out.println("Add New Employee:");
                    System.out.print("Enter Employee ID: ");
                    String id = input.nextLine();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    System.out.print("Enter Address: ");
                    String address = input.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = input.nextLine();
                    try{
                    EmployeeUser newEmp = new EmployeeUser(id, name, email, address, phone);
                    db.insertRecord(newEmp);
                    System.out.println("Employee added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding employee: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter Employee ID to delete: ");
                    String delId = input.nextLine();
                    try{
                    db.deleteRecord(delId);
                    System.out.println("Employee deleted successfully.");
                    }
                    catch (Exception e) {
                        System.out.println("Error deleting employee: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter Employee ID to search: ");
                    String searchId = input.nextLine();
                    EmployeeUser emp = db.getRecord(searchId);
                    if (emp != null) {
                        System.out.println("Employee Found: " + emp.lineRepresentation());
                    } else {
                        System.out.println("Employee with ID " + searchId + " not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            }
            while (choice!=5);
            input.close();



    }
}
