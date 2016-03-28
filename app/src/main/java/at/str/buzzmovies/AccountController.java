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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 3/28/16.
 */
public class AccountController {
    public static void getAccountList(final Context context, final Activity callee) {
        RequestQueue queue = VolleyQueue.getInstance(context).getRequestQueue();

        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_get_accounts_route);


        JsonArrayRequest accountsRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject account = response.getJSONObject(i);
                                String email = account.getString("email");
                                String name = account.getString("name");
                                String status = account.getString("status");
                                localStore.addAccount(new Account(email, null, status, name));
                            }

                            Log.d("Admin", "AccountList Populated");

                            ((UserListCallback) callee).populateUserList(localStore.getAccounts());
                        } catch (Exception e) {
                            Log.w("Admin", "AccountList Parse Fail");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Admin", error.getMessage());
                        CharSequence text = context.getText(R.string.network_error_try);
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {


        };

        VolleyQueue.getInstance(context).addToRequestQueue(accountsRequest);


    }
}
