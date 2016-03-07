package at.str.buzzmovies;

import android.media.Rating;

/**
 * Created by Tiera on 3/7/16.
 */
public class Review {

        protected Rating rating;
        protected String review;
        protected String reviewer;
        protected String major;

        public Review (Rating rating, String review,  String reviewer, String major) {
            this.rating = rating;
            this.reviewer = reviewer;
            this.major = major;
            this.review = review;
        }

        public void setRating(Rating rating) { this.rating = rating;}

        public void setReview(String review) {this.review = review;}

        public void setReviewer(String reviewer) {this.reviewer = reviewer;}

        public void setMajor(String major) {this.major = major;}

        public Rating getRating() {return rating;}

        public String getReview() {return review;}

        public String getReviewer() {return reviewer;}

        public String getMajor() {return major;}

    public String toString() {
        return reviewer + "gave this movie " + rating + " stars, and said" + review;
    }
}
