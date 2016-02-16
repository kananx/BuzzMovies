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
    private User user;
    private String name;
    private String interestStr;
    private String major;
    private String email;
    private TextView mEmail;
    private EditText nameText;
    private TextView majorPromp;
    private EditText majorText;
    private EditText interests;
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

        user = ((ImportantData) this.getApplication()).getCurrentUser();

        mEmail = (TextView) findViewById(R.id.email_textView);
        mEmail.setText(user.getEmail());
        email = mEmail.getText().toString();
        nameText = (EditText) findViewById(R.id.name_editText);
        majorPromp = (TextView) findViewById(R.id.major_TextView);
        majorText = (EditText) findViewById(R.id.major_editText);
        interests = (EditText) findViewById(R.id.interests_editText);

        majorText.setText(user.getMajor());
        interests.setText(user.getInterest());
        nameText.setText(user.getName());

        Button mHomeButton = (Button) findViewById(R.id.home_button);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set all of the fields in user
                //retrieving name, might use in user.java
                name = nameText.getText().toString();
                //retreiving major
                major = majorText.getText().toString();
                //retrieving interest
                interestStr = interests.getText().toString();
                changeInformation();
                toHome();
            }
        });


    }

    private void changeInformation() {
        user.setInterest(interestStr);
        user.setMajor(major);
        user.setName(name);
        ((ImportantData) this.getApplication()).addUser(user.getEmail(), user);
        ((ImportantData) this.getApplication()).setCurrentUser(user);
    }

    private void toHome() {
        Intent toHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(toHomeActivity);
    }
}
