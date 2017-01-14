package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/27/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class ForgetPasswdActivity  extends AppCompatActivity {
    public static ForgetPasswdActivity instance = null;
    private Button forgetPasswdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_forgetpasswd_activity);
        instance = this;

        // Initialize Forget Password Button
        forgetPasswdButton = (Button) findViewById(R.id.forgetpasswd_button_send);
        forgetPasswdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.finish();
            }
        });
    }
}
