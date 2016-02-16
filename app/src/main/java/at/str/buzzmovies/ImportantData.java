package at.str.buzzmovies;
import android.app.Application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ImportantData extends Application {

    private HashMap<String, User> USERDATABASE = new HashMap<>();

    private User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
    public HashMap<String, User> getDB() {
        return USERDATABASE;
    }
    public Set<String> getEmails() {
        return USERDATABASE.keySet();
    }

    public Collection<User> getUsers() {
        return USERDATABASE.values();
    }
    public void addUser(String email, User user) {
        USERDATABASE.put(email, user);
    }
}