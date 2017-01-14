package com.example.zootopia.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zootopia.adapter.MemberAdapter;
import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.dblayout.DbMyRoomsHandler;
import com.example.zootopia.dblayout.DbRoomMembersHandler;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.ChatService;
import com.example.zootopia.ws.remote.ChatroomService;
import com.example.zootopia.ws.remote.FriendService;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Member;
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

    private EditText roomnameEditText;
    private EditText descriptionEditText;
    private Switch typeSwitch;
    private Button okButton = null;
    private Button cancelButton = null;

    private ListView memberListView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_setup_room_activity);
        instance = this;

        // initialize EditText
        roomnameEditText = (EditText) findViewById(R.id.setup_room_editText_name);
        descriptionEditText = (EditText) findViewById(R.id.setup_room_editText_description);

        // initailize switch
        typeSwitch = (Switch) findViewById(R.id.setup_room_switch_voice);

        // initialize button
        okButton = (Button) findViewById(R.id.setup_room_button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> selectedID = getSeletedMember();
                for (int id : selectedID) {
                    System.err.println("[selectedID]" + id);
                }
                if (selectedID.size() < 2) {
                    // create a new AlertDialog Builder
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(SetupRoomActivity.this);

                    // set dialog title & message, and provide Button to dismiss
                    builder.setTitle(R.string.setupRoomWarningTitle);
                    builder.setMessage(R.string.setupRoomWarningError);
                    builder.setPositiveButton(R.string.ok, null);
                    builder.show();
                } else {
                    /* reate chatroom object */
                    // set member list
                    List<User> memberList = new ArrayList<User>();
                    memberList.add(UserContainer.user);
                    for (int id : selectedID) {
                        memberList.add(UserContainer.user.findFriend(id));
                    }

                    // set channel ID
                    int channelID = 0;
                    for (User member : memberList) {
                        channelID = channelID * 10 + member.getUserID();
                    }

                    // set room name
                    String roomName = roomnameEditText.getText().toString().trim();
                    if (roomName.equals("")) {
                        StringBuilder temp = new StringBuilder();
                        for (User u : memberList) {
                            temp.append("[");
                            temp.append(u.getName());
                            temp.append("]");
                        }
                        roomName = temp.toString();
                    }

                    // set room description
                    String description = descriptionEditText.getText().toString();

                    Chatroom newChatroom = new Chatroom(roomName, description, UserContainer.user, memberList);

                    // sent chat request
                    if (typeSwitch.isChecked()) {
                        for (User member : newChatroom.getMemberList()) {
                            if (!member.getUserID().equals(UserContainer.user.getUserID())) {
                                // JSON object
                                JSONObject objVideo = new JSONObject();
                                try {
                                    objVideo.put("caller_id", UserContainer.user.getUserID());
                                    objVideo.put("friend_id", member.getUserID());
                                    objVideo.put("type", ChannelActivity.CALLING_TYPE_VOICE);
                                    objVideo.put("channel_id", channelID);
                                    objVideo.put("room_name", roomName);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //remote service
                                ChatRequestTask videoTask = new ChatRequestTask();
                                videoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objVideo);
                            }
                        }
                    } else {
                        // remote request
                        for (User member : newChatroom.getMemberList()) {
                            if (!member.getUserID().equals(UserContainer.user.getUserID())) {
                                // JSON object
                                JSONObject objVoice = new JSONObject();
                                try {
                                    objVoice.put("caller_id", UserContainer.user.getUserID());
                                    objVoice.put("friend_id", member.getUserID());
                                    objVoice.put("type", ChannelActivity.CALLING_TYPE_VIDEO);
                                    objVoice.put("channel_id", channelID);
                                    objVoice.put("room_name", roomName);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //remote service
                                ChatRequestTask voiceTask = new ChatRequestTask();
                                voiceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objVoice);
                            }
                        }
                    }

                    // update remote database
                    SetupChatroomTask task = new SetupChatroomTask();
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, newChatroom);
                }
            }
        });

        cancelButton = (Button) findViewById(R.id.setup_room_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cancel button
                SetupRoomActivity.instance.finish();
            }
        });

        // Initialize member list view
        memberListView = (ListView) findViewById(R.id.setup_room_listView);
        LayoutInflater inflater = getLayoutInflater();
        MemberAdapter adapter = new MemberAdapter(inflater, UserContainer.user.getFriendList());
        memberListView.setAdapter(adapter);
        memberListView.setScrollContainer(false);
        memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.memberCheckBox.toggle();
                MemberAdapter.getIsSelected().put(position, holder.memberCheckBox.isChecked());
            }
        });
    }

    /**
     * get selected friend id list
     *
     * @return
     */
    public List<Integer> getSeletedMember() {
        List<Integer> memberIdList = new ArrayList<Integer>();

        int firstItemPosition = memberListView.getFirstVisiblePosition();
        int lastItemPosition = firstItemPosition + memberListView.getChildCount();

        for (int position = firstItemPosition; position < lastItemPosition; position++) {
            System.err.println("[Item Position]" + position);
            boolean flag = MemberAdapter.getIsSelected().get(position);
            if (flag) {
                memberIdList.add(UserContainer.user.getFriendList().get(position).getUserID());
            }
        }
        return memberIdList;
    }

    class SetupChatroomTask extends AsyncTask<Chatroom, Void, Chatroom> {

        private Exception exception;

        protected Chatroom doInBackground(Chatroom... params) {
            //FriendService.addFriend(params[0], params[1]);
            // create a new room
            ChatroomService.createChatroom(UserContainer.user.getUserID(), params[0]);
            return params[0];
        }

        protected void onPostExecute(Chatroom room) {
            // start chat
            Intent intent = new Intent(SetupRoomActivity.this, ChannelActivity.class);
            int channelID = 0;
            for (User member : room.getMemberList()) {
                channelID = channelID * 10 + member.getUserID();
            }
            if (!typeSwitch.isChecked()) {
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VIDEO);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, Integer.toString(channelID));
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, room.getRoomName());
            } else {
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VOICE);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, Integer.toString(channelID));
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, room.getRoomName());
            }
            // update local object
            UserContainer.chatroomList.add(room);
            UserContainer.user.addChatroom(room.getRoomID());

            // update local database
            DbMyRoomsHandler.insertRoom(room);
            DbRoomMembersHandler.insertRoomMemberList(room.getRoomID(), room.getMemberList());

            startActivity(intent);
            // finish the SetupRoomActivity
            SetupRoomActivity.instance.finish();
            // update WeDiscuzAcitivity
            WeDiscuzAcitivity.instance.recreate();
        }
    }

    class ChatRequestTask extends AsyncTask<JSONObject, Void, Void> {
        protected Void doInBackground(JSONObject... params) {
            try {
                int type = params[0].getInt("type");
                int callerID = params[0].getInt("caller_id");
                int friendID = params[0].getInt("friend_id");
                int channelID = params[0].getInt("channel_id");
                String roomName = params[0].getString("room_name");
                String callType;
                if (type == ChannelActivity.CALLING_TYPE_VIDEO)
                    callType = "video";
                else
                    callType = "voice";
                ChatService.sendChatRequest(callerID, friendID, callType, roomName, channelID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute() {

        }
    }
}
