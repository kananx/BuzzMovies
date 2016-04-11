package at.str.buzzmovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import android.support.test.runner.AndroidJUnit4;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test unit case for testing isEmailValid.
 */


public class EmailValidUnitTest {
    //This tests the isEmailValid method in LoginActivity

    private LoginActivity loginActivity;

    @Test
    public void testEmailContainsAt() {

        loginActivity = new LoginActivity();
        String a = "user@example.com";
        assertEquals(loginActivity.isEmailValid(a), true);
    }

}