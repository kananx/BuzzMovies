package at.str.buzzmovies;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Kananx on 3/8/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieViewHolder>{

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView year;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            year = (TextView)itemView.findViewById(R.id.year);
        }

    }


    ArrayList<Movie> movies;
    RVAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.title.setText(movies.get(i).title);
        movieViewHolder.year.setText(movies.get(i).year);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}
