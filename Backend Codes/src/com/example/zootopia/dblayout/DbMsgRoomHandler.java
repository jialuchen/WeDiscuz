package com.example.zootopia.dblayout;

import com.example.zootopia.entities.Message;
import com.example.zootopia.entities.MessageChatroom;

/**
 * Created by ychu1 on 16/4/15.
 */
public class DbMsgRoomHandler implements DbHandlerIntf {
    private final static String MESSAGE_ID = "msg_id";
    private final static String FROM_USER_ID = "from_user_id";
    private final static String ROOM_ID = "room_id";
    private final static String TITLE = "title";
    private final static String TIME = "time";
    private final static String CONTENT = "content";
    private final static String STATUS = "status";

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    //add
    public void addMsg(MessageChatroom room){

    }

    //read
    public Message getMessage(int msgID){

        return null;
    }

    //update TODO

    //delete
    public void deleteMessage(int msgID){

    }



}
