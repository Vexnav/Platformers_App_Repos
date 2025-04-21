package com.peerlink.peerlinkapp.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class AdminDashboardActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        session = new SessionManager(this);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);


        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_logout) {
                session.logout();
                startActivity(new Intent(this, com.peerlink.peerlinkapp.activities.auth.LoginActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, com.peerlink.peerlinkapp.activities.profile.ViewProfileActivity.class));
                return true;
            } else if (itemId == R.id.nav_approve_tutors) {
                startActivity(new Intent(this, com.peerlink.peerlinkapp.ApproveTutorActivity.class));
                return true;
            } else if (itemId == R.id.nav_sessions) {
                startActivity(new Intent(this, com.peerlink.peerlinkapp.activities.session.SessionListActivity.class));
                return true;
            } else if (itemId == R.id.nav_messages) {
                startActivity(new Intent(this, com.peerlink.peerlinkapp.MessageViewerActivity.class));
                return true;
            }

            return false;
        });


        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
    }
}
