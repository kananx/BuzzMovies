package at.str.buzzmovies;
import java.util.ArrayList;


/**
 * Created by Kananx on 3/8/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    ArrayList<Review> reviews;

    RVAdapter(ArrayList<Review> reviews){
        this.reviews = reviews;
    }

}
