package com.peerlink.peerlinkapp.models;


public class Document {
    private int id;
    private int senderId;
    private int receiverId;
    private String filePath;
    private String fileName;
    private String timestamp;

    public Document(int id, int senderId, int receiverId, String filePath, String fileName, String timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getFilePath() { return filePath; }
    public String getFileName() { return fileName; }
    public String getTimestamp() { return timestamp; }
}
