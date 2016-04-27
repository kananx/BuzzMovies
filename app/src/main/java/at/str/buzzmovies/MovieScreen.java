package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Movie Screen class that extends AppCompatActivity
 */
public class MovieScreen extends AppCompatActivity {
    public final static String MOVIE_ID = "at.str.buzzmovies.MESSAGE";
    Movie currentMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int moviePosition = intent.getIntExtra(MovieListAdapter.MOVIE_POSITION, 0);
        currentMovie = LocalStore.getMovies().get(moviePosition);


        TextView movieTitle = (TextView) findViewById(R.id.movieTitle);
        TextView movieGenre = (TextView) findViewById(R.id.movieGenre);
        TextView movieDescr = (TextView) findViewById(R.id.movieDescription);

        movieTitle.setText(currentMovie.getTitle());
        //movieGenre.setText(currentMovie.get);
        movieDescr.setText(((Integer)currentMovie.getYear()).toString());

        //implement viewList of reviews

        Button addRating = (Button) findViewById(R.id.addRatingButton);
        addRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review(currentMovie.getId());
            }
        });


    }


    /**
     * review method to review a movie
     * @param movieID takes in movie id as a string
     */
    public void review(String movieID) {
        Intent toReviewActivity = new Intent(this, ReviewActivity.class);
        toReviewActivity.putExtra(MOVIE_ID, movieID);
        startActivity(toReviewActivity);
    }


}
