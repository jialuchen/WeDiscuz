package com.example.zootopia.dblayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * DatabaseConnector - class which is responsible for database connection and manipulation
 * referring to the sample provided by teacher
 * <p/>
 * Created by Linpeng Lyu on 3/31/16.
 * Andrew ID: linpengl
 */
public class DatabaseConnector {
    // database name
    private static final String DATABASE_NAME = "Wediscuz_DB";
    private static SQLiteDatabase database; // database object
    private static DatabaseOpenHelper databaseOpenHelper; // database helper

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    // open the database connection
    public static void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public static void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    // inserts a new student record in the database
//    public void insertStudent(Student stu) {
//        ContentValues newStu = new ContentValues();
//        newStu.put("student_id", stu.getStudentID());
//        newStu.put("q1", stu.getScores().get(0));
//        newStu.put("q2", stu.getScores().get(1));
//        newStu.put("q3", stu.getScores().get(2));
//        newStu.put("q4", stu.getScores().get(3));
//        newStu.put("q5", stu.getScores().get(4));
//
//        open(); // open the database
//        database.insert("students", null, newStu);
//        close(); // close the database
//    } // end method insertStudent

    // delete a existing student record in the database
    public void deleteStudent(int student_id) {
        open(); // open the database
        database.delete("students", "student_id=" + student_id, null);
        close(); // close the database
    } // end method insertContact

    // query existing student record in the database
//    public void queryDate() {
//        open(); // open the database
//        Cursor cursor = database.query("students", null, null, null, null, null, null);
//        // traverse the results
//        if (cursor.moveToFirst()) {
//            for (int i = 0; i < cursor.getCount(); i++) {
//                cursor.moveToPosition(i);
//                int id = cursor.getInt(0);
//                ArrayList<Float> scores = new ArrayList<Float>();
//                for (int j = 0; j < 5; j++) {
//                    scores.add(cursor.getFloat(j + 1));
//                }
//                StudentProxy.populateStudent(id, scores);
//            }
//        }
//        close(); // close the database
//    } // end method insertContact

    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the students table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to drop existing tables
            String deleteTableQuery = "DROP TABLE IF EXISTS friends;" +
                    "DROP TABLE IF EXISTS chatroom;" +
                    "DROP TABLE IF EXISTS chatroom_members;";
            db.execSQL(deleteTableQuery);

            // query to create a new table named friends
            String createFriendsQuery = "CREATE TABLE friends" +
                    "(friend_id INTEGER primary key, " +
                    "friend_name TEXT, friend_gender TEXT, " +
                    "friend_description TEXT, friend_email TEXT);";
            db.execSQL(createFriendsQuery); // execute the query

            // query to create a new table named chatrooms
            String createChatroomsQuery = "CREATE TABLE chatrooms" +
                    "(room_id INTEGER primary key, room_name TEXT, " +
                    "room_description TEXT, host_id INTEGER, host_name TEXT);";
            db.execSQL(createChatroomsQuery); // execute the query

            // query to create a new table named chatroom_members
            String createChatroomMembersQuery = "CREATE TABLE chatroom_members" +
                    "(room_id INTEGER, member_id INTEGER, member_name TEXT, " +
                    "member_email TEXT, primary key(room_id, member_id));";
            db.execSQL(createChatroomMembersQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper
}
