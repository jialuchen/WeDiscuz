package com.example.zootopia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ChatroomFragment extends Fragment {
    private ListView listView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Initialize view
        View v = inflater.inflate(R.layout.ui_chatroom_fragment, container, false);

        // Initialize floating action button
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.chatroom_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an Intent to launch the Setup Room Activity
                Intent setupRoomActivity =
                        new Intent(getActivity(), SetupRoomActivity.class);
                startActivity(setupRoomActivity);
            }
        });

        // Initialize list view
        listView = (ListView) v.findViewById(R.id.chatroom_listView);
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), getData(), R.layout.ui_chatroom_listview,
                new String[]{"image", "name", "host"},
                new int[]{R.id.chatroom_listitem_image, R.id.chatroom_listitem_name, R.id.chatroom_listitem_host});

        listView.setAdapter(adapter);

        return v;
    }

    /**
     * getData - return list data
     *
     * @return list of data
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("host", "Jack");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        return list;
    }
}
