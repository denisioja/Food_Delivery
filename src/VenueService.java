import java.io.IOException;
import java.util.List;

public class VenueService {
    private static VenueService instance;
    private List<Venue> venues;
    private final String filePath = "data/venue.csv";
    private final CSVMapper<Venue> venueMapper = new VenueCSVMapper();

    private VenueService() {
        loadVenues();
    }

    public static VenueService getInstance() {
        if (instance == null) {
            instance = new VenueService();
        }
        return instance;
    }

    private void loadVenues() {
        try {
            venues = CSVReader.getInstance().read(filePath, venueMapper, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void createVenue(Venue venue) {
        venues.add(venue);
        saveVenues();
        AuditingService.getInstance().logAction("Create Venue");
    }

    public Venue getVenueByName(String name) {
        for (Venue venue : venues) {
            if (venue.getName().equals(name)) {
                return venue;
            }
        }
        return null;
    }

    public void updateVenue(String name, Venue updatedVenue) {
        Venue existingVenue = getVenueByName(name);
        if (existingVenue != null) {
            existingVenue.setName(updatedVenue.getName());
            existingVenue.setAddress(updatedVenue.getAddress());
            existingVenue.setMenu(updatedVenue.getMenu());
            existingVenue.setReviews(updatedVenue.getReviews());

            saveVenues();
            AuditingService.getInstance().logAction("Update Venue");

        } else {
            throw new IllegalArgumentException("Venue not found");
        }
    }

    public void deleteVenue(String name) {
        Venue venueToRemove = getVenueByName(name);
        if (venueToRemove != null) {
            venues.remove(venueToRemove);

            saveVenues();
            AuditingService.getInstance().logAction("Delete Venue");

        } else {
            throw new IllegalArgumentException("Venue not found");
        }
    }
    private void saveVenues() {
        try {
            CSVWriter.getInstance().write(filePath, venues, venueMapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
