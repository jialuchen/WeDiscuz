package com.example.zootopia.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearSmoothScroller;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.application.AgoraConfiguration;
import com.example.zootopia.dblayout.DbFriendsHandler;
import com.example.zootopia.dblayout.DbMyRoomsHandler;
import com.example.zootopia.entities.User;
import com.example.zootopia.wediscuz.R;
import com.example.zootopia.ws.remote.ChatService;
import com.example.zootopia.ws.remote.FriendService;

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
public class FriendsFragment extends Fragment {
    public static FriendsFragment instance;
    private ListView listView = null;
    private Button refreshButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Initialize view
        View v = inflater.inflate(R.layout.ui_friends_fragment, container, false);

        // Initialize floating action button
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.friends_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an Intent to launch the AddFriend Activity
                Intent addFriendActivity =
                        new Intent(getActivity(), AddFriendActivity.class);
                startActivity(addFriendActivity);
            }
        });

        // Initialize list view
        listView = (ListView) v.findViewById(R.id.friends_listView);
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), getData(), R.layout.ui_friends_listview,
                new String[]{"image", "name", "email"},
                new int[]{R.id.friends_listitem_image, R.id.friends_listitem_name, R.id.friends_listitem_email});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, 0, 0, "Video Chat");
                        menu.add(0, 1, 0, "Voice Chat");
                        menu.add(0, 2, 0, "Profile");
                        menu.add(0, 3, 0, "Delete");
                    }
                });
            }
        });

        // initial Button
        refreshButton = (Button) v.findViewById(R.id.friend_button_refresh);
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
        User user = UserContainer.user;
        User selectedFriend = user.getFriendList().get(position);
        // set room name and channel id
        String roomName = user.getName() + "," + selectedFriend.getName();
        Integer channelID = user.getUserID() * 10 + selectedFriend.getUserID();
        Intent intent;
        // menu
        switch (item.getItemId()) {
            case 0:
                // JSON object
                JSONObject objVideo = new JSONObject();
                try {
                    objVideo.put("caller_id", user.getUserID());
                    objVideo.put("friend_id", selectedFriend.getUserID());
                    objVideo.put("type", ChannelActivity.CALLING_TYPE_VIDEO);
                    objVideo.put("channel_id", channelID);
                    objVideo.put("room_name", roomName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //remote service
                ChatRequestTask videoTask = new ChatRequestTask();
                videoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objVideo);
                // Video Chat
                intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VIDEO);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelID.toString());
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, roomName);
                startActivity(intent);
                break;
            case 1:
                // JSON object
                JSONObject objVoice = new JSONObject();
                try {
                    objVoice.put("caller_id", user.getUserID());
                    objVoice.put("friend_id", selectedFriend.getUserID());
                    objVoice.put("type", ChannelActivity.CALLING_TYPE_VOICE);
                    objVoice.put("channel_id", channelID);
                    objVoice.put("room_name", roomName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //remote service
                ChatRequestTask voiceTask = new ChatRequestTask();
                voiceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objVoice);
                // Voice Chat
                intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra(ChannelActivity.EXTRA_CALLING_TYPE, ChannelActivity.CALLING_TYPE_VOICE);
                intent.putExtra(ChannelActivity.EXTRA_CHANNEL_ID, channelID.toString());
                intent.putExtra(ChannelActivity.EXTRA_ROOM_NAME, roomName);
                startActivity(intent);
                break;
            case 2:
                // Profile
                intent = new Intent(getActivity(), ProfileViewActivity.class);
                intent.putExtra(ProfileViewActivity.FRIEND_NAME, selectedFriend.getName());
                intent.putExtra(ProfileViewActivity.FRIEND_EMAIL, selectedFriend.getEmail());
                intent.putExtra(ProfileViewActivity.FRIEND_GENDER, selectedFriend.getGender());
                intent.putExtra(ProfileViewActivity.FRIEND_DESCRIPTION, selectedFriend.getDescription());
                startActivity(intent);
                break;
            case 3:
                // Delete
                // local data object
                user.deleteFriend(selectedFriend.getUserID());

                // local database
                DbFriendsHandler.deleteFriend(selectedFriend.getUserID());

                // remote database
                DeleteTask task = new DeleteTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UserContainer.user.getUserID(), selectedFriend.getUserID());
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

        List<User> friendList = UserContainer.user.getFriendList();
        for (User friend : friendList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", friend.getName());
            map.put("email", friend.getEmail());
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

    class RefreshTask extends AsyncTask<Integer, Void, List<User>> {
        protected List<User> doInBackground(Integer... params) {
            List<User> list = FriendService.getFriendList(params[0]);
            return list;
        }

        protected void onPostExecute(List<User> list) {
            // update local data object
            UserContainer.user.setFriendList(list);

            // update local database
            DbFriendsHandler.deleteAllFriends();
            for (User member : list) {
                DbFriendsHandler.deleteFriend(member.getUserID());
            }
            // update listview
            WeDiscuzAcitivity.instance.recreate();
        }
    }

    class DeleteTask extends AsyncTask<Integer, Void, Integer> {
        protected Integer doInBackground(Integer... params) {
            if (FriendService.deleteFriend(params[0], params[1])) {
                return params[1];
            } else {
                return null;
            }
        }

        protected void onPostExecute(Integer id) {
            if (id != null) {
                // local data object
                UserContainer.user.deleteFriend(id);
                // local data base
                DbFriendsHandler.deleteFriend(id);
                // message
                Toast.makeText(getContext(), "Delete successfully!",
                        Toast.LENGTH_SHORT).show();
                // update listview
                listView.invalidateViews();
            } else {
                // message
                Toast.makeText(getContext(), "Delete failed!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
