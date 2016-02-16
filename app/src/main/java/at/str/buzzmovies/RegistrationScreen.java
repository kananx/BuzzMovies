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
 * The registationActivity page for new users to create an account
 */
public class RegistrationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        Button mCancelButton = (Button) findViewById(R.id.cancel_reg);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        //Submit Registation Button and action
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
        EditText mPasswordView = (EditText) findViewById(R.id.Password_Register);
        EditText mConfirmPasswordView = (EditText) findViewById(R.id.Password_confirm);
        EditText mEmail = (EditText) findViewById(R.id.Email_Register);
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        String email = mEmail.getText().toString();
        if (!(password.equals(confirmPassword))) {
            Context context = getApplicationContext();
            CharSequence text = "Password do not match";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            User newUser = new User(email, password, null, null, null, null);
            ((ImportantData) this.getApplication()).addUser(email, newUser);
            ((ImportantData) this.getApplication()).setCurrentUser(newUser);
            User user = ((ImportantData) this.getApplication()).getCurrentUser();
            Intent toHomeActivity = new Intent(this, HomeActivity.class);
            startActivity(toHomeActivity);
        }
    }
}
