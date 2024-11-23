import java.time.LocalDateTime;

public class Delivery {
    private Order order;
    private DeliveryPerson deliveryPerson;
    private Address deliveryAddress;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;//???

    public Delivery(Order order, DeliveryPerson deliveryPerson, Address deliveryAddress) {
        this.order = order;
        this.deliveryPerson = deliveryPerson;
        this.deliveryAddress = deliveryAddress;
        this.status = DeliveryStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.deliveredAt = LocalDateTime.now();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public void startDelivery() {
        if (this.status == DeliveryStatus.PENDING) {
            this.status = DeliveryStatus.IN_TRANSIT;
            System.out.println("Delivery is now in transit.");
        } else if (this.status == DeliveryStatus.CANCELED)
            System.out.println("Cannot start delivery. It has been canceled.");
        else System.out.println("Cannot start delivery. Current status: " + this.status);
    }

    public void deliver() {
        if (this.status == DeliveryStatus.IN_TRANSIT) {
            this.status = DeliveryStatus.DELIVERED;
            this.deliveredAt = LocalDateTime.now();
            System.out.println("Order delivered succsessfully!");
        } else if (this.status == DeliveryStatus.CANCELED)
            System.out.println("Cannot deliver. The delivery has been canceled.");
        else System.out.println("Cannot deliver. Current staus: " + this.status);
    }

    public void cancelDelivery() {
        if (this.status == DeliveryStatus.PENDING || this.status == DeliveryStatus.IN_TRANSIT) {
            this.status = DeliveryStatus.CANCELED;
            System.out.println("Delivery has been canceled.");
        } else if (this.status == DeliveryStatus.DELIVERED)
            System.out.println("Cannot cancel. The delivery has been already been completed.");
        else System.out.println("Cannot cancel. Current status: " + this.status);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "order= " + order +
                ", deliveryPerson=" + deliveryPerson.getName() +
                ", deliveryAddress=" + deliveryAddress +
                ", status=" + deliveryAddress +
                ", createdAt=" + createdAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }

    public void displayDeliveryDetails() {
        System.out.println("Delivery Details:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("User: " + order.getUser().getName());
        System.out.println("Venue: " + order.getVenue().getName());
        System.out.println("Delivery Person: " + deliveryPerson.getName());
        System.out.println("Delivery Address: " + deliveryAddress.getHouseNumber() + " " + deliveryAddress.getStreet() + ", " + deliveryAddress.getCity());
        System.out.println("Status: " + status);
        System.out.println("Order Created At: " + order.getOrderDateTime());
        System.out.println("Delivery Created At: " + createdAt);

        if (status == DeliveryStatus.DELIVERED) {
            System.out.println("Delivered At: " + deliveredAt);
        }

        System.out.println("Total Price: $" + order.getTotalPrice());
        System.out.println("Items Ordered:");
        order.getMenu().displayMenuItems();
    }
}
