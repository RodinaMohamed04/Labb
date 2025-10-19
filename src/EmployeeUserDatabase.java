
// the list of EmployeeUser objects that hold the data read from the file
public class EmployeeUserDatabase extends Database {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    //method 2 ---> create EmployeeUser object from a line of text
    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid record format");
        }
        return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}
