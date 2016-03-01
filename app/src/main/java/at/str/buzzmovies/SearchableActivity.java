package at.str.buzzmovies;

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

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

public class SearchableActivity extends AppCompatActivity {

    //private static final String TAG = SearchableActivity.class.getSimpleName();
//    private OkHttpClient client = new OkHttpClient();
//    private TextView msearchResults;
//    private static String content = "Tiera";
//    final String[] responseString = new String[1];
    //private final Gson gson = new Gson();
    private RequestQueue queue;
    //Hold result from REST call
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //msearchResults = (TextView) findViewById(R.id.resultTextView2);
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
        //Log.i(TAG, "in onNewIntent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(query);
            //msearchResults.setText(content);
            //msearchResults.setText(responseString[0]);


        }
    }

//    public void doSearchQuery(String query) {
//        String url = "http://omdbapi.com/?t=" + query;
//        //String results = "doSearchQuery";
//        try {
//            //run(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//            results = e.toString();
//        }
//        //return results;
    public void doSearchQuery(String query) {
        String url = "http://omdbapi.com/?t=" + query;
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
                        response = resp.toString();
                        //printing first 500 chars of the response.  Only want to do this for debug
                        //TextView view = (TextView) findViewById(R.id.textView2);
                        //view.setText(response.substring(0, 500));

                        //Now we parse the information.  Looking at the format, everything encapsulated in RestResponse object
                        JSONObject obj1 = null;
                        /*try {
                            obj1 = resp.getJSONObject();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        assert obj1 != null;
                        //From that object, we extract the array of actual data labeled result
                        JSONArray array = obj1.optJSONArray("result");

                        for(int i=0; i < array.length(); i++) {
*/
                        ArrayList<Movie> movies = new ArrayList<>();
                            try {
                                //for each array element, we have to create an object

                                JSONObject jsonObject = resp;
                                Movie movie = new Movie(null,null,null);
                                assert jsonObject != null;
                                movie.setTitle(jsonObject.optString("Title"));
                                movie.setPlot(jsonObject.optString("Plot"));
                                movie.setGenre(jsonObject.optString("Genre"));
                                //save the object for later
                                movies.add(movie);


                            }
                            catch (Exception e) {
                                Log.d("VolleyApp", "Failed to get JSON object");
                                e.printStackTrace();
                            }
                        //}
                        //once we have all data, then go to list screen
                        toResults(movies);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                        //show error on phone
                        //TextView view = (TextView) findViewById(R.id.textView2);
                        //view.setText(response);
                    }
                });
        //this actually queues up the async response with Volley
        queue.add(jsObjRequest);
    }
        private void toResults(ArrayList<Movie> movies) {
            Intent intent = new Intent(this, itemListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movies);
            intent.putExtras(bundle);
            startActivity(intent);

        }



}


