package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * the logic for the UI elements on the screen that allows a user to leave a review for a movie
 */
public class ReviewActivity extends AppCompatActivity {
    private static RatingBar movieRating;
    private EditText mReview;
    private Movie movie;
    private Review currentReview;

    /**
     * creates the UI elements
     * @param savedInstanceState the state of the current instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        Intent intent = getIntent();
        String movieID = intent.getStringExtra(MovieScreen.MOVIE_ID);
        movie = localStore.getMovieByID(movieID);

        TextView reviewTitle = (TextView) findViewById(R.id.reviewTitle);
        mReview = (EditText) findViewById(R.id.review);
        reviewTitle.setText(movie.getTitle());

        movieRating = (RatingBar) findViewById(R.id.movieRating);

        currentReview = localStore.findReview(localStore.getCurrentAccount(), movie.getId());
        if (currentReview != null) {
            mReview.setText(currentReview.getReview());
            movieRating.setRating(currentReview.getRating());
        } else {
            currentReview = new Review();
        }


        listenerForRatingBar();
        Button submitButton = (Button) findViewById(R.id.submitReviewButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }

    /**
     * listener that detects a change in the current rating
     */
    private void listenerForRatingBar() {

        movieRating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        Toast.makeText(getApplicationContext(), "Your Selected Rating :" + String.valueOf(rating), Toast.LENGTH_SHORT).show();
                        currentReview.setRating(rating);
                    }
                }
        );


    }

    /**
     * When user presses submit button, returns screen to the current movie screen
     */
    private void submit() {

        currentReview.setReview(mReview.getText().toString());
        currentReview.setMajor(((User) localStore.getCurrentAccount()).getMajor());
        currentReview.setReviewer((User) localStore.getCurrentAccount());
        currentReview.setMovie(movie);
        localStore.addReview(currentReview);
        Intent toMovieScreen = new Intent(this, HomeActivity.class);
        startActivity(toMovieScreen);

    }
}

