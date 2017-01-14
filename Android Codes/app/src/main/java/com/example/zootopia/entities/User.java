package com.example.zootopia.entities;

import java.util.ArrayList;
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
    private String description;
    private List<User> friendList;
    private List<Integer> chatroomList;

    public User(Integer userID, String email, String name, String gender, String description, List<User> friendList, List<Integer> chatroomList) {
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.friendList = friendList;
        this.chatroomList = chatroomList;
    }

    public User(Integer userID, String email, String name, String gender, String description) {
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.description = description;
        friendList = new ArrayList<User>();
        chatroomList = new ArrayList<Integer>();
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.gender = "";
        friendList = new ArrayList<User>();
        chatroomList = new ArrayList<Integer>();
    }

    // getters and setters
    public User() {
        this.gender = "";
        friendList = new ArrayList<User>();
        chatroomList = new ArrayList<Integer>();
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

    public List<Integer> getChatroomList() {
        return chatroomList;
    }

    public void setChatroomList(List<Integer> chatroomList) {
        this.chatroomList = chatroomList;
    }

    // business

    /**
     * addFriend -  add a User object to the friend list
     *
     * @param newFriend
     * @return
     */
    public boolean addFriend(User newFriend) {
        for (User friend : friendList) {
            if (newFriend.getUserID() == friend.getUserID())
                return false;
        }
        friendList.add(newFriend);
        return true;
    }

    /**
     * deleteFriend - delete friend with give user id
     *
     * @param id
     * @return
     */
    public boolean deleteFriend(int id) {
        User temp = null;
        for (User f : friendList) {
            if (f.getUserID() == id)
                temp = f;
        }
        if (temp != null) {
            friendList.remove(temp);
            return true;
        } else
            return false;
    }

    /**
     * findFriend - find a friend by the user id
     *
     * @param id
     * @return
     */
    public User findFriend(int id) {
        User temp = null;
        for (User f : friendList) {
            if (f.getUserID() == id)
                temp = f;
        }
        return temp;
    }

    /**
     * addChatroom - add Chatroom object to the chatroomList
     *
     * @param id
     */
    public void addChatroom(Integer id) {
        chatroomList.add(id);
    }

    /**
     * deleteChatroom - delete chatroom from the list
     *
     * @param id
     * @return
     */
    public boolean deleteChatroom(int id) {
        Integer temp = null;
        for (Integer roomid : chatroomList) {
            if (roomid == id) {
                temp = roomid;
            }
        }
        if (temp != null) {
            chatroomList.remove(temp);
            return true;
        } else {
            return false;
        }
    }

    /**
     * findChatroom - find a chatroom by id
     *
     * @param id
     * @return
     */
    public int findChatroom(int id) {
        Integer chatroomid = null;
        for (Integer roomid : chatroomList) {
            if (roomid == id) {
                chatroomid = id;
            }
        }
        return chatroomid;
    }

    /**
     * findFriendByEmail - find a friend by the user id
     *
     * @param email
     * @return
     */
    public User findFriendByEmail(String email) {
        User temp = null;
        for (User f : friendList) {
            if (f.getEmail().equals(email))
                temp = f;
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
        sb.append(userID);
        sb.append("\t");

        sb.append("Name: ");
        sb.append(name);
        sb.append("\t");

        sb.append("Email: ");
        sb.append(email);
        sb.append("\t");

        sb.append("Gender: ");
        sb.append(gender);
        sb.append("\t");

        sb.append("Description: ");
        sb.append(description);

        return sb.toString();
    }
}
