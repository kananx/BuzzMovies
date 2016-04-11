package at.str.buzzmovies;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static at.str.buzzmovies.ProfileActivity.*;
import static org.junit.Assert.*;

/**
 * Created by Tiera on 4/11/16.
 */
public class ShouldChangeInformationTest {
    //See if Profile information is changed via changeInformation() in ProfileActivity
    private User user1;
    private ProfileActivity profileActivity;

    @Before
    public void setUp() throws Exception {
        //create new user
        user1 = new User("tiera@gatech.edu", "password", "unbanned", "Tiera", "CS", "comedy");
        profileActivity = new ProfileActivity();
    }

    @Test
    public void changeName() throws Exception {
        String user1name = user1.getName();
        profileActivity.user = user1;
        user1.setName("Ryan");
        profileActivity.changeInformation();
        assertNotSame(user1name, user1.getName());

    }

    @Test
    public void changeMajor() throws Exception {
        String user1Major = user1.getMajor();
        profileActivity.user = user1;
        user1.setMajor("Art History");
        profileActivity.changeInformation();
        assertNotSame(user1Major, user1.getMajor());
    }

    @Test
    public void changeInterest() throws Exception {
        String user1Interest = user1.getInterest();
        profileActivity.user = user1;
        user1.setInterest("Action and horror");
        profileActivity.changeInformation();
        assertNotSame(user1Interest, user1.getInterest());
    }

}