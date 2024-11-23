public class ReviewCSVMapper implements CSVMapper<Review> {
    @Override
    public Review map(String[] fields) {
        String reviewerName = fields[0];
        int rating = Integer.parseInt(fields[1]);
        String comment = fields[2];

        return new Review(reviewerName, rating, comment);
    }

    public String toCSV(Review review) {
        return String.join(",",
                review.getReviewerName(),
                String.valueOf(review.getRating()),
                review.getComment()
            );
    }
}
