package com.example.zootopia.entities;

import java.util.List;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class User {
    private Integer userID;
    private String email;
    private String name;
    private String gender;      // F/M
    private String birthday;    // MM-DD-YYYY
    private String description;
    private List<User> friendList;
    private List<Chatroom> chatroomList;

    public User(Integer userID, String email, String name, String gender, String birthday, String description, List<User> friendList, List<Chatroom> chatroomList) {
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.description = description;
        this.friendList = friendList;
        this.chatroomList = chatroomList;
    }

    public User() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    public List<Chatroom> getChatroomList() {
        return chatroomList;
    }

    public void setChatroomList(List<Chatroom> chatroomList) {
        this.chatroomList = chatroomList;
    }
}
