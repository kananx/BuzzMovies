package at.str.buzzmovies;

/**
 * An abstract Account class used for creating users and admins.
 */
public class Account {
    /**
     * The account's email (cannot be changed)
     */
    protected String email;
    /**
     * The account's password
     */
    protected String token;
    /**
     * The account's status
     */
    protected String status;
    /**
     * The account holder's name
     */
    protected String name;

    /**
     * Constructor for a new account.
     * @param newEmail The account's email (cannot be changed)
     * @param newToken The account's password
     * @param newStatus The account's status
     * @param newName The account holder's name
     */
    public Account(String newEmail, String newToken, String newStatus, String newName) {
        email = newEmail;
        token = newToken;
        status = newStatus;
        name = newName;
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
     * Returns the account holder's token (password)
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * Changes the account's password.
     * @param newPass The new password
     */
    public void setPassword(String newPass) {
        token = newPass;
    }

    /**
     * Changes the account's status.
     * @param newStatus The new status
     */
    public void setStatus(String newStatus) {
        status = newStatus;
    }

    /**
     * Changes the account holder's name.
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
