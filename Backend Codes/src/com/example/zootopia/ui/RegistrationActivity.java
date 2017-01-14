package com.example.zootopia.ui;

import android.content.Intent;
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
public class RegistrationActivity extends AppCompatActivity {
    public static RegistrationActivity instance =null;
    private Button registerButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registration_activity);
        instance = this;

        // registration button
        registerButton = (Button) findViewById(R.id.registration_button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an Intent to launch the Main Activity
                Intent mainActivity =
                        new Intent(RegistrationActivity.this, WeDiscuzAcitivity.class);
                startActivity(mainActivity);
                // finish activity
                instance.finish();
                LoginActivity.instance.finish();
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
}
