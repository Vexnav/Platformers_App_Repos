package com.peerlink.peerlinkapp.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;
import com.peerlink.peerlinkapp.helpers.SessionManager;

public class ViewProfileActivity extends AppCompatActivity {
    TextView name, email, role, department;
    TextView editProfileLink, changePasswordLink;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        role = findViewById(R.id.profile_role);
        department = findViewById(R.id.profile_department);
        editProfileLink = findViewById(R.id.edit_profile_link);
        changePasswordLink = findViewById(R.id.change_password_link);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        if (session.isLoggedIn()) {
            String userEmail = session.getUserByEmail();
            String[] userData = db.getUserByEmail(userEmail);
            if (userData != null) {
                name.setText(userData[0]);
                email.setText(userData[1]);
                role.setText(userData[2]);
                department.setText(userData[3]);
            }
        }

        editProfileLink.setOnClickListener(v ->
                startActivity(new Intent(this, EditProfileActivity.class)));

        changePasswordLink.setOnClickListener(v ->
                startActivity(new Intent(this, ChangePasswordActivity.class)));
    }
}
