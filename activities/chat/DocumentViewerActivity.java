package com.peerlink.peerlinkapp.activities.chat;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.peerlink.peerlinkapp.R;

public class DocumentViewerActivity extends AppCompatActivity {
    TextView documentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_viewer);

        documentName = findViewById(R.id.document_name);
        String filename = getIntent().getStringExtra("filename");
        documentName.setText("Viewing: " + filename);
    }
}