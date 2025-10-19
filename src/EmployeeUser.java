//represent an employee's personal information
public class EmployeeUser {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
      /*this.employeeId = employeeId; // Unique identifier for the employee
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;*/
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
        if(!email.contains("@")||!email.contains(".")) {
            throw new IllegalArgumentException("Invalid email address: " + email);
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
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phoneNumber = phoneNumber.trim();
       /* if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        phoneNumber = phoneNumber.trim();
        if (phoneNumber.matches("(\\+20|0)1\\d{9}")) {
          this.phoneNumber = phoneNumber;
        }
        else if (phoneNumber.matches("1\\d{9}"))
        {
            this.phoneNumber = "+20" + phoneNumber;
        }
        else
            throw  new IllegalArgumentException("Invalid Egyptian mobile number");*/
    }

    // Additional methods specific to EmployeeUser can be added here
    //method 1  returns the data of the employee comma separated
    public String lineRepresentation() {
        return getEmployeeId()+ "," + getName() + "," + getEmail() + "," + getAddress() + "," + getPhoneNumber();
    }

    //method 2 returns a string employee ID
    public String getSearchKey() {
        return getEmployeeId();  //unique Id for each employee
   }

}