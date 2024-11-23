import java.time.LocalDateTime;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private LocalDateTime orderDateTime;
    private User user;
    private Venue venue;
    private Menu menu;
    private double totalPrice;
    private PaymentMethod paymentMethod;

    public Order(User user, Venue venue, PaymentMethod paymentMethod) {
        this.orderId = nextOrderId++;
        this.orderDateTime = LocalDateTime.now();
        this.user = user;
        this.venue = venue;
        this.menu = venue.getMenu();
        this.totalPrice = calculateTotalPrice();
        this.paymentMethod = paymentMethod;
    }

    private double calculateTotalPrice() {
        if (menu != null && menu.getFoodItems() != null)
            return menu.getFoodItems().stream().mapToDouble(FoodItem::getPrice).sum();
        return 0.0;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public User getUser() {
        return user;
    }

    public Venue getVenue() {
        return venue;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date and Time: " + orderDateTime);
        System.out.println("User: " + user.getName());
        System.out.println("Venue: " + venue.getName());
        System.out.println("Total price: $" + totalPrice);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Items Ordered: ");
        menu.displayMenuItems();
    }
}
