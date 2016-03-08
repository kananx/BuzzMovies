package at.str.buzzmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginController {



    /**
     * Take in user credentials and checks them against the API. If the login attempt is successful, the currentUser singleton is set.
     * @param context Current application context. Pass in this.getApplicationContext();
     * @param callee reference to activity that called this function.
     * @param email Email address of the user
     * @param password Password of the user
     * @return true if the user logged in successfully, false if the the username or password is incorrect
     */
    public static void login(final Context context, final Activity callee, final String email, final String password) {
        RequestQueue queue = VolleyQueue.getInstance(context).
                getRequestQueue();

        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_login_route);

        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("email", email);
        parameters.put("password", password);

        final JSONObject params = new JSONObject(parameters);

        JsonObjectRequest loginRequest = new JsonObjectRequest
            (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("login").equals("true")) {
                            String token = response.getString("token");
                            String name = response.getString("name");

                            localStore.setCurrentAccount(new User(email, token, name));

                            Intent toHomeActivity = new Intent(callee, HomeActivity.class);
                            callee.startActivity(toHomeActivity);

                        } else {

                        }

                    } catch (Exception e) {
                        Log.w("Login", "Login parse Failed");
                    }



                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    CharSequence text = context.getText(R.string.network_error_try);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }) {


        };

        VolleyQueue.getInstance(context).addToRequestQueue(loginRequest);


    }
}
