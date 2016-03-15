package at.str.buzzmovies;

import android.content.Intent;
import android.media.Rating;
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

import java.lang.reflect.Array;

public class ReviewActivity extends AppCompatActivity {
    protected static RatingBar movieRating;
    protected EditText review;
    protected Movie movie;

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
        review = (EditText) findViewById(R.id.review);
        reviewTitle.setText(movie.getTitle());



        Review existingRev = localStore.findReview(localStore.getCurrentAccount(), movieID);
        if (existingRev != null) {
            review.setText(existingRev.getReview());
            movieRating.setRating(existingRev.getRating());
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

    public void listenerForRatingBar() {
        movieRating = (RatingBar) findViewById(R.id.movieRating);
        movieRating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        Toast.makeText(getApplicationContext(), "Your Selected Rating :" + String.valueOf(rating), Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    public void submit() {
        //arrylist of reviews for current movie
        //reviews.add(movieRating.getRating(), review.getText(), user.getName(), user.getMajor();

        Review rev = new Review(movieRating.getRating(), review.getText().toString(), localStore.getCurrentAccount().getName(),
                ((User)localStore.getCurrentAccount()).getMajor(), movie);
        localStore.addReview(rev);
        Intent toMovieScreen = new Intent(this, MovieScreen.class);
        startActivity(toMovieScreen);

    }
}

