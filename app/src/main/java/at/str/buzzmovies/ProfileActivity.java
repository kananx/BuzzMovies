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
import android.widget.TextView;


/**
 * Controls activity that happens on Profile page of the App
 * @author Delicous 3.14
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

        TextView email = (TextView) findViewById(R.id.email_textView);
        //have email load from user array
        EditText nameText = (EditText) findViewById(R.id.name_editText);
        //retrieving name, might use in user.java
        String name = nameText.getText().toString();

        TextView majorPromp = (TextView) findViewById(R.id.major_TextView);
        EditText majorText = (EditText) findViewById(R.id.major_editText);
        //retreiving major
        String major = majorText.getText().toString();
        EditText interests = (EditText) findViewById(R.id.interests_editText);
        //retrieving interest
        String interestStr = interests.getText().toString();


        Button mHomeButton = (Button) findViewById(R.id.home_button);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHome();
            }
        });


    }

    private void toHome() {
        Intent toHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(toHomeActivity);

    }
}
