package at.str.buzzmovies;

/**
 * Class to make Admin Account @inherits
 */
public class Admin extends Account {

    /**
     * Constructor for a new admin account.
     *
     * @param email    The account's email (cannot be changed)
     * @param password The account's password
     * @param name     The account holder's name
     */

    public Admin(String email, String password, String name) {
        super(email, password, null, name);
    }

}