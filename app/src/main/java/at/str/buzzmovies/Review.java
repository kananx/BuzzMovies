package at.str.buzzmovies;

/**
 * Class for a Review Object for a movie.
 * A review contains a rating of 1-5, a written review, has a reviewer and their major associated with it
 * and the movie that has been reviewed.
 * @author Tiera Lee
 * @version 1.0
 */
public class Review {

    private float rating;
    private String review;
    private User reviewer;
    private String major;
    private Movie movie;

   /*
    /**
     * create a rating object
     * @param rating number of stars
     * @param review written review
     * @param reviewer user who left review
     * @param major major of user who left review
     * @param movie movie that review is being left for

    public Review (float rating, String review,  User reviewer, String major, Movie movie) {
            this.rating = rating;
            this.reviewer = reviewer;
            this.major = major;
            this.review = review;
            this.movie = movie;
        }
<<<<<<< HEAD

        public Review() {}

    public void setRating(float rating) { this.rating = rating;}

        public void setReview(String review) {this.review = review;}

        public void setReviewer(User reviewer) {this.reviewer = reviewer;}

        public void setMajor(String major) {this.major = major;}

        public void setMovie(Movie movie) {this.movie = movie;}

        public float getRating() {return rating;}

        public String getReview() {return review;}

        public User getReviewer() {return reviewer;}

        public String getMajor() {return major;}

        public Movie getMovie() {return movie;}

=======
        */

    public Review() {}

    /**
     *
     * @param rating a number that sets amount of stars
     */
    public void setRating(float rating) { this.rating = rating;}

    /**
     *
     * @param review a written review that user about a movie
     */
    public void setReview(String review) {this.review = review;}

    /**
     *
     * @param reviewer associated user that created review
     */
    public void setReviewer(User reviewer) {this.reviewer = reviewer;}

    /**
     *
      * @param major the major of the user who created the review
     */
    public void setMajor(String major) {this.major = major;}

    /**
     *
     * @param movie the movie that the user left the review for
     */
    public void setMovie(Movie movie) {this.movie = movie;}

    /**
     *
     * @return the number of stars given to a movie
     *
     */
    public float getRating() {return rating;}

    /**
     *
     * @return the written review left for a movie
     */
    public String getReview() {return review;}

    /**
     *
     * @return the user that left the review
     */
    public User getReviewer() {return reviewer;}

    /**
     *
     * @return the major of the user who left the review
     */
    public String getMajor() {return major;}

    /**
     *
     * @return the movie that the review was written about
     */
    public Movie getMovie() {return movie;}

    @Override
    /**
     * formatted String containing the attributes of a review
     */

    public String toString() {
        return reviewer + "gave this movie " + rating + " stars, and said" + review;
    }
}
