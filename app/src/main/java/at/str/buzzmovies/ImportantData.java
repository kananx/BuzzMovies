package at.str.buzzmovies;
import android.app.Application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ImportantData extends Application {

    private HashMap<String, User> USERDATABASE = new HashMap<>();

    private User currentUser = null;

    /**
     * Returns the current user.
     * @return currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user as the given user
     * @param user the new currentUser
     */
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Returns the USERDATABASE
     * @return USERDATABASE
     */
    public HashMap<String, User> getDB() {
        return USERDATABASE;
    }

    /**
     * Returns the set of email addresses.
     * @return the set of email addresses
     */
    public Set<String> getEmails() {
        return USERDATABASE.keySet();
    }

    /**
     * Returns the set of users.
     * @return the set of users
     */
    public Collection<User> getUsers() {
        return USERDATABASE.values();
    }

    /**
     * Adds a user to the database.
     * @param email the new user's email address
     * @param user the new user
     */
    public void addUser(String email, User user) {
        USERDATABASE.put(email, user);
    }
}