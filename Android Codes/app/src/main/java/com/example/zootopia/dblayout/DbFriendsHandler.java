package com.example.zootopia.dblayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zootopia.entities.User;

/**
 * Created by Yilei Chu on 16/4/15.
 */
public class DbFriendsHandler implements DbHandlerIntf {
    private static final String FRIEND_ID = "friend_id";
    private static final String FRIEND_NAME = "friend_name";
    private static final String FRIEND_EMAIL = "friend_email";
    private static final String FRIEND_GENDER = "friend_gender";
    private static final String FRIEND_DESCRIPTION = "friend_description";
    private static final String TABLE_NAME = "friends";
    private static SQLiteDatabase database;

    public static void initialize() {
        database = DatabaseConnector.getDatabase();
    }

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    // add
    public static void insertFriend(User user) {
        ContentValues friend = new ContentValues();
        friend.put(FRIEND_ID, user.getUserID());
        friend.put(FRIEND_NAME, user.getName());
        friend.put(FRIEND_EMAIL, user.getEmail());
        friend.put(FRIEND_GENDER, user.getGender());
        friend.put(FRIEND_DESCRIPTION, user.getDescription());

        DatabaseConnector.open();
        initialize();
        database.insertWithOnConflict(TABLE_NAME, null, friend, SQLiteDatabase.CONFLICT_REPLACE);
        //database.insert(TABLE_NAME, null, friend);
        DatabaseConnector.close();
    }

    // read
    public static User getFriendById(int friendID) {
        String sql = "select * from " + TABLE_NAME + " where " + FRIEND_ID + " = ? ";
        User temp = null;

        DatabaseConnector.open();
        initialize();
        String[] parameters = new String[1];
        // initial parameter
        parameters[0] = new String(Integer.toString(friendID));
        // get cursor
        Cursor cursor = database.rawQuery(sql, parameters);
        // get columns count
        int columns = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            temp = new User();
            // get user id
            String column_userid = cursor.getColumnName(0);
            int userId = cursor.getInt(cursor.getColumnIndex(column_userid));
            temp.setUserID(userId);

            // get user name
            String column_username = cursor.getColumnName(1);
            String username = cursor.getString(cursor.getColumnIndex(column_username));
            temp.setName(username);

            // get user gender
            String column_gender = cursor.getColumnName(2);
            String gender = cursor.getString(cursor.getColumnIndex(column_gender));
            temp.setName(gender);

            // get user description
            String column_description = cursor.getColumnName(3);
            String description = cursor.getString(cursor.getColumnIndex(column_description));
            temp.setDescription(description);

            // get user description
            String column_email = cursor.getColumnName(4);
            String email = cursor.getString(cursor.getColumnIndex(column_email));
            temp.setEmail(email);
        }
        DatabaseConnector.close();
        return temp;
    }

    // delete
    public static void deleteFriend(int friendID) {
        DatabaseConnector.open();
        initialize();
        database.delete(TABLE_NAME, "friend_id=" + friendID, null);
        DatabaseConnector.close();
    }

    // delete All friend
    public static void deleteAllFriends() {
        DatabaseConnector.open();
        initialize();
        database.execSQL("delete from " + TABLE_NAME + ";");
    }

    public static void updateFriend(User user) {
        DatabaseConnector.open();
        initialize();
        database.delete(TABLE_NAME, "friend_id=" + user.getUserID(), null);
        ContentValues friend = new ContentValues();
        friend.put(FRIEND_ID, user.getUserID());
        friend.put(FRIEND_NAME, user.getName());
        friend.put(FRIEND_GENDER, user.getGender());
        friend.put(FRIEND_DESCRIPTION, user.getDescription());
        database.insertWithOnConflict(TABLE_NAME, null, friend, SQLiteDatabase.CONFLICT_REPLACE);
        DatabaseConnector.close();
    }

}
