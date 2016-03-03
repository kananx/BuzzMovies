package at.str.buzzmovies;
import android.app.Application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ImportantData extends Application {


    private User currentUser = null;
    public String results = null;

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


}