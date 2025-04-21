package com.peerlink.peerlinkapp.activities.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;


public class ChangePasswordActivity extends AppCompatActivity {
    EditText currentPass, newPass;
    Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPass = findViewById(R.id.current_password);
        newPass = findViewById(R.id.new_password);
        changeBtn = findViewById(R.id.change_password_button);

        changeBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show();
        });
    }
}