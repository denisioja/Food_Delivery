import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private String username;
    private String password;
    private List<Order> orders;

    public User(String name, String email, Address address, String username, String password, List<Order> orders) {
        super(name, email, address);
        this.username = username;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

}
