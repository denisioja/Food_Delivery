public class AddressCSVMapper implements CSVMapper<Address> {
    @Override
    public Address map(String[] fields) {
        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid CSV format for Address: Expected at least 3 fields but got " + fields.length);
        }

        String city = fields[0];
        String street = fields[1];
        String streetNumber = fields[2];

        return new Address(street, city, streetNumber); // Change to match Address constructor
    }

    public String toCSV(Address address) {
        return String.join(";", address.getCity(), address.getStreet(), address.getHouseNumber());
    }
}
