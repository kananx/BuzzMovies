package at.str.buzzmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryan on 3/28/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<Account> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mName;
        public ViewHolder(CheckBox v) {
            super(v);
            mName = v;
        }
    }

    public UserAdapter(ArrayList<Account> accounts) {
        mDataset = accounts;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);

        return new ViewHolder((CheckBox) v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(mDataset.get(position).getName());
        holder.mName.setChecked(mDataset.get(position).getStatus().equals("banned"));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
