package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.app.ActionBar;
import android.view.Menu;

/**
 * Home activity page that allows the user to interact with available movies.
 * @author Delicious 3.14
 * @verion 1.0
 * @since 02-13-2016
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar action_toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(action_toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //When logout button is clicked, logout of user profile and go to loginActivity
        Button mLogoutButton = (Button) findViewById(R.id.logout_button);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Button mProfileButton = (Button) findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }
        });




    }

    public boolean onCreateOptionsMenu(Menu overflow) {
        getMenuInflater().inflate(R.menu.overflow, overflow);
        return true;
    }

    /**
     * Directs app to the LoginActivity page
     */
    private void logout() {
        Intent toLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(toLoginActivity);
    }

    private void profile() {
        Intent toProfileActivity = new Intent(this, ProfileActivity.class);
        startActivity(toProfileActivity);
    }

}
