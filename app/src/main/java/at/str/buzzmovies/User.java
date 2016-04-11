package at.str.buzzmovies;

/**
 * User account that can use the application features
 * @inheritDoc Account.java
 */
public class User extends Account {
    private String major;
    private String interest;

    /**
     * Constructor for a new account.
     *
     * @param email    The account's email (cannot be changed)
     * @param token    The account's password
     * @param status   The account's status
     * @param name     The account holder's name
     * @param major    The account holder's major
     * @param interest The account holder's interests
     */
    public User(String email, String token, String status, String name, String major, String interest) {
        super(email, token, status, name);
        this.major = major;
        this.interest = interest;
    }


    /**
     * Constructor for new user account
     * @param email The user's email
     * @param token The user's API token
     * @param name The user's name
     */
    public User(String email, String token, String name) {
        super(email, token, null, name);
        this.major = null;
        this.interest = null;
    }

    /**
     * returns account holder's major
     * @return major
     */
    public String getMajor() {return major;}

    /**
     * returns accounts holder's interest
     * @return interest
     */

    public String getInterest() {return interest;}

    /**
     * Changes account holder's major
     * @param newMajor the updates major
     */
    public void setMajor(String newMajor) {major = newMajor;}

    /**
     * Changes account holder's interest
     * @param newInterest the updates interest
     */
    public void setInterest(String newInterest) {interest = newInterest;}

    @Override
    /**
     * Returns users information
     * @return user's information
     */
    public String toString() {return email + " (" + name + "), " + status + ", " + major + ", " + interest;}
}
