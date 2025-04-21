package com.peerlink.peerlinkapp.models;

public class Question {
    private int id;
    private int quizId;
    private String questionText;
    private String correctAnswer;

    public Question(int id, int quizId, String questionText, String correctAnswer) {
        this.id = id;
        this.quizId = quizId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public int getId() { return id; }
    public int getQuizId() { return quizId; }
    public String getQuestionText() { return questionText; }
    public String getCorrectAnswer() { return correctAnswer; }
}

