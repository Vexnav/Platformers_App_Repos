package com.peerlink.peerlinkapp.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginBtn;
    private CheckBox rememberMe;
    private DatabaseHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        // Auto-login if user is already remembered
        if (session.isLoggedIn()) {
            navigateToDashboard(session.getUserRole());
            finish();
            return;
        }

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        rememberMe = findViewById(R.id.remember_me_checkbox);

        loginBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            String role = db.validateLogin(email, password);
            if (role != null) {
                if (rememberMe.isChecked()) {
                    session.createLoginSession(email, role);
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                navigateToDashboard(role);
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials or not approved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToDashboard(String role) {
        Intent intent;
        if (role.equalsIgnoreCase("admin")) {
            intent = new Intent(this, com.peerlink.peerlinkapp.activities.dashboard.AdminDashboardActivity.class);
        } else if (role.equalsIgnoreCase("tutor")) {
            intent = new Intent(this, com.peerlink.peerlinkapp.activities.dashboard.TutorDashboardActivity.class);
        } else {
            intent = new Intent(this, com.peerlink.peerlinkapp.activities.dashboard.StudentDashboardActivity.class);
        }
        startActivity(intent);
    }
}
