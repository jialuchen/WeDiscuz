package com.example.zootopia.entities;

import java.util.List;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class Chatroom {
    private String roomID;
    private String roomName;
    private String description;
    private User creator;
    private List<User> memberList;

    public Chatroom() {}

    public Chatroom(String roomID, String roomName, String description, User creator, List<User> memberList) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.creator = creator;
        this.memberList = memberList;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<User> memberList) {
        this.memberList = memberList;
    }
}