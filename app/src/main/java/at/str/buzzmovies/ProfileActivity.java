package at.str.buzzmovies;

import android.content.Context;
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
import android.widget.Toast;


/**
 * Controls activity that happens on Profile page of the App
 * @author Delicous 3.14
 */
public class ProfileActivity extends AppCompatActivity {
    protected User user;
    private String name;
    private String interestStr;
    private String major;
    private String newPass;
    private String newPassC;
    private TextView mEmail;
    private TextView majorPromp;
    private EditText nameText;
    private EditText majorText;
    private EditText interests;
    private EditText changePass;
    private EditText confirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        user = (User) localStore.getCurrentAccount();

        TextView mEmail = (TextView) findViewById(R.id.email_textView);
        mEmail.setText(user.getEmail());
        String email = mEmail.getText().toString();
        nameText = (EditText) findViewById(R.id.name_editText);
        TextView majorPromp = (TextView) findViewById(R.id.major_TextView);
        majorText = (EditText) findViewById(R.id.major_editText);
        interests = (EditText) findViewById(R.id.interests_editText);
        changePass = (EditText) findViewById(R.id.changePass_editText);
        confirmPass = (EditText) findViewById(R.id.confirmPass_editText);

        majorText.setText(user.getMajor());
        interests.setText(user.getInterest());
        nameText.setText(user.getName());
        changePass.setText("");
        confirmPass.setText("");

        Button mHomeButton = (Button) findViewById(R.id.home_button);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    // Set all of the fields in user
                    //retrieving name, might use in user.java
                    name = nameText.getText().toString();
                    //retrieving major
                    major = majorText.getText().toString();
                    //retrieving interest
                    interestStr = interests.getText().toString();
                    changeInformation();
                    toHome();

            }
        });

        Button mChangePasswordButton = (Button) findViewById(R.id.change_password_button);
        mChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Actually make this method check the passwords then change them in the database. Below is some of the old code
                newPass = changePass.getText().toString();
                newPassC = confirmPass.getText().toString();
                if (!(newPass.equals(newPassC))) {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords do not match";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (newPass.length() > 0 && newPass.length() < 4) {
                    Context context = getApplicationContext();
                    CharSequence text = "Password must be at least 4 characters";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {

                }
            }
        });


    }

    public void changeInformation() {
        user.setInterest(interestStr);
        user.setMajor(major);
        user.setName(name);
        /*if (newPass.length() > 0) {
            user.setPassword(newPass);
        }
        */
        localStore.setCurrentAccount(user);

        // Now we have to go to ProfileController to also update the API
        //Pass Credentials to profile controller for authentication

        //ProfileController.updateProfile(this.getApplicationContext(), this, interestStr, major, name, localStore.getCurrentAccount().getToken());
    }

    private void toHome() {
        Intent toHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(toHomeActivity);
    }
}
