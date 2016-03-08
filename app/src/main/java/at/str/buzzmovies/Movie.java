package at.str.buzzmovies;

import android.media.Rating;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    protected String title;
    protected String plot;
    protected int year;
    protected String genre;
    protected String imdbId;



    /**
     * Constructor for a new movie.
     * @param title the movie's title
     * @param plot the movie's description
     * @param year year the movie was released
     * @param genre the movie's genre
     * @param id IMDB ID of the movie
     */
    public Movie(String title, String plot, int year, String genre, String id) {
        this.title = title;
        this.plot = plot;
        this.year = year;
        this.genre = genre;
        this.imdbId = id;
    }

    /**
     * Returns the movie's title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the movie's plot
     * @return description
     */
    public String getPlot() {
        return plot;
    }

    /**
     * Returns the movie's genre
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Getter for IMDB ID
     * @return IMDB ID
     */
    public String getId() {
        return imdbId;
    }

    /**
     * set new title for movie object
     * @param newTitle the new title for the movie object
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

<<<<<<< HEAD
    /**
     * set new plot for movie
     * @param newPlot the new plot for the movie
     */
    public void setPlot(String newPlot) {
        this.plot = newPlot;
    }
=======
    public String getimdbId() {return imdbId;}
>>>>>>> 94b6f5ddcfd184fd5115dc53ec6a5e3518820ea6

    /**
     * set new genre for the movie
     * @param newGenre genre for movie
     */
    public void setGenre(String newGenre) {
        this.genre = newGenre;
    }

    /**
     * set new ID for movie
     * @param newId IMDB ID
     */
    public void setId(String newId) {
        this.imdbId = newId;
    }

    /**
     * set year of release of movie
     * @param year year of release
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the movie's year of release
     * @return year of release
     */
    public int getYear() {
        return this.year;
    }


    //holds the ratings and reviews that will be displayed in a listView
    ArrayList<Review> reviews = new ArrayList<>();



    public String toString() {
        return title + " [" + year + "], " + plot;
    }


}
