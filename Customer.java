import java.util.ArrayList;
import java.util.Date;


// Define the Gender enum
 enum Gender {
    MALE,
    FEMALE,
}
// Customer class extending the User class
public class Customer extends User {
    private double balance;
    private String address;
    private Gender gender; // Using the Gender enum
    private String[] interests;

    // Constructor
    public Customer(){

    }

    // Constructor
    public Customer(String username, String password, Date dateOfBirth, double balance, String address, Gender gender, String[] interests) {
        super(username, password, dateOfBirth); // Call the superclass constructor
        this.balance = balance;
        this.address = address;
        this.gender = gender;
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "Customer: " + username + " (Balance: " + balance + ")";
    }
    
    // Getter for gender
    public Gender getGender() {
        return gender;
    }

    // Setter for gender
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Method to view cart
    public void viewCart() {
        System.out.println("Viewing cart for customer: " + this.getUsername());
        // Logic to view cart
    }

    // Method to view orders
    public void viewOrders() {
        System.out.println("Viewing orders for customer: " + this.getUsername());
        // Logic to view orders
    }
   
    public void displayProfile() {
        System.out.println("Customer Profile");
        System.out.println("Username: " + username);
        System.out.println("Balance: " + balance);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
        System.out.println("Interests: " + interests);
    }
}


