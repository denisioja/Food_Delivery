import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private List<Order> orders;
    private final String filePath = "data/orders.csv";
    private final CSVMapper<Order> orderMapper = new OrderCSVMapper();

    private OrderService() {
        loadOrders();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    private void loadOrders() {
        try {
            orders = CSVReader.getInstance().read(filePath, orderMapper, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(Order order) {
        orders.add(order);
        saveOrders();
        AuditingService.getInstance().logAction("Create Order");
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public void updateOrder(int orderId, Order updateOrder) {
        Order existingOrder = getOrderById(orderId);
        if (existingOrder != null) {
            existingOrder.setPaymentMethod(updateOrder.getPaymentMethod());
            saveOrders();
            AuditingService.getInstance().logAction("Update Order");
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    public void deleteOrder(int orderId) {
        Order orderToRemove = getOrderById(orderId);
        if (orderToRemove != null) {
            orders.remove(orderToRemove);
            saveOrders();
            AuditingService.getInstance().logAction("Delete Order");
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    public void saveOrders() {
        try {
            CSVWriter.getInstance().write(filePath, orders, orderMapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
