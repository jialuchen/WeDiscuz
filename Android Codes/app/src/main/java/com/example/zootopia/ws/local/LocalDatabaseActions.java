package com.example.zootopia.ws.local;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;

import java.util.List;

/**
 * Created by ychu1 on 16/4/15.
 */
public class LocalDatabaseActions implements LocalDatabaseActionIntf {

    /* ===================== read ===================== */
    @Override
    public List<User> getMyFriends() {
        return null;
    }

    @Override
    public List<Chatroom> getMyChatrooms() {
        return null;
    }

    @Override
    public List<User> getChatroomMembers() {
        return null;
    }
    public void createNewChatroom(Chatroom newRoom) {

    }
}
