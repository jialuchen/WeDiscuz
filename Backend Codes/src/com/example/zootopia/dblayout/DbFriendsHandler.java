package com.example.zootopia.dblayout;

import com.example.zootopia.entities.User;

/**
 * Created by caixiaomo on 16/4/15.
 */
public class DbFriendsHandler implements DbHandlerIntf {
    private static final String FRIEND_ID = "friend_id";
    private static final String FRIEND_NAME = "friend_name";
    private static final String FRIEND_GENDER = "friend_gender";
    private static final String FRIEND_BIRTHDAY = "friend_birthday";
    private static final String FRIEND_DESCRIPTION= "friend_description";

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    //add
    public void addFriend(int friendID){

    }

    //read
    public User getFriend(int msgID){

        return null;
    }

    //update TODO

    //delete
    public void deleteFriend(int friendID){


    }

}
