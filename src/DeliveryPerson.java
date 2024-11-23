import java.util.ArrayList;
import java.util.List;

public class DeliveryPerson extends Person{
    private String employeeId;
    private List<Delivery> deliveries;

    public DeliveryPerson(String name, String email, Address address, String employeeId) {//List<deliveries> poate sa nu fie pasat ca si parametru deoarece nu foloseste unul, ci atribuie o lista goala fiecarui angajat
        super(name, email, address);
        this.employeeId = employeeId;
        this.deliveries = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(Delivery delivery) {
        this.deliveries.remove(delivery);
    }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "name=" + getName() +
                ", email=" + getEmail() +
                ", address=" + getEmail() +
                ", employeeId=" + getEmployeeId() + '\'' +
                ", deliveries= " + deliveries.size() +
                '}';
      }
}
