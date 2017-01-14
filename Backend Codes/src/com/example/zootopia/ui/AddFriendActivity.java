package com.example.zootopia.ui;

import android.content.Intent;
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
public class AddFriendActivity extends AppCompatActivity {
    public static AddFriendActivity instance = null;

    private Button addFriendButton = null;
    private ListView addFriendListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_add_friend_activity);
        instance = this;

        // initialize button
        addFriendButton = (Button) findViewById(R.id.add_friend_button_add);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFriendActivity.instance.finish();
            }
        });

        // Initialize user list view
        addFriendListView = (ListView) findViewById(R.id.add_friend_listView);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.ui_friends_listview,
                new String[]{"image", "name", "email"},
                new int[]{R.id.friends_listitem_image, R.id.friends_listitem_name, R.id.friends_listitem_email});

        addFriendListView.setAdapter(adapter);
        addFriendListView.setScrollContainer(false);

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
