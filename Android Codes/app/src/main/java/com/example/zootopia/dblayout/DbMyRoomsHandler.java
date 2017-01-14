package com.example.zootopia.dblayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;

/**
 * Created by Yilei Chu on 16/4/15.
 */
public class DbMyRoomsHandler implements DbHandlerIntf {
    private final static String ROOM_ID = "room_id";
    private final static String ROOM_NAME = "room_name";
    private final static String ROOM_DESCRIPTION = "room_description";
    private final static String HOST_ID = "host_id";
    private final static String HOST_NAME = "host_name";
    private static final String TABLE_NAME = "chatrooms";
    private static SQLiteDatabase database;

    public static void initialize() {
        database = DatabaseConnector.getDatabase();
    }

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    // add
    public static void insertRoom(Chatroom room) {
        ContentValues chatroom = new ContentValues();
        chatroom.put(ROOM_ID, room.getRoomID());
        chatroom.put(ROOM_NAME, room.getRoomName());
        chatroom.put(ROOM_DESCRIPTION, room.getDescription());
        chatroom.put(HOST_ID, room.getCreator().getUserID());
        chatroom.put(HOST_NAME, room.getCreator().getName());

        DatabaseConnector.open();
        initialize();
        database.insertWithOnConflict(TABLE_NAME, null, chatroom, SQLiteDatabase.CONFLICT_REPLACE);
        //database.insert(TABLE_NAME, null, chatroom);
        DatabaseConnector.close();
    }

    // read
    public static Chatroom getRoomById(int roomID) {
        String sql = "select * from " + TABLE_NAME + " where " + ROOM_ID + " = ? ";
        Chatroom temp = null;

        DatabaseConnector.open();
        initialize();
        String[] parameters = new String[1];
        // initial parameter
        parameters[0] = new String(Integer.toString(roomID));
        // get cursor
        Cursor cursor = database.rawQuery(sql, parameters);
        // get columns count
        int columns = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            temp = new Chatroom();
            // get room id
            String column_roomid = cursor.getColumnName(0);
            int roomid = cursor.getInt(cursor.getColumnIndex(column_roomid));
            temp.setRoomID(roomid);

            // get room name
            String column_roomname = cursor.getColumnName(1);
            String roomname = cursor.getString(cursor.getColumnIndex(column_roomname));
            temp.setRoomName(roomname);

            // get room description
            String column_description = cursor.getColumnName(2);
            String description = cursor.getString(cursor.getColumnIndex(column_description));
            temp.setDescription(description);

            // get host id
            String column_hostid = cursor.getColumnName(3);
            int hostid = cursor.getInt(cursor.getColumnIndex(column_hostid));
            User host = null;
            if (UserContainer.user.getUserID().equals(hostid)) {
                host = UserContainer.user;
            } else {
                for (User friend : UserContainer.user.getFriendList()) {
                    if (friend.getUserID().equals(hostid)) {
                        host = friend;
                    }
                }
            }
            temp.setCreator(host);
        }
        DatabaseConnector.close();
        return temp;
    }

    //update
    public static void updateRoom(Chatroom room) {
        // delete and then insert
        DatabaseConnector.open();
        initialize();
        database.delete(TABLE_NAME, "room_id=" + room.getRoomID(), null);
        ContentValues chatroom = new ContentValues();
        chatroom.put(ROOM_ID, room.getRoomID());
        chatroom.put(ROOM_NAME, room.getRoomName());
        chatroom.put(ROOM_DESCRIPTION, room.getDescription());
        chatroom.put(HOST_ID, room.getCreator().getUserID());

        database.insertWithOnConflict(TABLE_NAME, null, chatroom, SQLiteDatabase.CONFLICT_REPLACE);
        DatabaseConnector.close();
    }

    //delete
    public static void deleteRoom(int roomID) {
        DatabaseConnector.open();
        initialize();
        database.delete(TABLE_NAME, "room_id=" + roomID, null);
        DatabaseConnector.close();
    }
}
