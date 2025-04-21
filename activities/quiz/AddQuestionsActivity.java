package com.peerlink.peerlinkapp.activities.quiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;

public class AddQuestionsActivity extends AppCompatActivity {

    EditText questionText, correctAnswer;
    Button addQuestionBtn;
    DatabaseHelper db;
    int quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        db = new DatabaseHelper(this);
        quizId = getIntent().getIntExtra("quizId", -1);

        questionText = findViewById(R.id.question_input);
        correctAnswer = findViewById(R.id.answer_input);
        addQuestionBtn = findViewById(R.id.add_question_button);

        addQuestionBtn.setOnClickListener(v -> {
            String question = questionText.getText().toString().trim();
            String answer = correctAnswer.getText().toString().trim();

            if (question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(this, "Fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.insertQuestion(quizId, question, answer);
            if (success) {
                Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();
                questionText.setText("");
                correctAnswer.setText("");
            } else {
                Toast.makeText(this, "Failed to add question", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
