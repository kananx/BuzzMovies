package at.str.buzzmovies;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Move List Adapter class that extends RecyclerView
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{
    public final static String MOVIE_POSITION = "at.str.buzzmovies.MESSAGE";

    private List<Movie> movies;

    /**
     * Movie View Holder Class
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public Movie movie;
        public int position;
        //public IMyViewHolderClicks mListener;

        MovieViewHolder(TextView v) {
            super(v);
            v.setClickable(true);
            v.setOnClickListener(this);
            //mListener = listener;
            mTextView = v;
        }

        /**
         * Onclick method
         * @param v view v
         */
        @Override
        public void onClick(View v) {
            Log.d("on click", "clicked");
        }

        /*
        public static interface IMyViewHolderClicks {
            public void onMovieClick(View caller);
        }*/



    }

    MovieListAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    /**
     * getItemCount method
     * @return number of movies
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * MovieListAdapter Class that takes in parent and viewType
     * @param parent parent of type viewgroup
     * @param viewType viewtype variable of int
     * @return returns a movieListAdapter.MovieViewHolder object
     */
    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder vh = new MovieViewHolder((TextView) v);
        return vh;
    }

    /**
     * OnBindViewHolder class method
     * @param holder movie view holder object
     * @param position final int object of the position
     */
    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.mTextView.setText(movies.get(position).getTitle());
        holder.movie = movies.get(position);
        holder.position = position;


        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Log.d("OnMovieListClick","Movie Position Clicked: " + holder.position);
                Intent intent = new Intent(context, MovieScreen.class);
                intent.putExtra(MOVIE_POSITION, holder.position);
                context.startActivity(intent);
            }
        });


    }
/*
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
   */
}
