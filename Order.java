import java.util.ArrayList;

enum Status {
    PENDING,
    COMPLETED,
    FAILED,
    CANCELLED
}
public class Order {
    private int orderId;
    private Cart cart;
    private Status status;
    private double totalCost;
    private PaymentMethod paymentMethod;

    public Order(){

    }
    
    public Order(int orderId, Cart cart, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.status = Status.PENDING;
        this.totalCost = calculateTotal(cart);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Total: " + totalCost + ", Payment: " + paymentMethod;
    }

    public void placeOrder() {
        if (paymentMethod.processPayment()) {
            this.status =Status.COMPLETED;
        } else {
            this.status = Status.FAILED;
        }
    }

    public void cancelOrder() {
        this.status = Status.CANCELLED;
    }
    private double calculateTotal(Cart cart) {
    double total = 0.0;
    for (Product product : cart.getProducts()) {
        total += product.getPrice(); // Accumulate the price of each product
    }
    return total;
}
    public void printOrderDetails() {
    System.out.println("Order ID: " + orderId);
    System.out.println("Total Cost: " + totalCost);
    System.out.println("Payment Method: " + paymentMethod);
    System.out.println("Items in Order:");

    for (Product product : cart.getProducts()) {
        System.out.println("- " + product.getName() + " (Price: " + product.getPrice() + ")");
    }
    }}
