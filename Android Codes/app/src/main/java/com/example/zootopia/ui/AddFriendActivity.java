package com.example.zootopia.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.dblayout.DbFriendsHandler;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.FriendService;
import com.example.zootopia.ws.remote.ProfileService;
import com.example.zootopia.ws.remote.RegisterService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class AddFriendActivity extends AppCompatActivity {
    public static AddFriendActivity instance = null;

    private EditText searchView;
    private Button addFriendButton;
    private Button searchFriendButton;

    private ImageView imageView;
    private TextView emailTextView;
    private TextView nameTextView;
    private TextView genderTextView;
    private TextView descriptionTextView;
    private EditText emailEditText;
    private EditText nameEditText;
    private EditText genderEditText;
    private EditText descriptionEditText;

    private User newFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_add_friend_activity);
        instance = this;

        // initialize SearchView
        searchView = (EditText) findViewById(R.id.add_friend_editText_search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide hint when user input
                searchView.setHint(null);
            }
        });

        // initialize button
        searchFriendButton = (Button) findViewById(R.id.add_friend_button_search);
        searchFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get input email
                String email = searchView.getText().toString();
                if (email.equals("")) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(AddFriendActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.addFriendTitle);
                    builder.setMessage(R.string.addFriendError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else if (email.equals(UserContainer.user.getEmail())) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(AddFriendActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.addFriendTitle);
                    builder.setMessage(R.string.addFriendError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else {
                    boolean flag = true;
                    for (User friend : UserContainer.user.getFriendList()) {
                        if (email.equals(friend.getEmail())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        SearchTask task = new SearchTask();
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, email);
                    } else {
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(AddFriendActivity.this);

                        // set dialog title & message, and provide Button to dismiss
                        builder.setTitle(R.string.addFriendTitle);
                        builder.setMessage(R.string.addFriendError);
                        builder.setPositiveButton(R.string.ok, null);
                        builder.show();
                    }
                }

            }
        });

        addFriendButton = (Button) findViewById(R.id.add_friend_button_add);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newFriend == null) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(AddFriendActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.addFriendTitle);
                    builder.setMessage(R.string.addFriendError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else {
                    // update remote database
                    AddTask task = new AddTask();
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UserContainer.user.getUserID(), newFriend.getUserID());
                }

            }
        });

        // initail ImageView
        imageView = (ImageView) findViewById(R.id.add_friend_imageView);
        imageView.setVisibility(View.GONE);

        // initial TextView
        emailTextView = (TextView) findViewById(R.id.add_friend_textView_email);
        emailTextView.setVisibility(View.GONE);

        nameTextView = (TextView) findViewById(R.id.add_friend_textView_name);
        nameTextView.setVisibility(View.GONE);

        genderTextView = (TextView) findViewById(R.id.add_friend_textView_gender);
        genderTextView.setVisibility(View.GONE);

        descriptionTextView = (TextView) findViewById(R.id.add_friend_textView_description);
        descriptionTextView.setVisibility(View.GONE);

        // initial EditText
        emailEditText = (EditText) findViewById(R.id.add_friend_editText_email);
        emailEditText.setVisibility(View.GONE);

        nameEditText = (EditText) findViewById(R.id.add_friend_editText_name);
        nameEditText.setVisibility(View.GONE);

        genderEditText = (EditText) findViewById(R.id.add_friend_editText_gender);
        genderEditText.setVisibility(View.GONE);

        descriptionEditText = (EditText) findViewById(R.id.add_friend_editText_description);
        descriptionEditText.setVisibility(View.GONE);
    }

    class SearchTask extends AsyncTask<String, Void, User> {
        protected User doInBackground(String... params) {
            User user = ProfileService.getUserProfile(params[0]);
            return user;
        }

        protected void onPostExecute(User user) {
            if (user != null) {
                // set new friend
                newFriend = user;
                // reset visibility
                imageView.setVisibility(View.VISIBLE);
                emailTextView.setVisibility(View.VISIBLE);
                nameTextView.setVisibility(View.VISIBLE);
                genderTextView.setVisibility(View.VISIBLE);
                descriptionTextView.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);
                nameEditText.setVisibility(View.VISIBLE);
                genderEditText.setVisibility(View.VISIBLE);
                descriptionEditText.setVisibility(View.VISIBLE);
                // reset content
                emailEditText.setText(user.getEmail());
                nameEditText.setText(user.getName());
                if (user.getGender().equals("M"))
                    genderEditText.setText("Male");
                else if (user.getGender().equals("F"))
                    genderEditText.setText("Female");
                else
                    genderEditText.setText("Other");
                descriptionEditText.setText(user.getDescription());
                // set enable
                emailEditText.setEnabled(false);
                nameEditText.setEnabled(false);
                genderEditText.setEnabled(false);
                descriptionEditText.setEnabled(false);

            } else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AddFriendActivity.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle(R.string.addFriendTitle);
                builder.setMessage(R.string.addFriendError);
                builder.setPositiveButton(R.string.ok, null);
                builder.show();
            }
        }
    }

    class AddTask extends AsyncTask<Integer, Void, User> {

        private Exception exception;

        protected User doInBackground(Integer... params) {
            User newUser = FriendService.addFriend(params[0], params[1]);
            return newUser;
        }

        protected void onPostExecute(User newUser) {
            // update local data object
            UserContainer.user.addFriend(newUser);
            // update local database
            DbFriendsHandler.insertFriend(newUser);
            // create a toast
            Toast.makeText(getApplicationContext(), "Add friend successfully!", Toast.LENGTH_SHORT).show();
            // finish current activity
            AddFriendActivity.instance.finish();
            WeDiscuzAcitivity.instance.recreate();
        }
    }
}
