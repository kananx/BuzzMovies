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
     * @param status   The account's status
     * @param name     The account holder's name
     */

    public Admin(String email, String password, String status, String name) {
        super(email, password, status, name);
    }

    @Override
    /**
     * Returns admins information
     * @return admin's information
     */
    public String toString() {return  email + " (" + name + "), " + status;}


}
