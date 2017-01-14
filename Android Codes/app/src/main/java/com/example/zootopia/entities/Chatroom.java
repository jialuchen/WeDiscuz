package com.example.zootopia.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class Chatroom {
    private Integer roomID;
    private String roomName;
    private String description;
    private User creator;
    private List<User> memberList;

    public Chatroom() {
        memberList = new ArrayList<User>();
    }

    public Chatroom(Integer roomID, String roomName, String description, User creator, List<User> memberList) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.creator = creator;
        this.memberList = memberList;
    }

    public Chatroom(String roomName, String description, User creator, List<User> memberList) {
        this.roomName = roomName;
        this.description = description;
        this.creator = creator;
        this.memberList = memberList;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
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

    /**
     * addMember - add a member to the room
     * @param member
     * @return
     */
    public boolean addMember(User member) {
        for (User m : memberList) {
            if (member.getUserID() == m.getUserID())
                return false;
        }
        memberList.add(member);
        return true;
    }

    /**
     * findFriend - find a friend by the user id
     * @param id
     * @return
     */
    public User findMember(int id) {
        User temp = null;
        for (User m : memberList) {
            if (m.getUserID() == id)
                temp = m;
        }
        return temp;
    }

    /**
     * findFriend - find a friend by the user id
     * @param email
     * @return
     */
    public User findMemberByEmail(String email) {
        User temp = null;
        for (User m : memberList) {
            if (m.getEmail().equals(email))
                temp = m;
        }
        return temp;
    }

    /**
     * toString
     *
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ");
        sb.append(roomID);
        sb.append("\t");

        sb.append("Name: ");
        sb.append(roomName);
        sb.append("\t");

        sb.append("Description: ");
        sb.append(description);
        sb.append("\t");

        sb.append("Creator id: ");
        sb.append(creator.getUserID());
        sb.append("\t");

        sb.append("Creator name: ");
        sb.append(creator.getName());

        return sb.toString();
    }
}