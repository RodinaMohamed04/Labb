
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome To Our Retail Store.");
        System.out.println("Choose How to Login as: 1)Admin 2)Employee");
        int option = s.nextInt();
        s.nextLine();
        if (option == 1) {
            AdminRole a = new AdminRole();
            while (true) {
                System.out.println("Choose the Service You Want: 1)Add Employee..2)Get Employees List..3)Remove Employee..4)Logout:");
                int aOption = s.nextInt();
                s.nextLine();
                switch (aOption) {
                    case 1:
                        System.out.println("Enter New Employee ID:");
                        String ID = s.nextLine();
                        System.out.println("Enter New Employee Name:");
                        String Name = s.nextLine();
                        System.out.println("Enter New Employee Email:");
                        String Email = s.nextLine();
                        System.out.println("Enter New Employee Address:");
                        String Address = s.nextLine();
                        System.out.println("Enter New Employee Phone Number:");
                        String PhoneNum = s.nextLine();
                        a.addEmployee(ID, Name, Email, Address, PhoneNum);
                        System.out.println("New Employee Added Successfully !");
                        break;
                    case 2:
                        EmployeeUser[] employees = a.getListOfEmployees();
                        System.out.println("Employees List :");
                        System.out.println("--------------------------------");
                        for (EmployeeUser emp : employees) {
                            System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail() + " " + emp.getAddress() + " " + emp.getPhoneNumber());
                        }
                        break;

                    case 3:
                        System.out.println("Enter Employees's ID You Want to Remove:");
                        String id = s.nextLine();
                        a.removeEmployee(id);
                        System.out.println("Employee is Successfully Removed.");
                        break;
                    case 4:
                        System.out.println("Thank You for Using Our System.");
                        a.logout();
                        return;
                    default:
                        System.out.println("Invalid Option Please Choose from 1-4.");
                }

            }

        } else if (option == 2) {
            while (true) {
                try {
                    EmployeeRole ee = new EmployeeRole();

                    System.out.println("Choose the Service You Want: 1)Add Product..2)Get Products List..3)Get Purchase Operations List"
                            + "..4)Check If Product is in Stock..5)Return Product..6)Apply Payment..7)Logout:");
                    int eeOption = s.nextInt();
                    s.nextLine();
                    switch (eeOption) {
                        case 1:
                            System.out.println("Enter New Product ID:");
                            String ID = s.nextLine();
                            System.out.println("Enter New Product Name:");
                            String Name = s.nextLine();
                            System.out.println("Enter Manufacturer Name:");
                            String ManName = s.nextLine();
                            System.out.println("Enter Supplier Name:");
                            String SuppName = s.nextLine();
                            System.out.println("Enter Product Quantity:");
                            Integer Quantity = s.nextInt();
                            ee.addProduct(ID, Name, ManName, SuppName, Quantity);
                            System.out.println("New Product Added Successfully !");
                            break;
                        case 2:

                            Product[] prod = ee.getListOfProducts();
                            System.out.println("Products List :");
                            System.out.println("--------------------------------");

                            for (Product p : prod) {
                                System.out.println(p.getProductID() + " " + p.getProductName() + " " + p.getManufacturerName() + " " + p.getSupplierName() + " " + p.getQuantity());
                            }
                            break;

                        case 3:

                            CustomerProduct[] purchases = ee.getListOfPurchasingOperations();
                            System.out.println("List of Purchasing Operations :");
                            System.out.println("--------------------------------");
                            for (CustomerProduct c : purchases) {
                                System.out.println(c.getCustomerSSN() + " " + c.getProductID() + " " + c.getPurchaseDate());
                            }

                            break;
                        case 4:
                            System.out.println("Enter Customer SSN:");
                            String CusSSN = s.nextLine();
                            System.out.println("Enter Product ID:");
                            String prodID = s.nextLine();
                            System.out.println("Enter Purchase Date YYYY-MM-DD");
                            LocalDate purchDate = LocalDate.parse(s.nextLine());

                            if (ee.purchaseProduct(CusSSN, prodID, purchDate)) {
                                System.out.println("Product is in Stock.");
                            } else {
                                System.out.println("Product is Sold Out.");
                            }
                            break;
                        case 5:
                            System.out.println("Enter Customer SSN:");
                            String CUSsSN = s.nextLine();
                            System.out.println("Enter Product ID:");
                            String prodid = s.nextLine();
                            System.out.println("Enter Purchase Date YYYY-MM-DD");
                            LocalDate purDate = LocalDate.parse(s.nextLine());
                            System.out.println("Enter Return Date YYYY-MM-DD");
                            LocalDate RETDate = LocalDate.parse(s.nextLine());
                            if (ee.returnProduct(CUSsSN, prodid, purDate, RETDate) == -1) {
                                System.out.println("Error..Product is not Returned.");
                            } else {

                                System.out.println("Product is Returned Successfully.");
                            }
                            break;
                        case 6:
                            System.out.println("Enter Customer SSN:");
                            String CusSsn = s.nextLine();
                            System.out.println("Enter Purchase Date YYYY-MM-DD");
                            LocalDate purchaseDate = LocalDate.parse(s.nextLine());
                            if (ee.applyPayment(CusSsn, purchaseDate)) {
                                System.out.println("Payment Applied Successfully.");
                            } else {
                                System.out.println("Payment Rejected.");
                            }

                            break;

                        case 7:
                            System.out.println("Thank You for Using Our System.");
                            ee.logout();
                            return;
                        default:
                            System.out.println("Invalid Option Please Choose from 1-7.");

                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            System.out.println("Invalid Option..Please Choose 1 or 2");
        }
    }
}
