package com.example.zootopia.ws.local;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.MessageChatroom;
import com.example.zootopia.entities.MessageFriend;
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

    @Override
    public List<MessageFriend> getFriendRequests() {
        return null;
    }

    @Override
    public List<MessageChatroom> getChatroomInvitations() {
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
