package at.str.buzzmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * handles the queue for HTTP requests via Volley
 */
public class VolleyQueue {
    private static VolleyQueue mInstance;
    private RequestQueue mRequestQueue;
    private final ImageLoader mImageLoader;
    private static Context mCtx;


    /**
     * creates the only queue for the request
     * @return instance of queue
     */
    public static VolleyQueue getInstance() {
        return mInstance;
    }

    /**
     * handles request
     * @param context current context
     */
    private VolleyQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
            new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap>
                        cache = new LruCache<>(20);

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }
            });
        }

    /**
     * synchronizes Volley Singleton
     * @param context current context
     * @return volley Singleton
     */
    public static synchronized VolleyQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyQueue(context);
        }
        return mInstance;
    }

    /**
     * gets request queue
     * @return request queue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * adds request to the queue
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * gets ImageLoader
     * @return image loader
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
