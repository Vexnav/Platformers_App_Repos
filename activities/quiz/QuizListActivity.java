package com.peerlink.peerlinkapp.activities.quiz;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;

import java.util.ArrayList;

public class QuizListActivity extends AppCompatActivity {
    ListView quizList;
    ArrayList<String> quizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        quizList = findViewById(R.id.quiz_list);
        quizzes = new ArrayList<>();
        quizzes.add("Math Basics");
        quizzes.add("Physics Intro");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizzes);
        quizList.setAdapter(adapter);
    }
}