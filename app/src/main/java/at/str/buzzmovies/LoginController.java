package at.str.buzzmovies;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 3/3/16.
 */
public class LoginController {



    /**
     * Take in user credentials and checks them against the API. If the login attempt is successful, the currentUser singleton is set.
     * @param context Current application context. Pass in this.getApplicationContext();
     * @param email Email address of the user
     * @param password Password of the user
     * @return true if the user logged in successfully, false if the the username or password is incorrect
     */
    public static void login(final Context context, final String email, final String password) {
        RequestQueue queue = VolleyQueue.getInstance(context).
                getRequestQueue();

        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_login_route);

        JsonObjectRequest loginRequest = new JsonObjectRequest
            (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    CharSequence text = response.toString();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent toHomeActivity = new Intent(context, HomeActivity.class);
                    //context.startActivity(toHomeActivity);
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

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            };
        };;

        VolleyQueue.getInstance(context).addToRequestQueue(loginRequest);


    }
}
