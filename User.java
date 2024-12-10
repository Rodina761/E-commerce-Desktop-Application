import java.util.Date;
import java.util.Scanner;

public abstract class User implements Comparable<User>{
    protected String username;
    protected String password;
    protected Date dateOfBirth;

    protected User(){

    }
    protected User(String username, String password, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public abstract void displayProfile();
    
    public static User registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Check if the username and password already exist in the database
        boolean isDuplicate = Database.getCustomers().stream()
            .anyMatch(customer -> customer.compareTo(new Customer(username, password, new Date(), 0.0, "", Gender.MALE, new String[]{})) == 0);

        // If duplicate found, return an error message and prevent registration
        if (isDuplicate) {
            System.out.println("Error: User with the same username and password already exists.");
            return null;
        }

        // No duplicate found, proceed with creating a new customer
        Customer newCustomer = new Customer(username, password, new Date(), 0.0, "", Gender.MALE, new String[]{});
        Database.addCustomer(newCustomer); // Add customer to the database
        System.out.println("Registration successful.");
        return newCustomer;
    }
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public static User loginUser(Scanner scanner) {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    // Search for matching customer
    User user = Database.getCustomers().stream()
            .filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
            .findFirst()
            .orElse(null);

    // If not a customer, search for matching admin
    if (user == null) {
        user = Database.getAdmins().stream()
                .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Handle login result
    if (user != null) {
        if (user instanceof Admin) {
            System.out.println("Admin login successful.");
        } else {
            System.out.println("Customer login successful.");
        }
        return user;
    } else {
        System.out.println("Invalid username or password.");
        return null;
    }
    }

    public void logout() {
        System.out.println(username + " has logged out.");
    }
    @Override
    public int compareTo(User u)
    {
        if(username.equals(u.username) && password.equals(u.password))
            {
                return 0;
            }
        else
            {
                return -1;
            }
    }
}

