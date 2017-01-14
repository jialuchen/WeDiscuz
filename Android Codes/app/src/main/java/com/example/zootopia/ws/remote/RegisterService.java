package com.example.zootopia.ws.remote;

import com.example.zootopia.application.AgoraConfiguration;
import com.example.zootopia.entities.User;
import com.example.zootopia.util.HttpRequestUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zootopia on 5/2/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */

public class RegisterService {
    public static User register(String email, String password, String name) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("passwd", password);
        params.put("name", name);
        JSONObject obj = client.doPost("register", params);
        if (obj != null) {
            try {
                if (obj.getString("error_status").equals("no error")) {
                    int uid = Integer.parseInt(obj.getString("user_id"));
                    User user = ProfileService.getUserProfile(uid);
                    return user;
                }
            } catch (JSONException e) {
                System.err.println("[RegisterService] " + obj.toString());
                e.printStackTrace();
            }
        }
        return null;
    }

}
