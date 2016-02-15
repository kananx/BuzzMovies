package at.str.buzzmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    /**
     * Directs the App back to the home activity page
     */
    private void register() {
        Intent toProfileActivity = new Intent(this, ProfileActivity.class);
        startActivity(toProfileActivity);
    }
}
