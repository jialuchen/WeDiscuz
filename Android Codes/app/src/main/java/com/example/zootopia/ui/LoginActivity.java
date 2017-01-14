package com.example.zootopia.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.dblayout.DatabaseConnector;
import com.example.zootopia.dblayout.DbFriendsHandler;
import com.example.zootopia.dblayout.DbMyRoomsHandler;
import com.example.zootopia.dblayout.DbRoomMembersHandler;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.LoginAuthenticationService;

import java.util.List;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class LoginActivity extends AppCompatActivity {
    public static LoginActivity instance = null;
    private Button loginButton;
    private Button registrationButton;
    private EditText emailEditText;
    private EditText passwdEditText;
    private TextView forgetPasswdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login_activity);
        instance = this;

        // login button
        loginButton = (Button) findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String passwd = passwdEditText.getText().toString();
                if (email.equals("") || passwd.equals("")) {
                    // create a new AlertDialog Builder
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(LoginActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.loginTitle);
                    builder.setMessage(R.string.loginEmptyError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else {
                    LoginTask task = new LoginTask();
                    task.execute(email, passwd);
                }
            }
        });

        // registration button
        registrationButton = (Button) findViewById(R.id.login_button_registration);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an Intent to launch the Registration Activity
                Intent registrationActivity =
                        new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationActivity);
            }
        });

        // Email EditText
        emailEditText = (EditText) findViewById(R.id.login_editText_email);
        emailEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide hint when user input
                emailEditText.setHint(null);
            }
        });

        // Password EditText
        passwdEditText = (EditText) findViewById(R.id.login_editText_password);
        passwdEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide hint when user input
                passwdEditText.setHint(null);
            }
        });

        // Forget password TextView
        forgetPasswdTextView = (TextView) findViewById(R.id.login_textView_forgetPassword);
        forgetPasswdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an Intent to launch the Registration Activity
                Intent forgetPasswdActivity =
                        new Intent(LoginActivity.this, ForgetPasswdActivity.class);
                startActivity(forgetPasswdActivity);
            }
        });
        // initial database
        DatabaseConnector databaseConnector = new DatabaseConnector(LoginActivity.instance);
    }

    public void initialData(User user) {
        // put into container
        UserContainer.user = user;
    }

    class LoginTask extends AsyncTask<String, Void, User> {

        private Exception exception;

        protected User doInBackground(String... params) {
            User user = LoginAuthenticationService.loginAuthentication(params[0], params[1]);
            List<Chatroom> chatroomList = LoginAuthenticationService.getChatroomList(user);
            UserContainer.chatroomList = chatroomList;
            return user;
        }

        protected void onPostExecute(User user) {
            // if authentication failed
            if (user == null) {
                // create a new AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(LoginActivity.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle(R.string.authenticationTitle);
                builder.setMessage(R.string.authenticationError);
                builder.setPositiveButton(R.string.ok, null);
                builder.show();
            } else {
                // if authentication succeed
                // initial data
                initialData(user);

                // write to local database
                // table friends
                for (User friend : UserContainer.user.getFriendList()) {
                    System.out.println(friend);
                    DbFriendsHandler.insertFriend(friend);
                }
                // table chatrooms
                for (Chatroom room : UserContainer.chatroomList) {
                    System.out.println(room);
                    DbMyRoomsHandler.insertRoom(room);
                }
                // table chatroom_members
                for (Chatroom room : UserContainer.chatroomList) {
                    for (User member : room.getMemberList()) {
                        DbRoomMembersHandler.insertRoomMember(room.getRoomID(), member);
                    }
                }

                // create an Intent to launch the Wediscuz Activity
                Intent mainActivity =
                        new Intent(LoginActivity.this, WeDiscuzAcitivity.class);
                startActivity(mainActivity);
                LoginActivity.instance.finish();
            }
        }
    }
}
