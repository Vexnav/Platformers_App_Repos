package com.peerlink.peerlinkapp.models;


public class Result {
    private int id;
    private int studentId;
    private int quizId;
    private int score;
    private String timestamp;

    public Result(int id, int studentId, int quizId, int score, String timestamp) {
        this.id = id;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getQuizId() { return quizId; }
    public int getScore() { return score; }
    public String getTimestamp() { return timestamp; }
}