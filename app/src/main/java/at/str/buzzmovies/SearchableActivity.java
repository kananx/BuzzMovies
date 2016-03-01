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
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

public class SearchableActivity extends AppCompatActivity {

    private static final String TAG = SearchableActivity.class.getSimpleName();
    private OkHttpClient client = new OkHttpClient();
    private TextView msearchResults;
    private static String content = "Tiera";
    final String[] responseString = new String[1];
    private final Gson gson = new Gson();
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        msearchResults = (TextView) findViewById(R.id.resultTextView2);




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
            msearchResults.setText(content);
            //msearchResults.setText(responseString[0]);


        }
    }

    public void doSearchQuery(String query) {
        String url = "http://omdbapi.com/?t=" + query;
        String results = "doSearchQuery";
        try {
            run(url);
        } catch (Exception e) {
            e.printStackTrace();
            results = e.toString();
        }
        //return results;



    }

    public void run(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }

    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }
}
