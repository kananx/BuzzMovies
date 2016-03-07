package at.str.buzzmovies;

import android.media.Rating;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class reviewActivity extends AppCompatActivity {
    private static RatingBar movieRating;


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


        TextView reviewTitle = (TextView) findViewById(R.id.reviewTitle);
        //reviewTitle.setText(moviex.getTitle());

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
        //push data from here to listview array


    }
}

