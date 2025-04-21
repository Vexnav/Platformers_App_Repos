package com.peerlink.peerlinkapp.activities.quiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;

public class TakeQuizActivity extends AppCompatActivity {
    TextView question;
    EditText answer;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        question = findViewById(R.id.quiz_question);
        answer = findViewById(R.id.answer_input);
        submitBtn = findViewById(R.id.submit_answer);

        question.setText("What is 2 + 2?");
        submitBtn.setOnClickListener(v -> Toast.makeText(this, "Answer submitted", Toast.LENGTH_SHORT).show());
    }
}