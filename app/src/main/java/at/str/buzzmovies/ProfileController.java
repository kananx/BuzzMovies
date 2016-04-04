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

/**
 * Created by Gregory on 4/4/16
 */
public class ProfileController {


    public enum AccountType {
        USER("user"), ADMIN("admin");

        private final String text;
        private AccountType(String text) { this.text = text; }
        public String getValue() { return text; }
    }

    /**
     * Take in user credentials and checks them against the API. If the login attempt is successful, the currentUser singleton is set.
     * @param context Current application context. Pass in this.getApplicationContext();
     * @param callee reference to activity that called this function.
     * @param interestStr The interest to update the profile to.
     * @param major The major to update the profile to
     * @param name The name to update the profile to
     * @return true if the user logged in successfully, false if the the username or password is incorrect
     */
    public static void updateProfile(final Context context, final Activity callee, final String interestStr, final String major, final String name) {
        RequestQueue queue = VolleyQueue.getInstance(context).
                getRequestQueue();

        //TODO: Create api_updateProfile_route in the strings.xml file
        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_updateProfile_route);

        //TODO: Create the parameters list needed by the API. Below is my guess
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("interest", interestStr);
        parameters.put("major", major);
        parameters.put("name", name);

        final JSONObject params = new JSONObject(parameters);

        JsonObjectRequest loginRequest = new JsonObjectRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    //TODO: Change this method based on the response we will get for chaning the profile
                    // Note: the user object in localStore has already been updated. we could potentially do that after the response to make sure things are staying consistent?
                    // Ryan you and I can check this out later
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("login").equals("true")) {
                                String token = response.getString("token");
                                String name = response.getString("name");



                                if (response.getString("accountType").equals("user")) {
                                    localStore.setCurrentAccount(new User(email, token, name));
                                    Intent toHomeActivity = new Intent(callee, HomeActivity.class);
                                    callee.startActivity(toHomeActivity);
                                } else if (response.getString("accountType").equals("admin")) {
                                    localStore.setCurrentAccount(new Admin(email, token, name));
                                    Intent toAdminActivity = new Intent(callee, AdminActivity.class);
                                    callee.startActivity(toAdminActivity);
                                }


                            } else {
                                String errorMsg = response.getString("error");
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, errorMsg, duration);
                                toast.show();
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

    public static void register(final Context context, final Activity callee, final String email, final String password, AccountType type) {
        RequestQueue queue = VolleyQueue.getInstance(context).
                getRequestQueue();

        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_register_route);

        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("accountType", type.getValue());

        final JSONObject params = new JSONObject(parameters);

        JsonObjectRequest regRequest = new JsonObjectRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("login").equals("true")) {
                                String token = response.getString("token");

                                if (response.getString("accountType").equals("user")) {
                                    localStore.setCurrentAccount(new User(email, token, null));
                                    Intent toHomeActivity = new Intent(callee, HomeActivity.class);
                                    callee.startActivity(toHomeActivity);
                                } else if (response.getString("accountType").equals("admin")) {
                                    localStore.setCurrentAccount(new Admin(email, token, null));
                                    Intent toAdminActivity = new Intent(callee, AdminActivity.class);
                                    callee.startActivity(toAdminActivity);
                                }


                            } else {
                                String errorMsg = response.getString("error");
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, errorMsg, duration);
                                toast.show();
                            }

                        } catch (Exception e) {
                            Log.w("Login", "Register parse Failed");
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

        VolleyQueue.getInstance(context).addToRequestQueue(regRequest);
    }

}
