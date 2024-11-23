//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class OrderCSVMapper implements CSVMapper<Order> {
//    @Override
//    public Order map(String[] fields) {
//        int orderId = Integer.parseInt(fields[0]);
//        LocalDateTime orderDateTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME);
//        User user = new UserCSVMapper().map(fields[2].split(";"));
//        Venue venue = new VenueCSVMapper().map(fields[3].split(";"));
//        PaymentMethod paymentMethod = PaymentMethod.valueOf(fields[4]);
//
//        return new Order(user, venue, paymentMethod);
//    }
//
//    public String toCSV(Order order) {
//        return String.join(",",
//                String.valueOf(order.getOrderId()),
//                order.getOrderDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
//                new UserCSVMapper().toCSV(order.getUser()),
//                new VenueCSVMapper().toCSV(order.getVenue()),
//                order.getPaymentMethod().toString()
//        );
//    }
//}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderCSVMapper implements CSVMapper<Order> {
    private UserCSVMapper userMapper = new UserCSVMapper();
    private VenueCSVMapper venueMapper = new VenueCSVMapper();

    @Override
    public Order map(String[] fields) {
        if (fields.length < 5) {
            throw new IllegalArgumentException("Invalid CSV format for Order: Expected at least 5 fields but got " + fields.length);
        }

        int orderId;
        try {
            orderId = Integer.parseInt(fields[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid order ID format: " + fields[0], e);
        }

        LocalDateTime orderDateTime;
        try {
            orderDateTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid order date-time format: " + fields[1], e);
        }

        String[] userParts = fields[2].split(";");
        if (userParts.length < 7) {
            throw new IllegalArgumentException("Invalid CSV format for User within Order: Expected at least 7 fields but got " + userParts.length);
        }
        User user = userMapper.map(userParts);

        String[] venueParts = fields[3].split(";");
        if (venueParts.length < 4) {
            throw new IllegalArgumentException("Invalid CSV format for Venue within Order: Expected at least 4 fields but got " + venueParts.length);
        }
        Venue venue = venueMapper.map(venueParts);

        PaymentMethod paymentMethod;
        try {
            paymentMethod = PaymentMethod.valueOf(fields[4].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment method format: " + fields[4], e);
        }

        Order order = new Order(user, venue, paymentMethod);
        return order;
    }

    public String toCSV(Order order) {
        String userCSV = userMapper.toCSV(order.getUser());
        String venueCSV = venueMapper.toCSV(order.getVenue());
        return String.join(",",
                String.valueOf(order.getOrderId()),
                order.getOrderDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                userCSV,
                venueCSV,
                order.getPaymentMethod().name()
        );
    }
}



