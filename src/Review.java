public class Review {
    private String reviewerName;
    private int rating;
    private String comment;

    public Review(String reviewerName, int rating, String comment) {
        setReviewerName(reviewerName);
        setRating(rating);
        setComment(comment);
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        if(reviewerName == null || reviewerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Reviewer name cannot be null or empty.");
        }
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating >= 1 && rating <=5) {
            this.rating = rating;
        }
        else throw new IllegalArgumentException("Rating must be between 1 and 5.");
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be null or empty.");
        }
        this.comment = comment;
    }

    public void displayReviewDetails() {
        System.out.println("Reviewer: " + reviewerName);
        System.out.println("Rating: " + rating + " stars");
        System.out.println("Comment: " + comment);
    }
}
