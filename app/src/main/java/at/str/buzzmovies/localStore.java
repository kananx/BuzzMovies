package at.str.buzzmovies;

/**
 * Created by ryan on 3/5/16.
 */
public class localStore {
    private static Account currentAccount = null;

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }
}
