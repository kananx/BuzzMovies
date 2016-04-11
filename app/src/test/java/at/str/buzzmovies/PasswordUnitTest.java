package at.str.buzzmovies;

import org.junit.Test;
import org.junit.runner.RunWith;
//import android.support.test.runner.AndroidJUnit4;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class PasswordUnitTest {
    //This tests the isPasswordValid2 method in AccountController

    @Test
    public void testPasswordNull() {
        String a = null;
        assertEquals(AccountController.isPasswordValid2(a), false);
    }

    @Test
    public void testPasswordTooShort() {
        String a = "Abcdef";
        assertEquals(AccountController.isPasswordValid2(a), false);
    }

    @Test
    public void testPasswordNoCapital() {
        String a = "abcdefg";
        assertEquals(AccountController.isPasswordValid2(a), false);
    }

    @Test
    public void testPasswordNoLowercase() {
        String a = "ABCDEFG";
        assertEquals(AccountController.isPasswordValid2(a), false);
    }

    @Test
    public void testPasswordIsValid() {
        String a = "Abcdefg";
        assertEquals(AccountController.isPasswordValid2(a), true);
    }
}