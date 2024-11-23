import java.util.*;

public class DeliveryService {
    private List<Delivery> deliveries;
    private AuditingService auditingService;

    public DeliveryService() {

        this.deliveries = new ArrayList<>();
        this.auditingService = AuditingService.getInstance();
    }

    public Delivery createDelivery(Order order, DeliveryPerson deliveryPerson, Address delieveryAddress) {
        Delivery delivery = new Delivery(order, deliveryPerson, delieveryAddress);
        deliveries.add(delivery);
        auditingService.logAction("Create delivery for Order ID: " + order.getOrderId());
        return delivery;
    }

    public void startDelivery(Delivery delivery) {

        delivery.startDelivery();
        auditingService.logAction("Started delivery for Order ID: " + delivery.getOrder().getOrderId());
    }

    public void completeDelivery(Delivery delivery) {

        delivery.deliver();
        auditingService.logAction("Complete delivery for Order ID: " + delivery.getOrder().getOrderId());
    }

    public void cancelDelivery(Delivery delivery) {

        delivery.cancelDelivery();
        auditingService.logAction("Cancel delivery for Order ID: " + delivery.getOrder().getOrderId());
    }

    public List<Delivery> getAllDeliveries() {
        return new ArrayList<>(deliveries);
    }

    public Delivery findDeliveryByOrder(Order order) {
        for (Delivery delivery : deliveries) {
            if (delivery.getOrder().equals(order)) {
                return delivery;
            }
        }
        return null;
    }

    public List<Delivery> getDeliveriesByDeliveryPerson(DeliveryPerson deliveryPerson) {
        List<Delivery> result = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            if(delivery.getDeliveryPerson().equals(deliveryPerson)) {
                result.add(delivery);
            }
        }
        return result;
    }

    public List<Delivery> getDeliveriesByStatus(DeliveryStatus status) {
        List<Delivery> result = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            if(delivery.getStatus() == status) {
                result.add(delivery);
            }
        }
        return result;
    }
}
