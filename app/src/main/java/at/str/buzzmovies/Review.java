package at.str.buzzmovies;

import android.media.Rating;

/**
 * Created by Tiera on 3/7/16.
 */
public class Review {

        protected float rating;
        protected String review;
        protected User reviewer;
        protected String major;
        protected Movie movie;

        public Review (float rating, String review,  User reviewer, String major, Movie movie) {
            this.rating = rating;
            this.reviewer = reviewer;
            this.major = major;
            this.review = review;
            this.movie = movie;
        }

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

    public String toString() {
        return reviewer + "gave this movie " + rating + " stars, and said" + review;
    }
}
