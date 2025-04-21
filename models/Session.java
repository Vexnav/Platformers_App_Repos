package com.peerlink.peerlinkapp.models;


public class Session {
    private int id;
    private int studentId;
    private int tutorId;
    private String subject;
    private String dateTime;
    private String status;

    public Session(int id, int studentId, int tutorId, String subject, String dateTime, String status) {
        this.id = id;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.subject = subject;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Session(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getTutorId() { return tutorId; }
    public String getDateTime() { return dateTime; }
    public String getStatus() { return status; }
}
