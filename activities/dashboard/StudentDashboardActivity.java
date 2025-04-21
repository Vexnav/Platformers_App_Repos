package com.peerlink.peerlinkapp.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.activities.auth.LoginActivity;
import com.peerlink.peerlinkapp.activities.profile.ViewProfileActivity;
import com.peerlink.peerlinkapp.activities.quiz.TakeQuizActivity;
import com.peerlink.peerlinkapp.activities.search.SearchTutorActivity;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class StudentDashboardActivity extends AppCompatActivity {

    SessionManager session;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        session = new SessionManager(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_search_tutor) {
                startActivity(new Intent(this, SearchTutorActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ViewProfileActivity.class));
                return true;
            } else if (itemId == R.id.nav_quiz) {
                startActivity(new Intent(this, TakeQuizActivity.class));
                return true;
            } else if (itemId == R.id.nav_logout) {
                session.logout();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });

        Toast.makeText(this, "Welcome Student", Toast.LENGTH_SHORT).show();
    }
}
