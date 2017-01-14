package com.example.zootopia.entities;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class MessageFriend extends Message {
    public MessageFriend() {
    }

    public MessageFriend(Integer messageID, Integer fromID, String fromName, Integer toID, String toName, String title, String content, String status) {
        this.msgID = messageID;
        this.fromUserID = fromID;
        this.fromUserName = fromName;
        this.toUserID = toID;
        this.toUserName = toName;
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
