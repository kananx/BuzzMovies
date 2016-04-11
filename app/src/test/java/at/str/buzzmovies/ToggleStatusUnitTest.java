package at.str.buzzmovies;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
//import android.support.test.runner.AndroidJUnit4;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.str.buzzmovies.AdminActivity;

import static org.junit.Assert.*;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ToggleStatusUnitTest {
    //This tests the isPasswordValid2 method in AccountController

    @Test
    public void testActive() {
        String status = "active";
        AdminActivity admin = new AdminActivity();
        assertEquals(admin.toggleStatus(status), "banned");
    }

    @Test
    public void testBanned() {
        String status = "banned";
        AdminActivity admin = new AdminActivity();
        assertEquals(admin.toggleStatus(status), "active");
    }

    @Test(expected=NullPointerException.class)
    public void testNull() {
        String status = null;
        AdminActivity admin = new AdminActivity();
        status = admin.toggleStatus(status);
    }

    @Test
    public void testOther() {
        String status = "other";
        AdminActivity admin = new AdminActivity();
        assertEquals(admin.toggleStatus(status), null);
    }

    @Test
    public void testEmpty() {
        String status = "";
        AdminActivity admin = new AdminActivity();
        assertEquals(admin.toggleStatus(status), null);
    }
}