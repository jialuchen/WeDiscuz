package com.example.zootopia.ws.remote;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;
import com.example.zootopia.util.HttpRequestUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zootopia on 5/2/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */

public class LoginAuthenticationService {
    /**
     * loginAuthentication - authenticate user by email and password
     *
     * @param email
     * @param password
     * @return user_id
     */
    public static User loginAuthentication(String email, String password) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("passwd", password);
        JSONObject obj = client.doGet("login", params);
        if (obj != null) {
            System.err.println("[LoginAuthenticationService] " + obj.toString());
            try {
                if (obj.getString("login_result").equals("verified")) {
                    JSONObject tempuser = new JSONObject(obj.getString("user_profile"));
                    int uid = tempuser.getInt("user_id");
                    User user = ProfileService.getDetailedUserProfile(uid);
                    return user;
                } else {
                    return null;
                }
            } catch (JSONException e) {
                System.err.println("[loginAuthentication] " + obj.toString());
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Chatroom> getChatroomList(User user) {
        if (user != null) {
            List<Chatroom> list = new ArrayList<>();
            for (int i : user.getChatroomList()) {
                list.add(ChatroomService.getChatroom(i));
            }
            return list;
        } else {
            return null;
        }
    }
}
