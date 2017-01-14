package com.example.zootopia.entities;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class MessageChatroom extends Message {
    private Integer creatorID;
    private String creatorName;
    private Integer roomID;
    private String roomName;

    public MessageChatroom() {}
    public MessageChatroom(Integer messageID, Integer fromID, String fromName, Integer toID, String toName, Integer creatorID, String creatorName, Integer roomID, String roomName, String title, String time, String content, String status) {
        this.msgID = messageID;
        this.fromUserID = fromID;
        this.fromUserName = fromName;
        this.toUserID = toID;
        this.toUserName = toName;
        this.creatorID = creatorID;
        this.creatorName = creatorName;
        this.roomID = roomID;
        this.roomName = roomName;
        this.title = title;
        this.content = content;
        this.status = status;
        this.time = time;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
}
