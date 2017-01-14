package com.example.zootopia.ui;

import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.dblayout.DatabaseConnector;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.ChatService;
import com.example.zootopia.ws.remote.FriendService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */

public class WeDiscuzAcitivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static WeDiscuzAcitivity instance = null;

    private RadioGroup radioGroup;
    private FriendsFragment friendsFragment;
    private ChatroomFragment chatroomFragment;
    private TextView nameTextView;
    private TextView emailTextView;

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

        View headerView = navigationView.getHeaderView(0);
        nameTextView = (TextView) headerView.findViewById(R.id.menu_textView_name);
        nameTextView.setText(UserContainer.user.getName());

        emailTextView = (TextView) headerView.findViewById(R.id.menu_textView_email);
        emailTextView.setText(UserContainer.user.getEmail());

        // Initialize fragment
        initialFragment();

        // start backgroud task
        ResponseToChatRequestTask task = new ResponseToChatRequestTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UserContainer.user.getUserID());
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
            User user = UserContainer.user;
            // create an Intent to launch the Profile Activity
            Intent profileActivity =
                    new Intent(WeDiscuzAcitivity.this, ProfileActivity.class);
            profileActivity.putExtra(ProfileViewActivity.FRIEND_NAME, user.getName());
            profileActivity.putExtra(ProfileViewActivity.FRIEND_EMAIL, user.getEmail());
            profileActivity.putExtra(ProfileViewActivity.FRIEND_GENDER, user.getGender());
            profileActivity.putExtra(ProfileViewActivity.FRIEND_DESCRIPTION, user.getDescription());
            startActivity(profileActivity);
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

    class ResponseToChatRequestTask extends AsyncTask<Integer, Void, Void> {

        private Exception exception;

        protected Void doInBackground(Integer... params) {
            while (true) {
                try {
                    Thread.sleep(10 * 1000);
                    JSONObject request = ChatService.getChatRequest(params[0]);
                    if (request != null) {
                        boolean availibility = request.getBoolean("isBusy");
                        if (availibility) {
                            Integer channelid = request.getInt("room_id");
                            String type = request.getString("type");
                            String name = request.getString("room_name");
                            Intent intent = new Intent(WeDiscuzAcitivity.instance, ChannelActivity.class);
                            if (type.equals("video")) {
                                // Video Chat
                                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VIDEO);
                                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelid.toString());
                                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, name);
                                startActivity(intent);
                            } else {
                                // Voice Chat
                                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VOICE);
                                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelid.toString());
                                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, name);
                                startActivity(intent);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
