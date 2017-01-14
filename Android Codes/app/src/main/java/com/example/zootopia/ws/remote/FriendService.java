package com.example.zootopia.ws.remote;

import com.example.zootopia.entities.User;
import com.example.zootopia.util.HttpRequestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class FriendService {
    /**
     * addFriend by friend id
     * (friend id will be got from searchFriendList)
     *
     * @param friendid
     * @return new Friend
     */
    public static User addFriend(int userid, int friendid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        params.put("friend_id", Integer.toString(friendid));
        JSONObject obj = client.doPost("relation", params);
        if (obj != null) {
            return ProfileService.getUserProfile(friendid);
        }
        return null;
    }

    // will be presented, not optional
    public static boolean deleteFriend(int userid, int friendid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        params.put("friend_id", Integer.toString(friendid));
        JSONObject obj = client.doDelete("relation", params);
        return obj != null;
    }

    public static int searchFriend(String email) {
        return ProfileService.getUserId(email);
    }

    public static List<User> getFriendList(int userid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doGet("relation", params);
        List<User> list = new ArrayList<>();
        if (obj != null) {
            JSONArray friendlist = null;
            try {
                friendlist = (JSONArray) obj.get("user_profile");
                System.err.println("[getFriendList] " + friendlist.toString());
                for (int i = 0; i < friendlist.length(); i++) {
                    JSONObject usr = friendlist.getJSONObject(i);
                    User friend = new User();
                    friend.setUserID(Integer.parseInt(usr.getString("user_id")));
                    friend.setName(usr.getString("user_name"));
                    friend.setEmail(usr.getString("email"));
                    if (obj.has("description"))
                        friend.setDescription(obj.getString("description"));
                    if (obj.has("gender"))
                        friend.setGender(obj.getString("gender"));
                    list.add(friend);
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return list;
    }
}
