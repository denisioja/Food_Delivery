import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        OrderService orderService = OrderService.getInstance();
        VenueService venueService = VenueService.getInstance();
        DeliveryService deliveryService = new DeliveryService();
        DeliveryPersonService deliveryPersonService = DeliveryPersonService.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("==== Menu ====");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Create Order");
            System.out.println("4. View All Order");
            System.out.println("5. Create Venue");
            System.out.println("6. View All Venues");
            System.out.println("7. Create Delivery");
            System.out.println("8. Start Delivery");
            System.out.println("9. Complete Delivery");
            System.out.println("10. Cancel Delivery");
            System.out.println("11. View All Deliveries");
            System.out.println("12. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUser(scanner, userService);
                    break;
                case 2:
                    viewAllUsers(userService);
                    break;
                case 3:
                    createOrder(scanner, orderService, userService, venueService);
                    break;
                case 4:
                    viewAllOrders(orderService);
                    break;
                case 5:
                    createVenue(scanner, venueService);
                    break;
                case 6:
                    viewAllVenues(venueService);
                    break;
                case 7:
                    createDelivery(scanner, deliveryService, orderService, userService, deliveryPersonService);
                    break;
                case 8:
                    startDelivery(scanner, deliveryService, orderService);
                    break;
                case 9:
                    completeDelivery(scanner, deliveryService ,orderService);
                    break;
                case 10:
                    cancelDelivery(scanner, deliveryService, orderService);
                    break;
                case 11:
                    viewAllDelivery(deliveryService);
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void createUser(Scanner scanner, UserService userService) {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter address (city, street, house number): ");
        String city = scanner.nextLine();
        String street = scanner.nextLine();
        String houseNumber = scanner.nextLine();
        Address address = new Address(city, street, houseNumber);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(name, email, address, username, password, new ArrayList<>());
        userService.createUser(user);
        System.out.println("User created succesfully.");
    }

    private static void viewAllUsers(UserService userService) {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.getUsername() + ": " + user.getName() + " (" + user.getEmail() + ")");
        }
    }

    private static void createOrder(Scanner scanner, OrderService orderService, UserService userService, VenueService venueService) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Enter venue name: ");
        String venueName = scanner.nextLine();
        Venue venue = venueService.getVenueByName(venueName);
        if (venue == null) {
            System.out.println("Venue not found.");
            return;
        }

        System.out.println("Enter payment method: ");
        String paymentMethodStr = scanner.nextLine();
        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentMethodStr.toUpperCase());

        Order order = new Order(user, venue, paymentMethod);
        orderService.createOrder(order);
        System.out.println("Order created succesfully.");
    }

    private static void viewAllOrders(OrderService orderService) {
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() + " | User: " + order.getUser().getUsername() + " | Venue: " + order.getVenue().getName());
        }
    }

    public static void createVenue(Scanner scanner, VenueService venueService) {
        System.out.println("Enter venue name: ");
        String name = scanner.nextLine();
        System.out.println("Enter address (city, street, house number): ");
        String city = scanner.nextLine();
        String street = scanner.nextLine();
        String houseNumber = scanner.nextLine();
        Address address = new Address(city, street, houseNumber);

        Menu menu = new Menu(new ArrayList<>());
        List<Review> reviews = new ArrayList<>();

        Venue venue = new Venue(name, address, menu, reviews);
        venueService.createVenue(venue);
        System.out.println("Venue created succesfully.");
    }

    private static void viewAllVenues(VenueService venueService) {
        List<Venue> venues = venueService.getVenues();
        for (Venue venue : venues) {
            System.out.println("Venue: " + venue.getName() + " | Address: " + venue.getAddress().getCity() + ", " + venue.getAddress().getStreet());
        }
    }

    private static void createDelivery(Scanner scanner, DeliveryService deliveryService, OrderService orderService, UserService userService, DeliveryPersonService deliveryPersonService) {
        System.out.println("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found");
            return;
        }

        System.out.println("Enter Delivery Person ID: ");
        String deliveryPersonId = scanner.nextLine();
        DeliveryPerson deliveryPerson = deliveryPersonService.findDeliveryPersonById(deliveryPersonId);


        System.out.println("Enter Delivery Address (city, street, house number): ");
        String city = scanner.nextLine();
        String street = scanner.nextLine();
        String houseNumber = scanner.nextLine();
        Address deliveryAddress = new Address(city, street, houseNumber);

        Delivery delivery = deliveryService.createDelivery(order, deliveryPerson, deliveryAddress);
        System.out.println("Delivery created: " + delivery);

    }

    private static void startDelivery(Scanner scanner, DeliveryService deliveryService, OrderService orderService) {
        System.out.println("Enter Order ID to start delivery: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderService.getOrderById(orderId);
        Delivery delivery = deliveryService.findDeliveryByOrder(order);
        if (delivery != null) {
            deliveryService.startDelivery(delivery);
            System.out.println("Delivery start.");
        } else System.out.println("Delivery not found.");
    }

    private static void completeDelivery(Scanner scanner, DeliveryService deliveryService, OrderService orderService) {
        System.out.println("Enter Order ID to complete delivery: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderService.getOrderById(orderId);
        Delivery delivery = deliveryService.findDeliveryByOrder(order);
        if (delivery != null) {
            deliveryService.completeDelivery(delivery);
            System.out.println("Delivery completed.");
        } else System.out.println("Delivery not found");
    }

    private static void cancelDelivery(Scanner scanner, DeliveryService deliveryService, OrderService orderService) {
        System.out.println("Enter Order ID to cancel delivery: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderService.getOrderById(orderId);
        Delivery delivery = deliveryService.findDeliveryByOrder(order);
        if (delivery != null) {
            deliveryService.cancelDelivery(delivery);
            System.out.println("Delivery canceled.");
        } else System.out.println("Delivery not found");
    }

    private static void viewAllDelivery(DeliveryService deliveryService) {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
         for (Delivery delivery : deliveries) {
             System.out.println("Delivery for Order ID: " + delivery.getOrder().getOrderId() +
                     " | Delivery Person: " + delivery.getDeliveryPerson().getName() +
                     " | Status: " + delivery.getStatus());
         }
    }

 }

