public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(){

    }
    
    // Constructor to initialize credit card details
    public CreditCardPayment(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment() {
        // Simulate credit card validation
        if (validateCardDetails()) {
            System.out.println("Credit Card Payment processed successfully.");
            return true;
        } else {
            System.out.println("Credit Card Payment failed. Invalid card details.");
            return false;
        }
    }
        
        private boolean validateCardDetails() {
        // Example validation logic (replace with actual validation in real systems)
        return cardNumber.length() == 16 && cvv.length() == 3 && !cardHolderName.isEmpty();
    }
        
        
}


