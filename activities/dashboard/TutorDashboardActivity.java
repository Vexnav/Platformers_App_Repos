package com.peerlink.peerlinkapp.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.peerlink.peerlinkapp.MessageViewerActivity;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.activities.auth.LoginActivity;
import com.peerlink.peerlinkapp.activities.chat.DocumentViewerActivity;
import com.peerlink.peerlinkapp.activities.profile.ViewProfileActivity;
import com.peerlink.peerlinkapp.activities.quiz.CreateQuizActivity;
import com.peerlink.peerlinkapp.activities.quiz.QuizResultActivity;
import com.peerlink.peerlinkapp.activities.session.SessionRequestActivity;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class TutorDashboardActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard);

        session = new SessionManager(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.tutor_bottom_nav);
        BottomNavigationView navView = findViewById(R.id.tutor_bottom_nav);

        navView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_messages) {
                startActivity(new Intent(this, MessageViewerActivity.class));
                return true;
            } else if (id == R.id.nav_quizzes) {
                startActivity(new Intent(this, CreateQuizActivity.class));
                return true;
            } else if (id == R.id.nav_documents) {
                startActivity(new Intent(this, DocumentViewerActivity.class));
                return true;
            } else if (id == R.id.nav_logout) {
                session.logout();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });


        Toast.makeText(this, "Welcome Tutor", Toast.LENGTH_SHORT).show();
    }
}
