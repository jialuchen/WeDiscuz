package com.example.zootopia.dblayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zootopia.adapter.UserContainer;
import com.example.zootopia.entities.Chatroom;
import com.example.zootopia.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yilei Chu on 16/4/15.
 */
public class DbRoomMembersHandler implements DbHandlerIntf {
    private static final String ROOM_ID = "room_id";
    private static final String MEMBER_ID = "member_id";
    private static final String MEMBER_NAME = "member_name";
    private static final String MEMBER_EMAIL = "member_email";
    private static final String TABLE_NAME = "chatroom_members";
    private static SQLiteDatabase database;

    public static void initialize() {
        database = DatabaseConnector.getDatabase();
    }

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    public static void insertRoomMemberList(int roomid, List<User> memberList) {
        for (User member: memberList) {
            insertRoomMember(roomid, member);
        }
    }

    public static void insertRoomMember(int roomid, User member) {
        ContentValues roomMember = new ContentValues();
        roomMember.put(ROOM_ID, roomid);
        roomMember.put(MEMBER_ID, member.getUserID());
        roomMember.put(MEMBER_NAME, member.getName());
        roomMember.put(MEMBER_EMAIL, member.getEmail());

        DatabaseConnector.open();
        initialize();
        database.insertWithOnConflict(TABLE_NAME, null, roomMember, SQLiteDatabase.CONFLICT_REPLACE);
        //database.insert(TABLE_NAME, null, roomMember);
        DatabaseConnector.close();
    }

    public static List<User> gerRoomMemberList(int roomid) {   // read
        String sql = "select * from " + TABLE_NAME + " where " + ROOM_ID + " = ? ";
        List<User> memberList = new ArrayList<User>();

        DatabaseConnector.open();
        initialize();
        String[] parameters = new String[1];
        // initial parameter
        parameters[0] = new String(Integer.toString(roomid));
        // get cursor
        Cursor cursor = database.rawQuery(sql, parameters);
        // get columns count
        int columns = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            User member = new User();
            // get member id
            String column_memberid = cursor.getColumnName(1);
            int memberid = cursor.getInt(cursor.getColumnIndex(column_memberid));
            member.setUserID(memberid);

            // get member name
            String column_membername = cursor.getColumnName(2);
            String membername = cursor.getString(cursor.getColumnIndex(column_membername));
            member.setName(membername);

            // get member email
            String column_email = cursor.getColumnName(3);
            String email = cursor.getString(cursor.getColumnIndex(column_email));
            member.setDescription(email);

            memberList.add(member);
        }
        DatabaseConnector.close();
        return memberList;
    }
}
