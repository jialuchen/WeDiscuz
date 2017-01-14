package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zootopia.wediscuz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class SetupRoomActivity extends AppCompatActivity {
    public static SetupRoomActivity instance = null;

    private Button okButton = null;
    private Button cancelButton = null;

    private ListView memberListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_setup_room_activity);
        instance = this;

        // initialize button
        okButton = (Button) findViewById(R.id.setup_room_button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupRoomActivity.instance.finish();
            }
        });

        cancelButton = (Button) findViewById(R.id.setup_room_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // login button
                SetupRoomActivity.instance.finish();
            }
        });

        // Initialize member list view
        memberListView = (ListView) findViewById(R.id.setup_room_listView);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.ui_member_listview,
                new String[]{"image", "name", "email"},
                new int[]{R.id.member_listitem_image, R.id.member_listitem_name, R.id.member_listitem_email});

        memberListView.setAdapter(adapter);
        memberListView.setScrollContainer(false);
    }

    /**
     * getData - return list data
     *
     * @return list of data
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Yilei Chu");
        map.put("email", "ychu1@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("email", "jialuc@andrew.cmu.edu");
        map.put("image",R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        return list;
    }
}
