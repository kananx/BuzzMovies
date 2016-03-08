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


/**
 * Created by Kananx on 3/8/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieViewHolder>{

    private List<Movie> movies;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView year;
        public final View mView;
        public Movie mItem;

        MovieViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
            year = (TextView)itemView.findViewById(R.id.year);
        }

    }

    RVAdapter(ArrayList<Movie> movies){
        this.movies = movies;

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_search_results, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.title.setText(movies.get(i).getTitle());
        movieViewHolder.year.setText(movies.get(i).getYear());

        movieViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MovieScreen.class);
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~movieID?
                    intent.putExtra(MovieScreen.MOVIE_ID, movieViewHolder.mItem.getTitle());

                    context.startActivity(intent);
                }
            });

        };

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }










}
