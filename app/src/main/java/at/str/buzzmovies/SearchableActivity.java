package at.str.buzzmovies;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import org.json.JSONObject;


/**
 * handles the logic for creating a search query using the search bar
 */
public class SearchableActivity extends AppCompatActivity {

    private static final String TAG = SearchableActivity.class.getSimpleName();
    private TextView msearchResults;
    private final Gson gson = new Gson();
    private String response;

    private RecyclerView mRecylerView;


    /**
     * creates the UI elements
     * @param savedInstanceState current state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RequestQueue queue = Volley.newRequestQueue(this);


        handleIntent(getIntent());

        setContentView(R.layout.activity_search_results);
        mRecylerView = (RecyclerView) findViewById(R.id.searchResultsRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLayoutManager);
    }

    /**
     * delegates task when new intent is passed
     * @param intent operation to be performed
     */
    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "in onNewIntent");
        setIntent(intent);
        handleIntent(intent);
    }


    /**
     * creates search query
     * @param intent operation to be performed
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            query = query.replaceAll(" ", "+");
            doSearchQuery(query);
        }
    }

    /**
     * populates the recycler view of Movies that match search query
     */
    private void populateMovieList() {
        RecyclerView.Adapter mAdapter = new MovieListAdapter(LocalStore.getMovies());
        mRecylerView.setAdapter(mAdapter);
    }

    /**
     * talks to OMDB API with current search query
     * @param query the keywords that are being searched
     */
    private void doSearchQuery(String query) {

        //this is the URL for our REST service
        String url = "http://omdbapi.com/?type=movie&s=" + query;
        LocalStore.clearMovies();

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
                                LocalStore.addMovie(new Movie(title,"",year,"",id));
                            }
                            Log.i("Network", "Movies Found: " + LocalStore.getMovies().size());

                        } catch (Exception e) {
                            Log.e("Network", "Error Parsing JSON");
                        }

                        populateMovieList();

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


