package at.str.buzzmovies;

import android.media.Rating;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private String genre;

    /**
     * Constructor for a new movie.
     * @param title the movie's title
     * @param description the movie's description
     * @param genre the movie's genre
     */
    public Movie(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    /**
     * Returns the movie's title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the movie's description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the movie's genre
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns movie info in the following format:
     * EMAIL (NAME), STATUS
     * @return movie info
     */

    public void setTitle(String newTitle) {this.title = newTitle;}

    public void setDescription(String newDescription) {this.description = newDescription;}

    public void setGenre(String newGenre) {this.genre = newGenre;}

    ArrayList<Rating> ratings = new ArrayList<>();


    public String toString() {
        return title + " [" + genre + "], " + description;
    }


}
