package com.example.zootopia.entities;

import java.util.List;

/**
 * Created by Zootopia on 4/12/16.
 * Yilei Chu (ychu1)
 * Linpeng Lyu (linpengl)
 * Jialu Chen (jialuc)
 */
public class Mailbox {
    private List<Message> msgList;

    public Mailbox() {}

    public Mailbox(List<Message> msgList) {
        this.msgList = msgList;
    }

    public List<Message> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
    }
}
