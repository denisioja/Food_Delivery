import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VenueCSVMapper implements CSVMapper<Venue> {
    private AddressCSVMapper addressMapper = new AddressCSVMapper();
    private ReviewCSVMapper reviewMapper = new ReviewCSVMapper();

    @Override
    public Venue map(String[] fields) {
        if (fields.length < 4) {
            throw new IllegalArgumentException("Invalid CSV format for Venue: Expected at least 4 fields but got " + fields.length);
        }

        String name = fields[0];
        String[] addressParts = fields[1].split(";");
        System.out.println("Address Parts: " + Arrays.toString(addressParts));
        if (addressParts.length < 3) {
            throw new IllegalArgumentException("Invalid CSV format for Address within Venue: Expected at least 3 fields but got " + addressParts.length);
        }
        Address address = addressMapper.map(addressParts);

        String[] foodItemsData = fields[2].split(";");
        List<FoodItem> foodItems = new ArrayList<>();
        for (String foodItemData : foodItemsData) {
            String[] foodItemParts = foodItemData.split(":");
            if (foodItemParts.length == 2) {
                String itemName = foodItemParts[0];
                double price = Double.parseDouble(foodItemParts[1]);
                foodItems.add(new FoodItem(itemName, price));
            }
        }
        Menu menu = new Menu(foodItems);

        String[] reviewData = fields[3].split(";");
        List<Review> reviews = new ArrayList<>();
        for (String review : reviewData) {
            String[] reviewParts = review.split(":");
            reviews.add(reviewMapper.map(reviewParts));
        }

        return new Venue(name, address, menu, reviews);
    }

    public String toCSV(Venue venue) {
        StringBuilder foodItemsBuilder = new StringBuilder();
        for (FoodItem foodItem : venue.getMenu().getFoodItems()) {
            if (foodItemsBuilder.length() > 0) {
                foodItemsBuilder.append(";");
            }
            foodItemsBuilder.append(foodItem.getName()).append(":").append(foodItem.getPrice());
        }

        StringBuilder reviewsBuilder = new StringBuilder();
        for (Review review : venue.getReviews()) {
            if (reviewsBuilder.length() > 0) {
                reviewsBuilder.append(";");
            }
            reviewsBuilder.append(review.getReviewerName()).append(":").append(review.getRating()).append(":").append(review.getComment());
        }

        String addressCSV = addressMapper.toCSV(venue.getAddress());

        return String.join(",",
                venue.getName(),
                addressCSV,
                foodItemsBuilder.toString(),
                reviewsBuilder.toString()
        );
    }
}

