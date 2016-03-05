package at.str.buzzmovies;

/**
 * An abstract Account class used for creating users and admins.
 */
public abstract class Account {
    protected String email;
    protected String token;
    protected String status;
    protected String name;

    /**
     * Constructor for a new account.
     * @param email The account's email (cannot be changed)
     * @param token The account's password
     * @param status The account's status
     * @param name The account holder's name
     */
    public Account(String email, String token, String status, String name) {
        this.email = email;
        this.token = token;
        this.status = status;
        this.name = name;
    }

    /**
     * Returns the account's email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the account's password.
     * @return password
     */
    public String getPassword() {
        return token;
    }

    /**
     * Returns the account's status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the account holder's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Change's the account's password.
     * @param newPass The new password
     */
    public void setPassword(String newPass) {
        token = newPass;
    }

    /**
     * Change's the account's status.
     * @param newStatus The new status
     */
    public void setStatus(String newStatus) {
        status = newStatus;
    }

    /**
     * Change's the account holder's name.
     * @param newName The new name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Returns account info in the following format:
     * EMAIL (NAME), STATUS
     * @return account info
     */
    public String toString() {
        return email + " (" + name + "), " + status;
    }
}
