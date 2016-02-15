package at.str.buzzmovies;

/**
 * Class to make User Account @inherits
 */
public class User extends Account {
    private String major;
    private String interest;

    /**
     * Constructor for a new account.
     *
     * @param email    The account's email (cannot be changed)
     * @param password The account's password
     * @param status   The account's status
     * @param name     The account holder's name
     * @param major    The account holder's major
     * @param interest The account holder's interests
     */
    public User(String email, String password, String status, String name, String major, String interest) {
        super(email, password, status, name);
        this.major = major;
        this.interest = interest;
    }

    /**
     * returns acount holder's major
     * @return major
     */
    public String getMajor() {return major;}

    /**
     * returns accounts holder's interest
     * @return interest
     */

    public String getInterest() {return interest;}

    /**
     * Change's account holder's major
     * @param newMajor the updates major
     */
    public void setMajor(String newMajor) {major = newMajor;}

    /**
     * Change's account holder's interest
     * @param newInterest the updates interest
     */
    public void setInterest(String newInterest) {interest = newInterest;}

    @Override
    /**
     * Returns users information
     * @return user's information
     */
    public String toString() {return  email + " (" + name + "), " + status + ", " + major + ", " + interest;}
}
