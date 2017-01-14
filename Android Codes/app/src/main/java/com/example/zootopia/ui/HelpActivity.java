package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class HelpActivity extends AppCompatActivity {
    public static HelpActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_help_activity);
        instance = this;
    }
}
