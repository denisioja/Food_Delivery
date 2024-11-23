////import java.util.ArrayList;
////import java.util.Arrays;
////
////public class UserCSVMapper implements CSVMapper<User> {
////
////    private AddressCSVMapper addressCSVMapper = new AddressCSVMapper();
////
////    @Override
////    public User map(String[] fields) {
////
////        if (fields.length < 7) { // Ensure there are enough fields
////            throw new IllegalArgumentException("Invalid CSV format for User: " + Arrays.toString(fields));
////        }
////
////        String name = fields[0];
////        String email = fields[1];
////
////        Address address = addressCSVMapper.map(new String[]{fields[2], fields[3], fields[4]});
////        String username = fields[5];
////        String password = fields[6];
////
////        return new User(name, email, address, username, password, new ArrayList<>());
////    }
////
////    @Override
////    public String toCSV(User user) {
////        return String.join(",",
////                user.getName(),
////                user.getEmail(),
////                user.getAddress().toString(),
////                user.getUsername(),
////                user.getPassword()
////        );
////    }
////}
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class UserCSVMapper implements CSVMapper<User> {
//    // Instance of AddressCSVMapper to use its non-static methods
//    private AddressCSVMapper addressCSVMapper = new AddressCSVMapper();
//
//    @Override
//    public User map(String[] fields) {
//        // Debugging output
//        System.out.println("Fields received for mapping User: " + Arrays.toString(fields));
//
//        if (fields.length < 7) { // Ensure there are enough fields for a User
//            throw new IllegalArgumentException("Invalid CSV format for User: " + Arrays.toString(fields));
//        }
//
//        String name = fields[0];
//        String email = fields[1];
//
//        // Use the instance method to parse the address fields
//        Address address = addressCSVMapper.map(new String[]{fields[2], fields[3], fields[4]});
//
//        String username = fields[5];
//        String password = fields[6];
//
//        return new User(name, email, address, username, password, new ArrayList<>());
//    }
//
//    @Override
//    public String toCSV(User user) {
//        return String.join(",",
//                user.getName(),
//                user.getEmail(),
//                addressCSVMapper.toCSV(user.getAddress()), // Using the instance toCSV method
//                user.getUsername(),
//                user.getPassword()
//        );
//    }
//}
//
import java.util.ArrayList;
import java.util.List;

public class UserCSVMapper implements CSVMapper<User> {

    @Override
    public User map(String[] fields) {
        System.out.println("Fields received for mapping: " + java.util.Arrays.toString(fields));

        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid CSV format for User: Expected 7 fields but got " + fields.length + ". Fields: " + java.util.Arrays.toString(fields));
        }

        try {
            // Extract and trim each field
            String name = fields[0].trim();
            String email = fields[1].trim();
            String city = fields[2].trim();
            String street = fields[3].trim();
            String houseNumber = fields[4].trim();
            String username = fields[5].trim();
            String password = fields[6].trim();

            if (name.isEmpty() || email.isEmpty() || city.isEmpty() || street.isEmpty() || houseNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Invalid CSV format for User: Some fields are empty. Fields: " + java.util.Arrays.toString(fields));
            }

            Address address = new Address(street, city, houseNumber);

            List<Order> orders = new ArrayList<>();

            User user = new User(name, email, address, username, password, orders);

            System.out.println("User Mapped - Name: " + name + ", Email: " + email + ", Address: " + address + ", Username: " + username);
            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid CSV format for User: " + java.util.Arrays.toString(fields), e);
        }
    }

    @Override
    public String toCSV(User user) {
        return String.join(";",
                user.getName(),
                user.getEmail(),
                user.getAddress().getCity(),
                user.getAddress().getStreet(),
                user.getAddress().getHouseNumber(),
                user.getUsername(),
                user.getPassword()
        );
    }
}



