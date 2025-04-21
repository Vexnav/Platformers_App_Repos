package com.peerlink.peerlinkapp.activities.session;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.activities.dashboard.StudentDashboardActivity;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class SessionRequestActivity extends AppCompatActivity {

    EditText tutorEmailField, messageField;
    Button requestBtn;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_request);

        tutorEmailField = findViewById(R.id.tutor_email);
        messageField = findViewById(R.id.message_field);
        requestBtn = findViewById(R.id.request_button);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        String tutorName = getIntent().getStringExtra("tutorName");
        if (tutorName != null) {
            tutorEmailField.setText(tutorName);
        }

        requestBtn.setOnClickListener(v -> {
            String tutorEmail = tutorEmailField.getText().toString().trim();
            String message = messageField.getText().toString().trim();
            String studentEmail = session.getUserByEmail();

            if (tutorEmail.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Please enter tutor email and a message", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.saveMessageRequest(studentEmail, tutorEmail, message);
            if (success) {
                Toast.makeText(this, "Request sent successfully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, StudentDashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Failed to send request.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
