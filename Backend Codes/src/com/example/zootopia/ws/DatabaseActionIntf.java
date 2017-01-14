package com.example.zootopia.ws;

import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.MessageChatroom;
import com.example.zootopia.entities.MessageFriend;

/**
 * Created by ychu1 on 16/4/15.
 */
public interface DatabaseActionIntf {
    /* operations containing write */
    void acceptFriendRequest(MessageFriend msg);        //myID is "dst"
    void acceptChatroomInvitation(MessageChatroom msg); //myID is "dst"
    void createNewChatroom(Chatroom newRoom);           //myID is the host id of chatroom
    void sendFriendRequest(MessageFriend msg);          //myID is "src"
    void sendChatroomInvitation(MessageChatroom msg);   //myID is "src"
}