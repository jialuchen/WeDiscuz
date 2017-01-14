package com.example.zootopia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zootopia.wediscuz.R;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class SettingActivity extends AppCompatActivity {
    public static SettingActivity instance = null;
    private ListView settingLv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);

        settingLv = (ListView)findViewById(R.id.setting_listView);
        settingLv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "pos:"+position+",id:"+id, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0: // give feedback
                        Intent giveFeedback = new Intent(SettingActivity.this, FeedbackActivity.class);
                        startActivity(giveFeedback);
                        break;
                    case 1: // contact us
                        Intent contactUs = new Intent(SettingActivity.this, ContactUsActivity.class);
                        startActivity(contactUs);
                        break;
                }
            }
        });
    }
}
