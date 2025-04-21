package com.peerlink.peerlinkapp.activities.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;

public class EditProfileActivity extends AppCompatActivity {
    EditText usernameInput;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        usernameInput = findViewById(R.id.username);
        saveBtn = findViewById(R.id.save_button);

        saveBtn.setOnClickListener(v -> {
            String name = usernameInput.getText().toString();
            Toast.makeText(this, "Profile updated: " + name, Toast.LENGTH_SHORT).show();
        });
    }
}