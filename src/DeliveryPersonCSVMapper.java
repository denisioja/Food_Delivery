public class DeliveryPersonCSVMapper implements CSVMapper<DeliveryPerson> {
    @Override
    public DeliveryPerson map(String[] fields) {
        String name = fields[0];
        String email = fields[1];
        String city = fields[2];
        String street = fields[3];
        String houseNumber = fields[4];
        String employeeId = fields[5];

        Address address = new Address(city, street, houseNumber);
        return new DeliveryPerson(name, email, address, employeeId);
    }

    public String toCSV(DeliveryPerson deliveryPerson) {
        return String.join(",",
                    deliveryPerson.getName(),
                    deliveryPerson.getEmail(),
                    deliveryPerson.getAddress().getCity(),
                    deliveryPerson.getAddress().getStreet(),
                    deliveryPerson.getAddress().getHouseNumber(),
                    deliveryPerson.getEmployeeId()
        );
    }
}
