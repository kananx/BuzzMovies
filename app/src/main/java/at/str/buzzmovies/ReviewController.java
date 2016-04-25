package at.str.buzzmovies;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 4/25/16.
 */
public class ReviewController {

    public static void saveReview(final Context context, final Activity callee, final User user, final Review review) {
        String url = context.getString(R.string.api_base_url) + context.getString(R.string.api_review_route);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", user.getToken());
        parameters.put("imdbid", review.getMovie().getId());
        parameters.put("stars", Float.toString(review.getRating()));
        parameters.put("review", review.getReview());

        final JSONObject params = new JSONObject(parameters);

        JsonObjectRequest profileRequest = new JsonObjectRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            CharSequence text = context.getText(R.string.review_saved);
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            Log.d("Network", "Review updated successfully");
                        } catch (Exception e) {
                            Log.w("Network", "Review Failed");
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
}
