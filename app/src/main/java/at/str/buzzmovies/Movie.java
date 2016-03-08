package at.str.buzzmovies;

import android.media.Rating;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String Title;
    private String Plot;
    private String Genre;
    private String imdbId;


    /**
     * Constructor for a new movie.
     * @param title the movie's title
     * @param description the movie's description
     * @param genre the movie's genre
     */
    public Movie(String title, String description, String genre, String id) {
        this.Title = title;
        this.Plot = description;
        this.Genre = genre;
        this.imdbId = id;
    }

    /**
     * Returns the movie's title
     * @return title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Returns the movie's description
     * @return description
     */
    public String getDescription() {
        return Plot;
    }

    /**
     * Returns the movie's genre
     * @return genre
     */
    public String getGenre() {
        return Genre;
    }


    public String getId() {return imdbId;}

    /**
     * Returns movie info in the following format:
     * EMAIL (NAME), STATUS
     * @return movie info
     */

    public void setTitle(String newTitle) {this.Title = newTitle;}

    public void setDescription(String newDescription) {this.Plot = newDescription;}

    public void setGenre(String newGenre) {this.Genre = newGenre;}

    public void setId(String newId) {this.imdbId = newId;}

    //holds the ratings and reviews that will be displayed in a listView
    ArrayList<Review> reviews = new ArrayList<>();



    public String toString() {
        return Title + " [" + Genre + "], " + Plot;
    }


}
