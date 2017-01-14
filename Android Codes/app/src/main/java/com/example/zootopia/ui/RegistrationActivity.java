package com.example.zootopia.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.RegisterService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class RegistrationActivity extends AppCompatActivity {
    public static RegistrationActivity instance = null;
    private Button registerButton;
    private Button cancelButton;

    private EditText emailEditText;
    private EditText nameEditText;
    private EditText passwdEditText;
    private EditText confirmEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registration_activity);
        instance = this;

        // initialize EditText
        emailEditText = (EditText) findViewById(R.id.registration_editText_email);

        nameEditText = (EditText) findViewById(R.id.registration_editText_name);

        passwdEditText = (EditText) findViewById(R.id.registration_editText_password);

        confirmEditText = (EditText) findViewById(R.id.registration_editText_confirm);

        // registration button
        registerButton = (Button) findViewById(R.id.registration_button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String passwd = passwdEditText.getText().toString();
                String confirm = confirmEditText.getText().toString();

                if (email.equals("") || name.equals("") || passwd.equals("") || confirm.equals("")) {
                    // create a new AlertDialog Builder
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegistrationActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.registerTitle);
                    builder.setMessage(R.string.registerEmptyError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else if (!passwd.equals(confirm)) {
                    // create a new AlertDialog Builder
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegistrationActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.registerTitle);
                    builder.setMessage(R.string.registerConfirmError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else {
                    RegisterTask task = new RegisterTask();
                    task.execute(email, passwd, name);
                }
            }
        });

        // cancel button
        cancelButton = (Button) findViewById(R.id.registration_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish activity
                RegistrationActivity.instance.finish();
            }
        });
    }

    class RegisterTask extends AsyncTask<String, Void, User> {

        private Exception exception;

        protected User doInBackground(String... params) {
            User user = RegisterService.register(params[0], params[1], params[2]);
            return user;
        }

        protected void onPostExecute(User user) {
            if (user != null) {
                UserContainer.user = user;
                UserContainer.chatroomList = new ArrayList<Chatroom>();
                // create an Intent to launch the Main Activity
                Intent mainActivity =
                        new Intent(RegistrationActivity.this, WeDiscuzAcitivity.class);
                startActivity(mainActivity);
                // finish activity
                instance.finish();
                LoginActivity.instance.finish();
            } else {
                // create a new AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(RegistrationActivity.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle(R.string.registerTitle);
                builder.setMessage(R.string.registerDuplicateError);
                builder.setPositiveButton(R.string.ok, null);
                builder.show();
            }
        }
    }
}
