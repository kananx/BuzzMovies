package at.str.buzzmovies;

import java.util.ArrayList;

/**
 * Created by ryan on 3/5/16.
 */
public class localStore {

    private static ArrayList<Review> reviews = new ArrayList<Review>();
    private static Account currentAccount = null;


    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static Review findReview(Account user, String id) {

        for (Review rev: reviews ) {
            String key = rev.getMovie().getId();
            if (key.equals(id)) {
                return rev;
            }
        }
        return null;

    }

    public static void addReview (Review rev) {
        reviews.add(rev);
    }

}
