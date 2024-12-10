import java.util.List;
import java.util.Date;

public class Admin extends User {
    private String role;
    private int workingHours;
    // Constructor
    public Admin(){

    }
    // Constructor
    public Admin(String username, String password, Date dateOfBirth, String role, int workingHours) {
        super(username, password, dateOfBirth);
        this.role = role;
        this.workingHours = workingHours;
    }

    // Show all customers, products, and orders
    public void showAll(List<Customer> customers, List<Product> products, List<Order> orders) {
        System.out.println("All Customers:");
        for (Customer c : customers) {
            c.displayProfile();
        }
        System.out.println("\nAll Products:");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("\nAll Orders:");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // Add a product to the database
    public void addProduct(Product product) {
        Database.getProducts().add(product);
    }

    // Remove a product from the database
    public void removeProduct(Product product) {
        Database.getProducts().remove(product);
    }

    // Update a product in the database
    public void updateProduct(Product updatedProduct) {
        boolean productFound = false;
        for (Product product : Database.getProducts()) {
            if (product.getId() == updatedProduct.getId()) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setStock(updatedProduct.getStock());
                System.out.println("Product updated successfully:");
                System.out.println(product);
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            System.out.println("Product not found with ID: " + updatedProduct.getId());
        }
    }
    
    @Override
    public void displayProfile() {
        System.out.println("Admin Profile:");
        System.out.println("Username: " + username);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Role: " + role);
        System.out.println("Working Hours: " + workingHours);
    }

    // Override the toString() method to include the 'role' field
    @Override
    public String toString() {
        return "Admin: " + username + " Role: " + role;
    }

    // Getter methods for username and password for login validation
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
