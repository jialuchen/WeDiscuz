package com.example.zootopia.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.dblayout.DbFriendsHandler;
import com.example.zootopia.dblayout.DbMyRoomsHandler;
import com.example.zootopia.dblayout.DbRoomMembersHandler;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.ChatService;
import com.example.zootopia.ws.remote.ChatroomService;
import com.example.zootopia.ws.remote.FriendService;
import com.example.zootopia.ws.remote.LoginAuthenticationService;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Button refreshButton;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, 0, 0, "Video Chat");
                        menu.add(0, 1, 0, "Voice Chat");
                        menu.add(0, 2, 0, "Chatroom Profile");
                        menu.add(0, 3, 0, "Delete");
                    }
                });
            }
        });

        // Intialize button
        // initial Button
        refreshButton = (Button) v.findViewById(R.id.chatroom_button_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshTask task = new RefreshTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UserContainer.user.getUserID());
            }
        });

        return v;
    }

    /**
     * onContextItemSelected
     *
     * @param item
     * @return
     */
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        // get list item position
        int position = info.position;
        List<Chatroom> chatroomList = UserContainer.chatroomList;
        Chatroom selectedChatroom = chatroomList.get(position);
        // set room name and channel id
        String roomName = selectedChatroom.getRoomName();
        // set new channel ID (!= roomid)
        int channelIDINT = 0;
        for (User member : selectedChatroom.getMemberList()) {
            channelIDINT = channelIDINT * 10 + member.getUserID();
        }
        String channelID = Integer.toString(channelIDINT);
        Intent intent;
        // menu
        switch (item.getItemId()) {
            case 0:
                // remote request
                for (User member : selectedChatroom.getMemberList()) {
                    if (!member.getUserID().equals(UserContainer.user.getUserID())) {
                        // JSON object
                        JSONObject objVideo = new JSONObject();
                        try {
                            objVideo.put("caller_id", UserContainer.user.getUserID());
                            objVideo.put("friend_id", member.getUserID());
                            objVideo.put("type", ChannelActivity.CALLING_TYPE_VIDEO);
                            objVideo.put("channel_id", channelIDINT);
                            objVideo.put("room_name", roomName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //remote service
                        ChatRequestTask videoTask = new ChatRequestTask();
                        videoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objVideo);
                    }
                }
                // Video Chat
                intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VIDEO);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelID);
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, roomName);
                startActivity(intent);
                break;
            case 1:
                for (User member : selectedChatroom.getMemberList()) {
                    if (!member.getUserID().equals(UserContainer.user.getUserID())) {
                        // JSON object
                        JSONObject objVoice = new JSONObject();
                        try {
                            objVoice.put("caller_id", UserContainer.user.getUserID());
                            objVoice.put("friend_id", member.getUserID());
                            objVoice.put("type", ChannelActivity.CALLING_TYPE_VOICE);
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
                // Voice Chat
                intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VOICE);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelID);
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, roomName);
                startActivity(intent);
                break;
            case 2:
                // Room info
                intent = new Intent(getActivity(), RoomInfoActivity.class);
                intent.putExtra(RoomInfoActivity.ROOM_NAME, roomName);
                intent.putExtra(RoomInfoActivity.ROOM_ID, selectedChatroom.getRoomID().toString());
                intent.putExtra(RoomInfoActivity.ROOM_DESCRIPTION, selectedChatroom.getDescription());
                intent.putExtra(RoomInfoActivity.CREATOR_NAME, selectedChatroom.getCreator().getName());
                startActivity(intent);
                break;
            case 3:
                // Delete room
                // local data object
                UserContainer.user.deleteChatroom(selectedChatroom.getRoomID());
                UserContainer.chatroomList.remove(selectedChatroom);

                // local data object
                DbMyRoomsHandler.deleteRoom(selectedChatroom.getRoomID());

                // remote object

                // reload activity
                listView.refreshDrawableState();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);

    }

    /**
     * getData - return list data
     *
     * @return list of data
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Chatroom c : UserContainer.chatroomList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", c.getRoomName());
            map.put("host", c.getCreator().getName());
            map.put("image", R.mipmap.ic_launcher);
            list.add(map);
        }
        return list;
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

    class RefreshTask extends AsyncTask<Integer, Void, List<Chatroom>> {
        protected List<Chatroom> doInBackground(Integer... params) {
            List<Integer> list = ChatroomService.getChatroomList(params[0]);
            UserContainer.user.setChatroomList(list);
            List<Chatroom> chatroomList = LoginAuthenticationService.getChatroomList(UserContainer.user);
            return chatroomList;
        }

        protected void onPostExecute(List<Chatroom> list) {
            // update local data object
            UserContainer.chatroomList = list;

            // update local database
            for (Chatroom room : list) {
                DbMyRoomsHandler.insertRoom(room);
            }
            // table chatroom_members
            for (Chatroom room : UserContainer.chatroomList) {
                for (User member : room.getMemberList()) {
                    DbRoomMembersHandler.insertRoomMember(room.getRoomID(), member);
                }
            }

            // update listview
            WeDiscuzAcitivity.instance.recreate();
        }
    }

}
