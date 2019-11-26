package com.example.example.sewain;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class Main2Activity extends AppCompatActivity {
    String login_username;

    FragmentManager manager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    manager.popBackStack();
                    manager.beginTransaction()
                            .add(R.id.frmMain, new HomeFragment())
                            .addToBackStack("fragment")
                            .commit();
                    getSupportActionBar().setTitle("Home");
                    return true;
                case R.id.navigation_chat:
                    manager.popBackStack();
                    manager.beginTransaction()
                            .add(R.id.frmMain, new ChatFragment())
                            .addToBackStack("fragment")
                            .commit();
                    getSupportActionBar().setTitle("Chat");
                    return true;
                case R.id.navigation_history:
                    manager.popBackStack();
                    manager.beginTransaction()
                            .add(R.id.frmMain, new HistoryFragment())
                            .addToBackStack("fragment")
                            .commit();
                    getSupportActionBar().setTitle("History");
                    return true;
                case R.id.navigation_account:
                    manager.popBackStack();
                    manager.beginTransaction()
                            .add(R.id.frmMain, new AccountFragment())
                            .addToBackStack("fragment")
                            .commit();
                    getSupportActionBar().setTitle("Account");
//                    mTextMessage.setText(R.string.title_account);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        login_username = Preferences.getLoggedInUser(this.getBaseContext());
        Toast.makeText(this, "Hai " + login_username, Toast.LENGTH_LONG).show();

        manager.popBackStack();
        manager.beginTransaction()
                .add(R.id.frmMain, new HomeFragment())
                .addToBackStack("fragment")
                .commit();
        getSupportActionBar().setTitle("Home");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




}
