package com.example.zootopia.ws.remote;

import com.example.zootopia.util.HttpRequestUtil;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Yilei Chu (ychu1) on 5/1/16.
 *
 * get:
 * http://localhost:8080/AppBackend/CallingServlet?callee_id=2
 *
 * post:
 * http://localhost:8080/AppBackend/CallingServlet?callee_id=2&caller_id=1&room_id=1997&type=voice
 */

public class ChatService {
    //type: voice / video (not return busy/available, demo will let user available)
    public static void sendChatRequest(int callerID, int friendID, String type, String roomName, int channelId) {
        //assume user available, do post to update calling db
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        HashMap<String, String> params = new HashMap<>();
        params.put("caller_id",Integer.toString(callerID));
        params.put("callee_id",Integer.toString(friendID));
        params.put("room_id",Integer.toString(channelId));
        params.put("room_name",roomName);
        params.put("type",type);

        JSONObject obj = client.doPost("CallingServlet", params);
        if (obj != null) {
            System.err.println("[ChatService] "+obj.toString());
        }
    }

    /**
     * background service, check whether I am called
     * getChatRequest
     * @param callee_id
     */
    public static JSONObject getChatRequest(int callee_id) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        // GET
        HashMap<String, String> params = new HashMap<>();
        params.put("callee_id", Integer.toString(callee_id));
        JSONObject obj = client.doGet("CallingServlet", params);
        if (obj != null) {
            System.err.println("[ChatService] "+obj.toString());
        }
        return obj;
    }
}
