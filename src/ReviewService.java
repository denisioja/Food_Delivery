import java.io.IOException;
import java.util.List;

public class ReviewService {
    private static ReviewService instance;
    private List<Review> reviews;
    private final String filePath = "data/reviews.csv";
    private final CSVMapper<Review> reviewMapper = new ReviewCSVMapper();

    private ReviewService() {
        loadReviews();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    private void loadReviews() {
        try {
            reviews = CSVReader.getInstance().read(filePath, reviewMapper, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void createReview(Review review) {
        reviews.add(review);
        saveReviews();
        AuditingService.getInstance().logAction("Create Review");
    }

    public Review getReviewByReviewerName(String reviewerName) {
        for (Review review : reviews) {
            if (review.getReviewerName().equals(reviewerName)) {
                return review;
            }
        }
        return null;
    }

    public void updateReview(String reviewerName, Review updateReview) {
        Review existingReview = getReviewByReviewerName(reviewerName);
        if (existingReview != null) {
            existingReview.setRating(updateReview.getRating());
            existingReview.setComment(updateReview.getComment());

            saveReviews();
            AuditingService.getInstance().logAction("Update Review");

        } else {
            throw new IllegalArgumentException("Review not found");
        }
    }

    public void deleteReview(String reviewerName) {
        Review reviewToRemove = getReviewByReviewerName(reviewerName);
        if (reviewToRemove != null) {
            reviews.remove(reviewToRemove);

            saveReviews();
            AuditingService.getInstance().logAction("Delete Review");

        } else {
            throw new IllegalArgumentException("Review not found");
        }
    }

    public void saveReviews() {
        try {
            CSVWriter.getInstance().write(filePath, reviews, reviewMapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
