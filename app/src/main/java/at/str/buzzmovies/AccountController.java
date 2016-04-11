package at.str.buzzmovies;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 3/28/16.
 */
public class AccountController {

    /**
     * Fetches list of accounts from server
     * @param context context of current application
     * @param callee Activity calling the function
     */
    public static void getAccountList(final Context context, final Activity callee) {
        final String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_get_accounts_route);

        final JsonArrayRequest accountsRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            LocalStore.clearAccounts();
                            for (int i = 0; i < response.length(); i++) {
                                final JSONObject account = response.getJSONObject(i);
                                final String email = account.getString("email");
                                final String name = account.getString("name");
                                final String status = account.getString("status");
                                LocalStore.addAccount(new Account(email, null, status, name));
                            }
                            Log.d(context.getString(R.string.admin_tag), "AccountList Populated");
                            ((UserListCallback) callee).populateUserList(LocalStore.getAccounts());
                        } catch (JSONException e) {
                            Log.w(context.getString(R.string.admin_tag), "AccountList Parse Fail");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(context.getString(R.string.admin_tag), error.getMessage());
                        final CharSequence text = context.getText(R.string.network_error_try);
                        final int duration = Toast.LENGTH_SHORT;
                        final Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {

        };

        VolleyQueue.getInstance(context).addToRequestQueue(accountsRequest);
    }

    /**
     * Updates account status on server
     * @param context context of current application
     * @param account account to be affected by the change
     */
    public static void setAccountStatus(final Context context, Account account) {

        final String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_get_accounts_route);

        final Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("email", account.getEmail());
        parameters.put("status", account.getStatus());

        final JSONObject params = new JSONObject(parameters);

        final JsonObjectRequest updateStatus = new JsonObjectRequest(Request.Method.POST,
                url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(context.getString(R.string.admin_tag), "Status Update Successful");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        final CharSequence text = context.getText(R.string.network_error_try);
                        final int duration = Toast.LENGTH_SHORT;
                        final Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {

        };

        VolleyQueue.getInstance(context).addToRequestQueue(updateStatus);
    }

    /**
     * The method to test the validity of an entered password - NOT if it is the correct password for an account
     * @param password The password that was entered into the text field
     * @return True is the password matches the requirements, False if it doesn't.
     */
    public static boolean isPasswordValid2(String password) {
        if ((password != null) && (password.length() >= 7)) {
            //Regex match for an upper and lowercase letter
            return password.matches("^(?=.*[a-z])(?=.*[A-Z]).+$");
        } else {
            //Remember that, if this fails to display a toast
            return false;
        }
    }
}