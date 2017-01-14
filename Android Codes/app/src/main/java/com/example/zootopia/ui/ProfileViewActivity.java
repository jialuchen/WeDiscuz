package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/30/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class ProfileViewActivity extends AppCompatActivity {
    public static ProfileViewActivity instance = null;

    public final static String FRIEND_NAME = "FRIEND_NAME";
    public final static String FRIEND_EMAIL = "FRIEND_EMAIL";
    public final static String FRIEND_GENDER = "FRIEND_GENDER";
    public final static String FRIEND_DESCRIPTION = "FRIEND_DESCRIPTION";

    // EditText
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText genderEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profile_view_activity);
        instance = this;

        // initialize and set text for EditText
        nameEditText = (EditText) findViewById(R.id.profile_view_editText_name);
        if (getIntent().getStringExtra(FRIEND_NAME) != null)
            nameEditText.setText(getIntent().getStringExtra(FRIEND_NAME));
        nameEditText.setEnabled(false);

        emailEditText = (EditText) findViewById(R.id.profile_view_editText_email);
        if (getIntent().getStringExtra(FRIEND_EMAIL) != null)
            emailEditText.setText(getIntent().getStringExtra(FRIEND_EMAIL));
        emailEditText.setEnabled(false);

        genderEditText = (EditText) findViewById(R.id.profile_view_editText_gender);
        if (getIntent().getStringExtra(FRIEND_GENDER) != null) {
            String gender = getIntent().getStringExtra(FRIEND_GENDER);
            if (gender.equals("M"))
                genderEditText.setText("Male");
            else if (gender.equals("F"))
                genderEditText.setText("Female");
            else
                genderEditText.setText("Other");
        }
        genderEditText.setEnabled(false);

        descriptionEditText = (EditText) findViewById(R.id.profile_view_editText_description);
        if (getIntent().getStringExtra(FRIEND_DESCRIPTION) != null)
            descriptionEditText.setText(getIntent().getStringExtra(FRIEND_DESCRIPTION));
        descriptionEditText.setEnabled(false);
    }
}
