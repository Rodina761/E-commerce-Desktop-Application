import java.util.ArrayList;
public class Cart {
    private Customer customer;
    private ArrayList<Product> products ;

    
    public Cart() {
        this.products = new ArrayList<>();
    } 
    public void addToCart(Product product) {
        products.add(product);
    }

    public void removeFromCart(Product product) {
        products.remove(product);
    }

     public void viewCart() {
        System.out.println("Cart Contents:");
        for (Product product : products) {
            product.viewDetails();
        }
     }
        public ArrayList<Product> getProducts() {
        return products;
    }
    public double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice(); // Accumulate the price of each product
        }
        return total;
    }
}