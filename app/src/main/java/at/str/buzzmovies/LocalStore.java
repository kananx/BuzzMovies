package at.str.buzzmovies;

import java.util.ArrayList;

/**
 * Created by ryan on 3/5/16.
 */
public class LocalStore {

    /**
     * The list of reviews
     */
    private static ArrayList<Review> reviews = new ArrayList<Review>();
    /**
     * The current account
     */
    private static Account currentAccount = null;
    /**
     * The list of movies
     */
    private static ArrayList<Movie> movies = new ArrayList<Movie>();
    /**
     * The list of accounts
     */
    private static ArrayList<Account> accounts = new ArrayList<Account>();


    /**
     * Returns the current account
     * @return currentAccount
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Stores changes in an account
     * @param account The account
     */
    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    /**
     * Returns a review with the given ID
     * @param user The account of the review
     * @param id The ID of the review
     * @return review (if it exists, otherwise null)
     */
    public static Review findReview(Account user, String id) {

        for (final Review review: reviews ) {
            final String key = review.getMovie().getId();
            if (key.equals(id)) {
                return review;
            }
        }
        return null;
    }

    /**
     * Adds a review
     * @param rev The review
     */
    public static void addReview (Review rev) {
        reviews.add(rev);
    }

    /**
     * Adds a movie
     * @param movie The movie
     */
    public static void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Clears the list of movies
     */
    public static void clearMovies() {
        movies.clear();
    }

    /**
     * Adds a new account
     * @param account The new account
     */
    public static void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Clears all accounts
     */
    public static void clearAccounts() {
        accounts.clear();
    }

    /**
     * Returns a list of movies
     * @return movies
     */
    public static ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Returns a movie with the given ID
     * @param id The movie's ID
     * @return movie
     */
    public static Movie getMovieByID(String id) {
        for (final Movie movie:movies) {
            final String key = movie.getId();
            if (key.equals(id)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns a list of all accounts
     * @return accounts
     */
    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
}
