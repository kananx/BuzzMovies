package at.str.buzzmovies;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;

/**
 * Created by Tiera on 4/11/16.
 */
public class ChangeInformationUnitTest {
    //See if progile information is changed via changedInformation() in ProfileActivity
    private User user1;
    private ProfileActivity profileActivity;

    @Before
    public void setUp() {
        user1 = new User("tiera@gatech.edu", "password", "unbanned", "Tiera", "CS", "Comedy");
        profileActivity = new ProfileActivity();
    }

    @Test
    public void changeName() {
        String user1name = user1.getName();
        profileActivity.user = user1;
        user1.setName("Ryan");
        profileActivity.changeInformation();
        assertNotSame(user1name, user1.getName());
    }

    @Test
    public void changeMajor() {
        String user1Major = user1.getMajor();
        profileActivity.user = user1;
        user1.setMajor("Art History");
        profileActivity.changeInformation();
        assertNotSame(user1Major, user1.getMajor());
    }

    @Test
    public void changeInterest() {
        String user1Interest = user1.getInterest();
        profileActivity.user = user1;
        user1.setInterest("Action and Horror");
        profileActivity.changeInformation();
        assertNotSame(user1Interest, user1.getInterest());
    }
}
