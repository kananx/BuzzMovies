package at.str.buzzmovies;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gregory on 4/26/2016.
 */
public class HomeController {

    public static Movie getMovieByID(final String imdbID, final Context context, final Activity callee) {
        String url = "http://www.omdbapi.com/?i=" + imdbID + "&plot=short&r=json";
        LocalStore.clearMovies();

        JsonObjectRequest movieIDRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        try {
                            String movieTitle = "";

                            for (int i = 0; i < resp.length(); i++) {
                                movieTitle = resp.getString("Title");
                                int year = Integer.parseInt(resp.getString("Year"));
                                Movie movieToAdd = new Movie(movieTitle, "", year, "", imdbID);
                                LocalStore.setCurrentRecommendedMovie(movieToAdd);
                                LocalStore.addMovie(movieToAdd);
                                ((HomeActivity) callee).updateRecommendationTextBox();

                            }
                            Log.i("Network", movieTitle);

                        } catch (Exception e) {
                            Log.e("Network", "Error Parsing JSON");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String response = "JSon Request Failed!!";
                        //show error on phone
                    }
                });

        VolleyQueue.getInstance(context).addToRequestQueue(movieIDRequest);
        Movie curMovie = LocalStore.getCurrentRecommendedMovie();
        return curMovie;


    }

    public static Movie getMovieRecommendation(final Context context, final Activity callee) {
        String token = LocalStore.getCurrentAccount().getToken();
        final String url = context.getString(R.string.api_base_url) + "/recommendation?token=" + token;


        final JsonObjectRequest recommendationRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String movieID = "";
                    for (int i = 0; i < response.length(); i++) {
                        movieID = response.getString("movie");
                    }
                    Log.i("Testing Rec Response", movieID);
                    getMovieByID(movieID, context, callee);

                } catch (JSONException e) {
                    Log.w("Testing Rec Response", "Movie ID Parse Fail");
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Testing Rec Response", "Request Failed");
            }
        }) {

        };

        VolleyQueue.getInstance(context).addToRequestQueue(recommendationRequest);
        return LocalStore.getCurrentRecommendedMovie();
    }

}
