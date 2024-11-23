import java.util.List;

public class Venue {
    private String name;
    private Address address;
    private Menu menu;
    private List<Review> reviews;

    public Venue(String name, Address address, Menu menu, List<Review> reviews) {
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void displayVenueDetails() {
        System.out.println("Venue Name: " + name);
        System.out.println("Address: " + address.getHouseNumber() + " " + address.getCity() + " " + address.getStreet());
        System.out.println("Menu: ");
        for(Review review : reviews) {
            System.out.println("- " + review.getReviewerName() + ": " + review.getRating() + " stars");
        }
    }
}
