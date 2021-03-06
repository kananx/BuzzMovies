package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


/**
 * Home activity page that allows the user to interact with available movies.
 * @author Delicious 3.14
 * @version 1.0
 * @since 02-13-2016
 */
public class HomeActivity extends AppCompatActivity {

    protected Button mRecommendationButton;
    private TextView mRecommendationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar actionToolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(actionToolbar);
        mRecommendationButton = (Button) findViewById(R.id.recommendation_button);
        mRecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecommendation();
            }
        });

       mRecommendationTextView = (TextView) findViewById(R.id.recommended_movie);

        mRecommendationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMovie();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getMenuInflater().inflate(R.menu.options_menu, optionMenu);
        // Associate searchable configuration with the SearchView
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overflow_logout:
                logout();
                return true;
            case R.id.overflow_profile:
                profile();
                return true;
            case R.id.search:
                onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Directs app to the LoginActivity page.
     */
    private void logout() {
        LocalStore.setCurrentAccount(null);
        final Intent toLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(toLoginActivity);
    }

    /**
     * Directs app to the ProfileActivity page.
     */
    private void profile() {
        final Intent toProfileActivity = new Intent(this, ProfileActivity.class);
        startActivity(toProfileActivity);
    }

    private void getRecommendation() {
        Movie movie = HomeController.getMovieRecommendation(this.getApplicationContext(), this);
    }

    public void updateRecommendationTextBox() {
        mRecommendationTextView.setText(LocalStore.getCurrentRecommendedMovie().getTitle());
    }

    private void goToMovie() {
        Intent toMovieScreen = new Intent(this, MovieScreen.class);
        toMovieScreen.putExtra(MovieScreen.MOVIE_ID, LocalStore.getCurrentRecommendedMovie().getId());
        startActivity(toMovieScreen);
    }
}

