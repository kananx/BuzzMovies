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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gregory on 4/4/16
 */
public class ProfileController {


    public enum AccountType {
        USER("user"), ADMIN("admin");

        private final String text;
        AccountType(String text) { this.text = text; }
        public String getValue() { return text; }
    }

    /**
     * Take in user credentials and checks them against the API. If the login attempt is successful, the currentUser singleton is set.
     * @param context Current application context. Pass in this.getApplicationContext();
     * @param callee reference to activity that called this function.
     * @param interestStr The interest to update the profile to.
     * @param major The major to update the profile to
     * @param name The name to update the profile to
     * true if the user logged in successfully, false if the the username or password is incorrect
     */
    public static void updateProfile(final Context context, final Activity callee, final String interestStr, final String major, final String name, final String token) {

        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_profile_route);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", token);
        parameters.put("name", name);
        parameters.put("major", major);
        parameters.put("interests", interestStr);

        final JSONObject params = new JSONObject(parameters);

        JsonObjectRequest profileRequest = new JsonObjectRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //TODO: Maybe do something else
                            Log.d("Network", "Profile updated successfully");
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
        VolleyQueue.getInstance(context).addToRequestQueue(profileRequest);
    }


    public static void getMajorsList(final Context context, final Activity callee) {
        final String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_get_majors_route);

        final JsonArrayRequest majorsRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<String> majorsArray = new ArrayList<String>();

                    for (int i = 0; i < response.length(); i++) {
                        final JSONObject majorsJSONArray = response.getJSONObject(i);
                        final String major = majorsJSONArray.getString("major");
                        majorsArray.add(major);
                    }
                    LocalStore.setMajors(majorsArray);
                    Log.i("Testing Major Response", "Majors Populated");
                } catch (JSONException e) {
                    Log.w(context.getString(R.string.admin_tag), "MajorList Parse Fail");
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Testing Major Response", "Request Failed");
            }
        }) {

        };

        VolleyQueue.getInstance(context).addToRequestQueue(majorsRequest);
    }
}
