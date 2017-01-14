package com.example.zootopia.dblayout;

import com.example.zootopia.entities.Chatroom;

/**
 * Created by caixiaomo on 16/4/15.
 */
public class DbMyRoomsHandler implements DbHandlerIntf {
    private final static String ROOM_ID = "room_id";
    private final static String ROOM_NAME = "room_name";
    private final static String ROOM_DESCRIPTION = "room_description";
    private final static String HOST_ID = "host_id";

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    //add
    public void addRoom(Chatroom room){

    }

    //read
    public Chatroom getRoom(int roomID){

        return null;
    }

    //update TODO

    //delete
    public void deleteRoom(int roomID){

    }
}
