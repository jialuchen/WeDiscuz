package com.example.zootopia.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zootopia on 5/2/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class RoomInfoActivity extends AppCompatActivity {
    public static RoomInfoActivity instance;

    public final static String ROOM_ID = "ROOM_ID";
    public final static String ROOM_NAME = "ROOM_NAME";
    public final static String ROOM_DESCRIPTION = "ROOM_DESCRIPTION";
    public final static String CREATOR_NAME = "CREATOR_NAME";

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText creatorEditText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_room_profile_activity);
        instance = this;

        // initialize EditText
        nameEditText = (EditText) findViewById(R.id.room_profile_editText_name);
        if (getIntent().getStringExtra(ROOM_NAME) != null)
            nameEditText.setText(getIntent().getStringExtra(ROOM_NAME));
        nameEditText.setEnabled(false);

        descriptionEditText = (EditText) findViewById(R.id.room_profile_editText_description);
        if (getIntent().getStringExtra(ROOM_DESCRIPTION) != null)
            descriptionEditText.setText(getIntent().getStringExtra(ROOM_DESCRIPTION));
        descriptionEditText.setEnabled(false);

        creatorEditText = (EditText) findViewById(R.id.room_profile_editText_creator);
        if (getIntent().getStringExtra(CREATOR_NAME) != null)
            creatorEditText.setText(getIntent().getStringExtra(CREATOR_NAME));
        creatorEditText.setEnabled(false);

        // initialize listview
        listView = (ListView) findViewById(R.id.room_profile_listView_member);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.ui_friends_listview,
                new String[]{"image", "name", "email"},
                new int[]{R.id.friends_listitem_image, R.id.friends_listitem_name, R.id.friends_listitem_email});
        listView.setAdapter(adapter);
    }

    /**
     * getData - return list data
     *
     * @return list of data
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        int roomid = Integer.valueOf(getIntent().getStringExtra(ROOM_ID));
        Chatroom room = null;
        for (Chatroom r : UserContainer.chatroomList) {
            if (r.getRoomID().equals(roomid))
                room = r;
        }
        List<User> memberList = new ArrayList<User>();
        if (room != null)
            memberList = room.getMemberList();
        for (User member : memberList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", member.getName());
            map.put("email", member.getEmail());
            map.put("image", R.mipmap.ic_launcher);
            list.add(map);
        }
        return list;
    }
}
