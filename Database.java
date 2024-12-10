import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void removeProduct(int productId) {
        categories.remove(productId);
    }

    // Constructor
    public Database() {
        // Default constructor
    }

    // Encapsulation: Provide public methods for accessing and modifying data
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    // Initialize dummy data
    static {
        initializeDummyData();
    }

    private static void initializeDummyData() {
        // Create Categories
        Category electronics = new Category("Electronics", "Electronic gadgets and devices");
        Category books = new Category("Books", "Books of various genres");
        addCategory(electronics);
        addCategory(books);

        // Create Products
        Product phone = new Product(1, "Smartphone", 999.99, 10, electronics, 1);
        Product laptop = new Product(2, "Laptop", 1299.99, 5, electronics, 1);
        Product novel = new Product(3, "Novel", 19.99, 20, books, 1);
        addProduct(phone);
        addProduct(laptop);
        addProduct(novel);

        // Add products to categories
        electronics.addProduct(phone);
        electronics.addProduct(laptop);
        books.addProduct(novel);

        // Create Customers
        Customer customer1 = new Customer("Menna", "password123", new Date(), 50000.0, "123 Main St", Gender.FEMALE, new String[]{"Tech", "Make-up"});
        Customer customer2 = new Customer("Basmala", "password123", new Date(), 15000.0, "123 Main Street", Gender.FEMALE, new String[]{"Books"});
        addCustomer(customer1);
        addCustomer(customer2);

        // Create Admins
        Admin admin1 = new Admin("Rodina", "pass123", new Date(), "Manager", 8);
        addAdmin(admin1);

        // Dummy Orders
        Cart cart1 = new Cart();
        cart1.addToCart(phone);
        cart1.addToCart(novel);
        Order order1 = new Order(1, cart1, new CashPayment(1019.98, 2000.0));
        addOrder(order1);
    }
}
