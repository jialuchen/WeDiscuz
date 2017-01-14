package com.example.zootopia.ws.remote;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.MessageChatroom;
import com.example.zootopia.entities.MessageFriend;

import org.json.JSONObject;

/**
 * Created by ychu1 on 16/4/15.
 */
public class RemoteDatabaseActions implements RemoteDatabaseActionIntf {
    /* ===================== read ===================== */
    @Override
    public JSONObject login(String email, String password) {
        return null;
    }

    /* ===================== write ===================== */
    @Override
    public void acceptFriendRequest(MessageFriend msg) {

    }

    @Override
    public void acceptChatroomInvitation(MessageChatroom msg) {

    }

    @Override
    public void createNewChatroom(Chatroom newRoom) {

    }

    @Override
    public void sendFriendRequest(MessageFriend msg) {

    }

    @Override
    public void sendChatroomInvitation(MessageChatroom msg) {

    }
}
