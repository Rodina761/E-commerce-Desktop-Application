public class CashPayment implements PaymentMethod {
    private double totalCost; // The cost of the order
    private double cashProvided; // The cash provided by the customer

    // Constructor to initialize total cost and cash provided
    public CashPayment(double totalCost, double cashProvided) {
        this.totalCost = totalCost;
        this.cashProvided = cashProvided;
    }

    @Override
    public boolean processPayment() {
        // Simulate cash payment validation
        if (cashProvided >= totalCost) {
            double change = cashProvided - totalCost;
            System.out.println("Cash Payment processed successfully.");
            System.out.println("Change to be returned: $" + change);
            return true;
        } else {
            System.out.println("Cash Payment failed. Insufficient cash provided.");
            return false;
        }
    }
}