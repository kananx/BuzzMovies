package at.str.buzzmovies;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{

    private List<Movie> movies;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        MovieViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

    }

    MovieListAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder vh = new MovieViewHolder((TextView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.mTextView.setText(movies.get(position).getTitle());


        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MovieScreen.class);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~movieID?
                //intent.putExtra(MovieScreen.MOVIE_ID, ) );

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
