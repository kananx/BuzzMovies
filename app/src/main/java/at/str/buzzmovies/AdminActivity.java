package at.str.buzzmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements UserListCallback{
    private RecyclerView mUserList;
    private RecyclerView.Adapter mUserAdapter;
    private RecyclerView.LayoutManager mUserListLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mUserList = (RecyclerView) findViewById(R.id.user_list);
        mUserList.setHasFixedSize(true);

        mUserListLayout = new LinearLayoutManager(this);
        mUserList.setLayoutManager(mUserListLayout);

        AccountController.getAccountList(this.getApplicationContext(), this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getMenuInflater().inflate(R.menu.admin_options_menu, optionMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overflow_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Directs app to the LoginActivity page.
     */
    private void logout() {
        localStore.setCurrentAccount(null);
        Intent toLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(toLoginActivity);
    }

    public void populateUserList(ArrayList<Account> accounts) {
        Log.i("Admin", "UserList Callback");
        mUserAdapter = new UserAdapter(accounts);
        mUserList.setAdapter(mUserAdapter);
    }

    public void handleBanAccountClick(View view) {
        Account account = (Account) view.getTag();

        if (account.getStatus().equals("banned")) {
            account.setStatus("active");
        } else if (account.getStatus().equals("active")) {
            account.setStatus("banned");
        }

        AccountController.setAccountStatus(this.getApplicationContext(), account);
    }
}
