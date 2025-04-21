package com.peerlink.peerlinkapp.helpers;


public class QuizUtils {
    public static int calculateScore(java.util.List<String> correctAnswers, java.util.List<String> userAnswers) {
        int score = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            if (correctAnswers.get(i).equalsIgnoreCase(userAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }
}
