package com.example.zootopia.ws.remote;

import com.example.zootopia.entities.Chatroom;
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
public class ChatroomService {
    public static boolean createChatroom(int userid, Chatroom chatroom) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("host_id", Integer.toString(userid));
        params.put("room_name", chatroom.getRoomName());
        params.put("room_description", chatroom.getDescription());
        JSONObject obj = client.doPost("chatroom", params);
        if (obj != null) {
            try {
                String room_id = obj.getString("room_id");
                System.err.println("[ChatroomService] Create a new chatroom, room_id:" + room_id);
                chatroom.setRoomID(Integer.parseInt(room_id));
                for (User usr : chatroom.getMemberList())
                    addChatmember(usr.getUserID(), Integer.parseInt(room_id));
                return true;
            } catch (JSONException e) {
                System.err.println("[ChatroomService]" + obj.toString());
                e.printStackTrace();
            }
        }
        return false;
    }

    // optional, may only update member list
    public static boolean deleteChatmember(int userid, Chatroom chatroom) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("room_id", Integer.toString(chatroom.getRoomID()));
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doDelete("chatroommember", params);
        return obj != null;
    }

    public static boolean addChatmember(int userid, int roomid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("room_id", Integer.toString(roomid));
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doPost("chatroommember", params);
        return obj != null;
    }

    public static Chatroom getChatroom(int roomid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("room_id", Integer.toString(roomid));
        JSONObject obj = client.doGet("chatroom", params);
        if (obj != null) {
            Chatroom room = new Chatroom();
            room.setRoomID(roomid);
            try {
                room.setCreator(ProfileService.getUserProfile(
                        Integer.parseInt(obj.getString("host_id"))));
                room.setRoomName(obj.getString("room_name"));
                room.setDescription(obj.getString("room_description"));
                room.setMemberList(getChatroomUserList(roomid));
                return room;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Integer> getChatroomList(int userid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(userid));
        JSONObject obj = client.doGet("chatroommember", params);
        List<Integer> list = new ArrayList<>();
        if (obj != null) {
            JSONArray roomlist = null;
            try {
                roomlist = (JSONArray) obj.get("chatroom_list");
                System.err.println("[getChatroomList] " + roomlist.toString());
                for (int i = 0; i < roomlist.length(); i++) {
                    JSONObject room_id = roomlist.getJSONObject(i);
                    list.add(Integer.parseInt(room_id.getString("room_id")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<User> getChatroomUserList(int roomid) {
        HttpRequestUtil client = HttpRequestUtil.getHttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("room_id", Integer.toString(roomid));
        JSONObject obj = client.doGet("chatroommember", params);
        List<User> list = new ArrayList<>();
        if (obj != null) {
            JSONArray memberList;
            try {
                memberList = (JSONArray) obj.get("chatroom_member_list");
                System.err.println("[getChatroomUserList] " + memberList.toString());
                for (int i = 0; i < memberList.length(); i++) {
                    JSONObject usr = memberList.getJSONObject(i);
                    User member = new User();
                    member.setUserID(Integer.parseInt(usr.getString("user_id")));
                    member.setName(usr.getString("user_name"));
                    member.setEmail(usr.getString("email"));
                    if (obj.has("gender"))
                        member.setGender(usr.getString("gender"));
                    if (obj.has("description"))
                        member.setDescription(usr.getString("description"));
                    list.add(member);
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return list;
    }
}
