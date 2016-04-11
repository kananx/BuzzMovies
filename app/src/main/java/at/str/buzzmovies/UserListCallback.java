package at.str.buzzmovies;

import java.util.ArrayList;

/**
 * Interface for calling the User List
 * for admin purposes
 */
public interface UserListCallback {
    /**
     * populates the list of users
     * @param accounts list of user accounts
     */
    void populateUserList(ArrayList<Account> accounts);
}
