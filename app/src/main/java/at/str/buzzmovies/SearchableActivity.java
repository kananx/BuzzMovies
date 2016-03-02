package at.str.buzzmovies;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.app.SearchManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

public class SearchableActivity extends AppCompatActivity {

    private static final String TAG = SearchableActivity.class.getSimpleName();
    private TextView msearchResults;
    private final Gson gson = new Gson();
    private RequestQueue queue;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        msearchResults = (TextView) findViewById(R.id.resultTextView2);
        queue = Volley.newRequestQueue(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "in onNewIntent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(query);
            //msearchResults.setText(moviesInfo);
            //msearchResults.setText(responseString[0]);


        }
    }

    public void doSearchQuery(String query) {

        //this is the URL for our REST service
        String url = "http://omdbapi.com/?s=" + query;

        /*
            We expect to get back a JSON response.  Volley also has String responses.
            This is an async call, but all the threading is handled for us in the background
            We just need 2 callback functions.
                onResponse = this is called when the response actually comes back from server
                onError = this is called if there is any error in the response
         */
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        msearchResults.setText(resp.toString());
                        //printing first 500 chars of the response.  Only want to do this for debug

/*

                        //Now we parse the information.  Looking at the format, everything encapsulated in RestResponse object
                        JSONObject obj1 = null;
                        try {
                            obj1 = resp.getJSONObject("RestResponse");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        /*assert obj1 != null;
                        //From that object, we extract the array of actual data labeled result
                        JSONArray array = obj1.optJSONArray("result");
                        ArrayList<NetworkInfo.State> states = new ArrayList<>();
                        for(int i=0; i < array.length(); i++) {

                            try {
                                //for each array element, we have to create an object
                                JSONObject jsonObject = array.getJSONObject(i);
                                State s = new State();
                                assert jsonObject != null;
                                s.setName(jsonObject.optString("name"));
                                s.setA2Code(jsonObject.optString("alpha2_code"));
                                s.setA3Code(jsonObject.optString("alpha3_code"));
                                //save the object for later
                                states.add(s);


                            } catch (JSONException e) {
                                Log.d("VolleyApp", "Failed to get JSON object");
                                e.printStackTrace();
                            }
                        }
                        //once we have all data, then go to list screen
                        changeView(states);*/
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                        //show error on phone
                        msearchResults.setText(response);

                    }
                });
        //this actually queues up the async response with Volley
        queue.add(jsObjRequest);
    }

}


