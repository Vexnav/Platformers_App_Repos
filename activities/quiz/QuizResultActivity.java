package com.peerlink.peerlinkapp.activities.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;


public class QuizResultActivity extends AppCompatActivity {
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        resultText = findViewById(R.id.result_score);
        resultText.setText("Score: 4/5");
    }
}