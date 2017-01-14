package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class ProfileActivity extends AppCompatActivity {
    public static ProfileActivity instance = null;

    private Button editButton = null;
    private Button saveButton = null;

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
                // login button
                ProfileActivity.instance.finish();
            }
        });

        saveButton = (Button) findViewById(R.id.profile_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // login button
                ProfileActivity.instance.finish();
            }
        });
    }
}
