package com.peerlink.peerlinkapp.models;


public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private String timestamp;

    public Message(int anInt, int cInt, int i, String content, String timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }


    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
}