package at.str.buzzmovies;

import java.util.ArrayList;

/**
 * Created by ryan on 3/5/16.
 */
public class localStore {

    private static ArrayList<Review> reviews = new ArrayList<Review>();
    private static Account currentAccount = null;
    private static ArrayList<Movie> movies = new ArrayList<Movie>();
    private static ArrayList<Account> accounts= new ArrayList<Account>();


    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static Review findReview(Account user, String id) {

        for (Review review: reviews ) {
            String key = review.getMovie().getId();
            if (key.equals(id)) {
                return review;
            }
        }
        return null;

    }

    public static void addReview (Review rev) {
        reviews.add(rev);
    }

    public static void addMovie(Movie movie) {
        movies.add(movie);
    }

    public static void clearMovies() {
        movies.clear();
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    public static ArrayList<Movie> getMovies() {
        return movies;
    }

    public static Movie getMovieByID(String id) {
        for (Movie movie:movies ) {
            String key = movie.getId();
            if (key.equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
}
