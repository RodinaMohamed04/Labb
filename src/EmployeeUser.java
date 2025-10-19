//represent an employee's personal information

public class EmployeeUser implements Record {

    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {

        setEmployeeId(employeeId);
        setName(name);
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;

    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {

        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{11}")) {

        }
        this.phoneNumber = phoneNumber.trim();

    }

    //method 1  returns the data of the employee comma separated
    @Override
    public String lineRepresentation() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getAddress() + "," + getPhoneNumber();
    }

    //method 2 returns a string employee ID
    @Override
    public String getSearchKey() {
        return getEmployeeId();  //unique Id for each employee
    }

}
