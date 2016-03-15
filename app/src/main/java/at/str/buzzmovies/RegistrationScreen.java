package at.str.buzzmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The RegistrationActivity page for new users to create an account
 */
public class RegistrationScreen extends AppCompatActivity {

    static String defaultEmail;
    static String defaultPass;
    EditText mEmail;
    EditText mPasswordView;
    EditText mConfirmPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        mEmail = (EditText) findViewById(R.id.Email_Register);
        mPasswordView = (EditText) findViewById(R.id.Password_Register);
        mConfirmPasswordView = (EditText) findViewById(R.id.Password_confirm);
        mEmail.setText(defaultEmail);
        mPasswordView.setText(defaultPass);
        defaultEmail = "";
        defaultPass = "";

        Button mCancelButton = (Button) findViewById(R.id.cancel_reg);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        //Submit Registration Button and action
        Button mRegisterButton = (Button) findViewById(R.id.submit_reg);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    /**
     * Directs the app back to the LoginActivity page
     */
    private void cancel() {
        Intent toLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(toLoginActivity);
    }
    private void register() {
        String email = mEmail.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        if (!(password.equals(confirmPassword))) {
            Context context = getApplicationContext();
            CharSequence text = "Passwords do not match";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (password.length() < 4) {
            Context context = getApplicationContext();
            CharSequence text = "Password must be at least 4 characters";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            User newUser = new User(email, password, "", "", "", "");
            localStore.setCurrentAccount(newUser);
            User user = (User) localStore.getCurrentAccount();
            Intent toHomeActivity = new Intent(this, HomeActivity.class);
            startActivity(toHomeActivity);
        }
    }
}
