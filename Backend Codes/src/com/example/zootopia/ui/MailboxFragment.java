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
public class MailboxFragment extends Fragment {
    private ListView friendRequestListView = null;
    private ListView roomInvitationListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v =  inflater.inflate(R.layout.ui_mailbox_fragment, null);

        // Initialize friend request list view
        friendRequestListView = (ListView) v.findViewById(R.id.mailbox_listView_friend_request);
        SimpleAdapter contactAdapter = new SimpleAdapter(this.getContext(), getCFriendRequestData(), R.layout.ui_friend_request_listview,
                new String[]{"name", "time"},
                new int[]{R.id.friend_request_listitem_name, R.id.friend_request_listitem_time});

        friendRequestListView.setAdapter(contactAdapter);
        friendRequestListView.setScrollContainer(false);

        // Initialize chatroom list view
        roomInvitationListView = (ListView) v.findViewById(R.id.mailbox_listView_room_invitation);
        SimpleAdapter chatroomAdapter = new SimpleAdapter(this.getContext(), getRoomInvitationData(), R.layout.ui_room_invitation_listview,
                new String[]{"name", "time"},
                new int[]{R.id.room_invitation_listitem_name, R.id.room_invitation_listitem_time});

        roomInvitationListView.setAdapter(chatroomAdapter);
        roomInvitationListView.setScrollContainer(false);
        return v;
    }

    /**
     * getContactData - return list data of friend request
     *
     * @return list of data
     */
    private List<Map<String, Object>> getCFriendRequestData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Yilei Chu");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("time", "2016-4-14");
        list.add(map);

        return list;
    }

    /**
     * getChatroomData - return list data of room invitation
     *
     * @return list of data
     */
    private List<Map<String, Object>> getRoomInvitationData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Java for Smart Phone");
        map.put("time", "2016-03-11");
        list.add(map);

        return list;
    }
}
