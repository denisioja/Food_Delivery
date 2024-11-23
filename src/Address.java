public class Address {
    private String street;
    private String city;
    private String houseNumber;

    public Address(String street, String city, String houseNumber) {
        if (street == null || street.isEmpty()){
            throw new InvalidAddressException("Street name cannot be empty.");
        }
        if (city == null || city.isEmpty()) {
            throw new InvalidAddressException("City name cannot be empty.");
        }
        if (houseNumber == null || houseNumber.isEmpty()) {
            throw new InvalidAddressException("House number cannot be empty.");
        }

        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null || street.isEmpty()) {
            throw new InvalidAddressException("Street name cannot be empty.");
        }
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        if (city == null || city.isEmpty()) {
            throw new InvalidAddressException("City name cannot be empty.");
        }
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        if (houseNumber == null || houseNumber.isEmpty()) {
            throw new InvalidAddressException("House number cannot be empty.");
        }
        this.houseNumber = houseNumber;
    }
}
