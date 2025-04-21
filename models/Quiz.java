package com.peerlink.peerlinkapp.models;

public class Quiz {
    private int id;
    private int tutorId;
    private String title;

    public Quiz(int id, int tutorId, String title) {
        this.id = id;
        this.tutorId = tutorId;
        this.title = title;
    }

    public int getId() { return id; }
    public int getTutorId() { return tutorId; }
    public String getTitle() { return title; }
}
