package com.example.zootopia.ws.local;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;

import java.util.List;

/**
 * Created by ychu1 on 16/4/15.
 */
public interface LocalDatabaseActionIntf {
    // apart from the 5 actions related to write, there are "read" local db actions
    List<User> getMyFriends(); //check local db for
    List<Chatroom> getMyChatrooms(); //get chatrooms where I am member
    List<User> getChatroomMembers(); // get member list of a room I participate
}
