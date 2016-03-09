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
        //setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        }
    }

    public void doSearchQuery(String query) {

        //this is the URL for our REST service
        String url = "http://omdbapi.com/?type=movie&s=" + query;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        try {
                            JSONArray results = resp.getJSONArray("Search");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject current = results.getJSONObject(i);
                                String title = current.getString("Title");
                                int year = Integer.parseInt(current.getString("Year"));
                                String id = current.getString("imdbID");
                                localStore.addMovie(new Movie(title,"",year,"",id));
                            }
                            Log.i("Network","Movies Found: " + localStore.getMovies().size());

                            Intent intent = new Intent(SearchableActivity.this, SearchResultsActivity.class);
                            startActivity(intent);

                        } catch (Exception e) {
                            Log.e("Network", "Error Parsing JSON");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                        //show error on phone
                        msearchResults.setText(response);

                    }
                });

        VolleyQueue.getInstance(this.getApplicationContext()).addToRequestQueue(jsObjRequest);
    }

}


