package at.str.buzzmovies;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GetMovieByIDUnitTest {
    //This tests the getMovieByID method in LocalStore

    Movie testMovie = new Movie("Frozen", "Plot", 2013, "Animation, Adventure, Comedy", "tt2294629");

    @Test
    public void testMatchingID() {
        LocalStore.addMovie(testMovie);
        String testID = LocalStore.getMovieByID("tt2294629").getId();
        assertEquals(testID, testMovie.getId());
    }

    @Test
    public void testMatchingTitle() {
        LocalStore.addMovie(testMovie);
        String testName = LocalStore.getMovieByID("tt2294629").getTitle();
        assertEquals(testName, "Frozen");
    }

    @Test
    public void testMissingID() {
        LocalStore.addMovie(testMovie);
        assertEquals(LocalStore.getMovieByID("a"), null);
    }

    @Test
    public void testNull() {
        LocalStore.addMovie(testMovie);
        assertEquals(LocalStore.getMovieByID(null), null);
    }
}