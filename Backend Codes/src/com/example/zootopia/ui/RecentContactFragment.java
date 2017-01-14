package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class RecentContactFragment extends Fragment {
    private ListView contactListView = null;
    private ListView chatroomListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v =  inflater.inflate(R.layout.ui_recentcontact_fragment, null);

        // Initialize contact list view
        contactListView = (ListView) v.findViewById(R.id.recentcontact_listView_contact);
        SimpleAdapter contactAdapter = new SimpleAdapter(this.getContext(), getContactData(), R.layout.ui_friends_listview,
                new String[]{"image", "name", "email"},
                new int[]{R.id.friends_listitem_image, R.id.friends_listitem_name, R.id.friends_listitem_email});

        contactListView.setAdapter(contactAdapter);
        contactListView.setScrollContainer(false);

        // Initialize chatroom list view
        chatroomListView = (ListView) v.findViewById(R.id.recentcontact_listView_chatroom);
        SimpleAdapter chatroomAdapter = new SimpleAdapter(this.getContext(), getChatroomData(), R.layout.ui_chatroom_listview,
                new String[]{"image", "name", "host"},
                new int[]{R.id.chatroom_listitem_image, R.id.chatroom_listitem_name, R.id.chatroom_listitem_host});

        chatroomListView.setAdapter(chatroomAdapter);
        chatroomListView.setScrollContainer(false);

        return v;
    }

    /**
     * getContactData - return list data of recent contact
     *
     * @return list of data
     */
    private List<Map<String, Object>> getContactData() {
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

    /**
     * getChatroomData - return list data of recent chat room
     *
     * @return list of data
     */
    private List<Map<String, Object>> getChatroomData() {
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
