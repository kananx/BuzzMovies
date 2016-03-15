package at.str.buzzmovies;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toolbar;


import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Home activity page that allows the user to interact with available movies.
 * @author Delicious 3.14
 * @verion 1.0
 * @since 02-13-2016
 */
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar action_toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setActionBar(action_toolbar);


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
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getMenuInflater().inflate(R.menu.options_menu, optionMenu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) optionMenu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overflow_logout:
                logout();
                return true;
            case R.id.overflow_profile:
                profile();
                return true;
            case R.id.search:
                //onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Handles any intents passed into this activity
     * @param intent the intent that started this activity or was passed into it
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            omdbQuery(query);
        }
    }

    /**
     * Directs app to the LoginActivity page.
     */
    private void logout() {
        Intent toLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(toLoginActivity);
    }

    /**
     * Directs app to the ProfileActivity page.
     */
    private void profile() {
        Intent toProfileActivity = new Intent(this, ProfileActivity.class);
        startActivity(toProfileActivity);
    }

    public void omdbQuery(String query) {

        //this is the URL for our REST service
        String url = "http://omdbapi.com/?type=movie&s=" + query;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (com.android.volley.Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
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
                            Log.i("Network", "Movies Found: " + localStore.getMovies().size());



                        } catch (Exception e) {
                            Log.e("Network", "Error Parsing JSON");
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Network","VolleyError" + error.getMessage());

                    }
                });

        VolleyQueue.getInstance(this.getApplicationContext()).addToRequestQueue(jsObjRequest);
    }


}
