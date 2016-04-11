package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        Button mCancelLoginButton = (Button) findViewById(R.id.cancel_login_button);
        mCancelLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordView.setText("");
                mEmailView.setText("");
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                toRegister(email, password);
            }
        });
    }

    /**
     * Sends the app to the RegiserActivity.
     * @param email contents of email textfield
     * @param password contents of password textfield
     */
    private void toRegister(String email, String password) {
        RegistrationScreen.defaultEmail = email;
        RegistrationScreen.defaultPass = password;
        Intent toRegisterActivity = new Intent(this, RegistrationScreen.class);
        startActivity(toRegisterActivity);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //Pass Credentials to login controller for authentication
            LoginController.login(this.getApplicationContext(), this, email, password);
        }
    }

    /**
     * Checks if the email has an @ sign.
     * @param email The input email address from the text field
     * @return True/False if the email is valid
     */
    private boolean isEmailValid(String email) {
        //TODO: Replace this a regex match
        return email.contains("@");
    }

    /**
     * Check if the password satisfies our requirements (currently length > 3)
     * @param password The password input into the field
     * @return True/False whether or not the password is valid
     */
    private boolean isPasswordValid(String password) {
        //Password is checked server-side.
        return password.length() > 0;
    }

}

