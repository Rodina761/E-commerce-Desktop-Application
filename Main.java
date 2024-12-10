import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize Database to ensure dummy data is loaded
        Database database = new Database();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nWelcome to the E-commerce platform!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            User currentUser = null;

            if (choice == 1) {
                // Registration
                currentUser = User.registerUser(scanner);
            } else if (choice == 2) {
                // Login
                currentUser = User.loginUser(scanner);
            } else if (choice == 3) {
                // Exit
                System.out.println("Exiting the application. Goodbye!");
                isRunning = false;
                continue;
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // If the user successfully logs in or registers
            if (currentUser != null) {
                if (currentUser instanceof Admin) {
                    // Admin functionality
                    Admin admin = (Admin) currentUser;
                    int adminChoice;

                    do {
                        System.out.println("\nAdmin Functions:");
                        System.out.println("1. Show all data");
                        System.out.println("2. Add a product");
                        System.out.println("3. Remove a product");
                        System.out.println("4. Update a product");
                        System.out.println("5. Logout");
                        System.out.println("6. Exit");
                        System.out.print("Choose an option: ");
                        adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (adminChoice) {
                            case 1:
                                // Show all data
                                admin.showAll(Database.getCustomers(), Database.getProducts(), Database.getOrders());
                                break;

                            case 2:
                                // Add a product
                                System.out.println("Enter product details (id, name, price, stock, category):");
                                int id = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String name = scanner.nextLine();
                                double price = scanner.nextDouble();
                                int stock = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String categoryName = scanner.nextLine();
                                Category category = Database.getCategories().stream()
                                        .filter(c -> c.getName().equalsIgnoreCase(categoryName))
                                        .findFirst()
                                        .orElse(null);
                                if (category != null) {
                                    Product newProduct = new Product(id, name, price, stock, category, 1);
                                    admin.addProduct(newProduct);
                                    Database.addProduct(newProduct);
                                    System.out.println("Product added successfully.");
                                } else {
                                    System.out.println("Category not found.");
                                }
                                break;

                            case 3:
                                // Remove a product
                                System.out.print("Enter product ID to remove: ");
                                int productIdToRemove = scanner.nextInt();
                                Product productToRemove = Database.getProducts().stream()
                                        .filter(p -> p.getId() == productIdToRemove)
                                        .findFirst()
                                        .orElse(null);
                                if (productToRemove != null) {
                                    admin.removeProduct(productToRemove);
                                    Database.removeProduct(productIdToRemove);
                                    System.out.println("Product removed successfully.");
                                } else {
                                    System.out.println("Product not found.");
                                }
                                break;

                            case 4:
                                // Update a product
                                System.out.println("Enter updated product details (id, name, price, stock):");
                                int updateId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String updateName = scanner.nextLine();
                                double updatePrice = scanner.nextDouble();
                                int updateStock = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                Category productCategory = null; // If updating category is required, add logic here
                                Product updatedProduct = new Product(updateId, updateName, updatePrice, updateStock, productCategory, 1);
                                admin.updateProduct(updatedProduct);
                                System.out.println("Product updated successfully.");
                                break;

                            case 5:
                                // Logout
                                System.out.println("Logging out...");
                                currentUser = null;
                                break;

                            case 6:
                                // Exit
                                System.out.println("Exiting the application. Goodbye!");
                                isRunning = false;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } while (adminChoice != 5 && isRunning);

                } else {
                    // Regular user functionality
                    int userChoice;
                    Cart cart = new Cart();

                    do {
                        System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
                        System.out.println("1. View Products");
                        System.out.println("2. Add to Cart");
                        System.out.println("3. View Cart");
                        System.out.println("4. Place Order");
                        System.out.println("5. Logout");
                        System.out.println("6. Exit");
                        System.out.print("Choose an option: ");
                        userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (userChoice) {
                            case 1:
                                // Display all categories and products
                                System.out.println("\nCategories and Products:");
                                for (Category category : Database.getCategories()) {
                                    System.out.println("Category: " + category.getName());
                                    category.viewProducts();
                                }
                                break;

                            case 2:
                                // Add product to cart
                                System.out.print("Enter product ID to add to cart: ");
                                int productId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                Product productToAdd = Database.getProducts().stream()
                                        .filter(p -> p.getId() == productId)
                                        .findFirst()
                                        .orElse(null);
                                if (productToAdd != null) {
                                    cart.addToCart(productToAdd);
                                    System.out.println("Product added to cart.");
                                } else {
                                    System.out.println("Product not found.");
                                }
                                break;

                            case 3:
                                // View cart
                                System.out.println("\nYour Cart:");
                                cart.viewCart();
                                break;

                            case 4:
                                // Place order
                                if (cart.getProducts().isEmpty()) {
                                    System.out.println("Your cart is empty. Add products to your cart before placing an order.");
                                } else {
                                    System.out.println("Placing your order...");
                                    double totalCost = cart.calculateTotal();
                                    CashPayment payment = new CashPayment(totalCost, 1000.0); // Example cash provided
                                    Order newOrder = new Order(Database.getOrders().size() + 1, cart, payment);
                                    Database.addOrder(newOrder);
                                    newOrder.placeOrder();
                                    newOrder.printOrderDetails();
                                    System.out.println("Order placed successfully.");
                                }
                                break;

                            case 5:
                                // Logout
                                System.out.println("Logging out...");
                                currentUser = null;
                                break;

                            case 6:
                                // Exit
                                System.out.println("Exiting the application. Goodbye!");
                                isRunning = false;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } while (userChoice != 5 && isRunning);
                }
            } else {
                System.out.println("\nReturning to the main menu...");
            }
        }

        scanner.close();
    }
}
