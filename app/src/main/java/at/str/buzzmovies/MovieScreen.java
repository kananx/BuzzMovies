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

public class MovieScreen extends AppCompatActivity {
    public final static String MOVIE_ID = "at.str.buzzmovies.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_screen);
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


        TextView movieTitle = (TextView) findViewById(R.id.movieTitle);
        TextView movieGenre = (TextView) findViewById(R.id.movieGenre);
        TextView movieDescr = (TextView) findViewById(R.id.movieDescription);

        //movieTitle.setText(moviex.getTitle);
        //movieGenre.setText(moviex.getgenre);
        //movieDescr.setText(moviex.getgenre);

        //implement viewList of reviews

        Button addRating = (Button) findViewById(R.id.addRatingButton);
        addRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 3/8/16 Update with movie ID var 
                review("");
            }
        });


    }


    public void review(String movieID) {
        Intent toReviewActivity = new Intent(this, ReviewActivity.class);
        toReviewActivity.putExtra(MOVIE_ID, movieID);
        startActivity(toReviewActivity);


    }

}
