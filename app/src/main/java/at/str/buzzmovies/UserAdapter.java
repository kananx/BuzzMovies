package at.str.buzzmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Facilitates the creation of list of Users
 * For Admin purposes only
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final ArrayList<Account> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox mName;
        public ViewHolder(CheckBox v) {
            super(v);
            mName = v;
        }
    }

    /**
     * creates adapter for ArrayList of users
     * @param accounts list of accounts
     */
    public UserAdapter(ArrayList<Account> accounts) {
        mDataset = accounts;
    }

    /**
     * creates new RecylerView.ViewHolder
     * @param parent parent ViewHolder
     * @param viewType the type of viewHolder
     * @return userAdapter.ViewHolder to hold users
     */
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);

        return new ViewHolder((CheckBox) v);
    }

    /**
     * display data in specified location
     * @param holder and itemView
     * @param position position of item
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(mDataset.get(position).getName());
        holder.mName.setChecked(mDataset.get(position).getStatus().equals("banned"));
        holder.mName.setTag(mDataset.get(position));
    }

    /**
     *
     * @return number of users
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
