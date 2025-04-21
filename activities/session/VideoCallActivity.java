package com.peerlink.peerlinkapp.activities.session;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;


public class VideoCallActivity extends AppCompatActivity {
    Button startCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        startCallBtn = findViewById(R.id.start_call_button);
        startCallBtn.setOnClickListener(v -> Toast.makeText(this, "Starting video call...", Toast.LENGTH_SHORT).show());
    }
}