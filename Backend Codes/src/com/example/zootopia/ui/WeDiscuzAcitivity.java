package com.example.zootopia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */

public class WeDiscuzAcitivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static WeDiscuzAcitivity instance =null;

    private RadioGroup radioGroup;
    private FriendsFragment friendsFragment;
    private ChatroomFragment chatroomFragment;
    private RecentContactFragment recentFragment;
    private MailboxFragment mailboxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_wediscuz_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        instance = this;

        // Initialize navigation bar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize fragment
        initialFragment();
    }

    /**
     * initialFragment - initialize Fragment of the activity
     */
    public void initialFragment() {
        friendsFragment = new FriendsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.wediscuz_frameLayout, friendsFragment)
                .commit();
        radioGroup = (RadioGroup) findViewById(R.id.wediscuz_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            switch (checkedId) {
                case R.id.wediscuz_radioButton_friends:
                    if (friendsFragment == null) {
                        friendsFragment = new FriendsFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.wediscuz_frameLayout, friendsFragment).commit();
                    break;
                case R.id.wediscuz_radioButton_chatroom:
                    if (chatroomFragment == null) {
                        chatroomFragment = new ChatroomFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.wediscuz_frameLayout, chatroomFragment).commit();
                    break;
                case R.id.wediscuz_radioButton_recentContact:
                    if (recentFragment == null) {
                        recentFragment = new RecentContactFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.wediscuz_frameLayout, recentFragment).commit();
                    break;
                case R.id.wediscuz_radioButton_mailbox:
                    if (mailboxFragment == null) {
                        mailboxFragment = new MailboxFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.wediscuz_frameLayout, mailboxFragment).commit();
                    break;
                default:
                    break;
            }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.we_discuz_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // create an Intent to launch the Profile Activity
            Intent profileActivity =
                    new Intent(WeDiscuzAcitivity.this, ProfileActivity.class);
            startActivity(profileActivity);
        } else if (id == R.id.nav_calendar) {
            // create an Intent to launch the Calendar Activity
            Intent calendarActivity =
                    new Intent(WeDiscuzAcitivity.this, CalendarActivity.class);
            startActivity(calendarActivity);
        } else if (id == R.id.nav_setting) {
            // create an Intent to launch the Setting Activity
            Intent settingActivity =
                    new Intent(WeDiscuzAcitivity.this, SettingActivity.class);
            startActivity(settingActivity);
        } else if (id == R.id.nav_help) {
            // create an Intent to launch the Help Activity
            Intent helpActivity =
                    new Intent(WeDiscuzAcitivity.this, HelpActivity.class);
            startActivity(helpActivity);
        } else if (id == R.id.nav_logout) {
            // create an Intent to launch the Login Activity
            Intent loginActivity =
                    new Intent(WeDiscuzAcitivity.this, LoginActivity.class);
            startActivity(loginActivity);
            WeDiscuzAcitivity.instance.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
