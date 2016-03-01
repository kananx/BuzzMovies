package at.str.buzzmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String plot;
    private String genre;

    /**
     * Constructor for a new movie.
     * @param title the movie's title
     * @param plot the movie's description
     * @param genre the movie's genre
     */
    public Movie(String title, String plot, String genre) {
        this.title = title;
        this.plot = plot;
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
     * Returns movie info in the following format:
     * EMAIL (NAME), STATUS
     * @return movie info
     */



    public void setTitle(String newTitle) {this.title = newTitle;}

    public void setPlot(String newPlot) {this.plot = newPlot;}

    public void setGenre(String newGenre) {this.genre = newGenre;}


    public String toString() {
        return title + " [" + genre + "], " + plot;
    }
}
