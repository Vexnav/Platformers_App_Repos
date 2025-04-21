package com.peerlink.peerlinkapp.activities.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class CreateQuizActivity extends AppCompatActivity {

    private EditText titleInput;
    private Button createBtn;
    private DatabaseHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        // Initialize UI elements
        titleInput = findViewById(R.id.quiz_title);
        createBtn = findViewById(R.id.create_quiz_button);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        createBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Quiz title cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            String tutorEmail = session.getUserByEmail();
            if (tutorEmail == null || tutorEmail.isEmpty()) {
                Toast.makeText(this, "Session error: tutor not logged in", Toast.LENGTH_SHORT).show();
                return;
            }

            int tutorId = db.getUserIdByEmail(tutorEmail);
            if (tutorId == -1) {
                Toast.makeText(this, "Tutor ID not found", Toast.LENGTH_SHORT).show();
                return;
            }

            int quizId = db.insertQuizAndGetId(tutorId, title);
            if (quizId != -1) {
                Toast.makeText(this, "Quiz Created. Add questions now!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, AddQuestionsActivity.class);
                intent.putExtra("quizId", quizId);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to create quiz", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
