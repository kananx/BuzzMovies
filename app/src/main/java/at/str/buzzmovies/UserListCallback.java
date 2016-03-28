package at.str.buzzmovies;

import java.util.ArrayList;

/**
 * Created by ryan on 3/28/16.
 */
public interface UserListCallback {
    void populateUserList(ArrayList<Account> accounts);
}
