package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class CalendarActivity extends AppCompatActivity {
    public static CalendarActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_calendar_activity);
        instance = this;
    }
}
