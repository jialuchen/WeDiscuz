package com.example.zootopia.ws.remote;

import com.example.zootopia.entities.User;
import com.example.zootopia.util.HttpRequestUtil;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zootopia on 5/2/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */

public class ProfileService {
    public static User getDetailedUserProfile(int userid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doGet("profile", params);
        if (obj != null) {
            User user = new User();
            try {
                int uid = Integer.parseInt(obj.getString("user_id"));
                user.setUserID(uid);
                user.setName(obj.getString("user_name"));
                user.setEmail(obj.getString("email"));
                if (obj.has("description"))
                    user.setDescription(obj.getString("description"));
                if (obj.has("gender"))
                    user.setGender(obj.getString("gender"));
                user.setFriendList(FriendService.getFriendList(uid));
                user.setChatroomList(ChatroomService.getChatroomList(uid));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;
        }
        return null;
    }

    public static User getUserProfile(int userid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doGet("profile", params);
        if (obj != null) {
            User user = new User();
            try {
                user.setUserID(Integer.parseInt(obj.getString("user_id")));
                user.setName(obj.getString("user_name"));
                user.setEmail(obj.getString("email"));
                if (obj.has("description"))
                    user.setDescription(obj.getString("description"));
                if (obj.has("gender"))
                    user.setGender(obj.getString("gender"));
                user.setFriendList(new ArrayList<User>());
                user.setChatroomList(new ArrayList<Integer>());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;
        }
        return null;
    }

    public static User getUserProfile(String email) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        JSONObject obj = client.doGet("profile", params);
        if (obj != null) {
            User user = null;
            try {
                if (obj.has("user_id")) {
                    user = new User();
                    user.setUserID(Integer.parseInt(obj.getString("user_id")));
                    user.setName(obj.getString("user_name"));
                    user.setEmail(obj.getString("email"));
                    if (obj.has("description"))
                        user.setDescription(obj.getString("description"));
                    if (obj.has("gender"))
                        user.setGender(obj.getString("gender"));
                    user.setFriendList(new ArrayList<User>());
                    user.setChatroomList(new ArrayList<Integer>());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;
        }
        return null;
    }

    /**
     * Given email address, return user id if registered
     * @param email
     * @return
     */
    public static int getUserId(String email) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        JSONObject obj = client.doGet("profile", params);
        if (obj != null) {
            try {
                return Integer.parseInt(obj.getString("user_id"));
            } catch (JSONException e) {
                System.err.println("[ProfileService] " + obj.toString());
                e.printStackTrace();
            }
        }
        return -1;
    }


    // update profile
    // sample request:
    // requests.post('http://localhost:8080/AppBackend/profile',
    // data = {'user_id':3, 'gender':'F', 'user_name':'Lily', 'description':"student"})
    public static void saveProfile(User user) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user.getUserID().toString());
        params.put("gender", user.getGender());
        params.put("user_name", user.getName());
        params.put("description", user.getDescription());
        client.doPost("profile", params);
    }
}
