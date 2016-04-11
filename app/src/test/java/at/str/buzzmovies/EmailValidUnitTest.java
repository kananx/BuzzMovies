package at.str.buzzmovies;

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

    @Test
    public void testEmailContainsAt() {
        String a = user@example.com;
        assertEquals(LoginActivity.isEmailValid(a), true);
    }

}