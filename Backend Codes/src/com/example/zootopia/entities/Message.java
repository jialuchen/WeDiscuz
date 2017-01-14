package com.example.zootopia.entities;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public abstract class Message {
    protected Integer msgID;
    protected Integer fromUserID;
    protected String fromUserName;
    protected Integer toUserID;
    protected String toUserName;
    protected String title;
    protected String time; //MM-dd-YYYY-HH:mm:ss
    protected String content;
    protected String status; //accept/refuse/pending

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(Integer fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getToUserID() {
        return toUserID;
    }

    public void setToUserID(Integer toUserID) {
        this.toUserID = toUserID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
