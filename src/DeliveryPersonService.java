import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersonService {
    private static DeliveryPersonService instance;
    private final String filePath = "data/delivery_persons.csv";
    private List<DeliveryPerson> deliveryPersons;
    private final CSVMapper<DeliveryPerson> mapper = new DeliveryPersonCSVMapper();

    private DeliveryPersonService() {
        loadDeliveryPersons();
    }

    public static DeliveryPersonService getInstance() {
        if (instance == null) {
            instance = new DeliveryPersonService();
        }
        return instance;
    }

    private void loadDeliveryPersons() {
        try {
            deliveryPersons = CSVReader.getInstance().read(filePath, mapper, ",");
            AuditingService.getInstance().logAction("Loaded delivery persons from file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeliveryPerson(DeliveryPerson deliveryPerson) {
        deliveryPersons.add(deliveryPerson);
        saveDeliveryPersons();
        AuditingService.getInstance().logAction("Added new delivery person: " + deliveryPerson.getName());
    }

    public DeliveryPerson findDeliveryPersonById(String employeeId) {
        AuditingService.getInstance().logAction("Searching for delivery person by ID: " + employeeId);
        for (DeliveryPerson deliveryPerson : deliveryPersons) {
            if (deliveryPerson.getEmployeeId().equalsIgnoreCase(employeeId)) {
                return deliveryPerson;
            }
        }
        return null;
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        AuditingService.getInstance().logAction("Retrive all delivery persons");
        return new ArrayList<>(deliveryPersons);
    }

    private void saveDeliveryPersons() {
        try {
            CSVWriter.getInstance().write(filePath, deliveryPersons, mapper);
            AuditingService.getInstance().logAction("Saved delivery persons to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
