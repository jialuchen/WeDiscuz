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
public class LoginActivity extends AppCompatActivity {
    public static LoginActivity instance =null;
    private Button loginButton;
    private Button registrationButton;

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
                // create an Intent to launch the Wediscuz Activity
                Intent mainActivity =
                        new Intent(LoginActivity.this, WeDiscuzAcitivity.class);
                startActivity(mainActivity);
                LoginActivity.instance.finish();
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
    }
}
