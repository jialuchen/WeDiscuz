package com.example.zootopia.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.ProfileService;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class ProfileActivity extends AppCompatActivity {
    public static ProfileActivity instance = null;

    public final static String FRIEND_NAME = "FRIEND_NAME";
    public final static String FRIEND_EMAIL = "FRIEND_EMAIL";
    public final static String FRIEND_GENDER = "FRIEND_GENDER";
    public final static String FRIEND_DESCRIPTION = "FRIEND_DESCRIPTION";

    private Button editButton = null;
    private Button saveButton = null;

    // EditText
    private EditText nameEditText;
    private EditText emailEditText;
    private Spinner genderSpinner;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profile_activity);
        instance = this;

        // initialize button
        editButton = (Button) findViewById(R.id.profile_button_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set enable
                nameEditText.setEnabled(true);
                genderSpinner.setEnabled(true);
                descriptionEditText.setEnabled(true);
                saveButton.setEnabled(true);
                editButton.setEnabled(false);
            }
        });

        saveButton = (Button) findViewById(R.id.profile_button_save);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset user property
                UserContainer.user.setName(nameEditText.getText().toString());
                UserContainer.user.setGender((String) genderSpinner.getSelectedItem());
                String gender = (String) genderSpinner.getSelectedItem();
                if (gender.equals("Male"))
                    UserContainer.user.setGender("M");
                else if (gender.equals("Female"))
                    UserContainer.user.setGender("F");
                else
                    UserContainer.user.setGender("");
                UserContainer.user.setDescription(descriptionEditText.getText().toString());

                // set enable
                nameEditText.setEnabled(false);
                genderSpinner.setEnabled(false);
                descriptionEditText.setEnabled(false);
                saveButton.setEnabled(false);
                editButton.setEnabled(true);

                // update remote database
                UpdateTask task = new UpdateTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UserContainer.user);
            }
        });

        // initialize and set text for EditText
        nameEditText = (EditText) findViewById(R.id.profile_editText_name);
        if (getIntent().getStringExtra(FRIEND_NAME) != null)
            nameEditText.setText(getIntent().getStringExtra(FRIEND_NAME));
        nameEditText.setEnabled(false);

        emailEditText = (EditText) findViewById(R.id.profile_editText_email);
        if (getIntent().getStringExtra(FRIEND_EMAIL) != null)
            emailEditText.setText(getIntent().getStringExtra(FRIEND_EMAIL));
        emailEditText.setEnabled(false);

        genderSpinner = (Spinner) findViewById(R.id.profile_spinner_gender);
        genderSpinner.setEnabled(false);
        String gender = getIntent().getStringExtra(FRIEND_GENDER);
        if (gender == null) {
            genderSpinner.setSelection(2);
        } else {
            if (gender.equals("M"))
                genderSpinner.setSelection(0);
            else if (gender.equals("F"))
                genderSpinner.setSelection(1);
            else
                genderSpinner.setSelection(2);
        }

        descriptionEditText = (EditText) findViewById(R.id.profile_editText_description);
        if (getIntent().getStringExtra(FRIEND_DESCRIPTION) != null)
            descriptionEditText.setText(getIntent().getStringExtra(FRIEND_DESCRIPTION));
        descriptionEditText.setEnabled(false);
    }

    class UpdateTask extends AsyncTask<User, Void, User> {

        private Exception exception;

        protected User doInBackground(User... params) {
            ProfileService.saveProfile(params[0]);
            return params[0];
        }

        protected void onPostExecute(User user) {
            // create a toast
            Toast.makeText(getApplicationContext(), "Update profile successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
