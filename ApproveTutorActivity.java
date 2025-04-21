package com.peerlink.peerlinkapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.peerlink.peerlinkapp.adapters.TutorApproveAdapter;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;

import java.util.ArrayList;

public class ApproveTutorActivity extends AppCompatActivity {
    ListView tutorListView;
    DatabaseHelper db;
    ArrayList<String> tutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_tutor);

        tutorListView = findViewById(R.id.tutor_list);
        db = new DatabaseHelper(this);

        tutors = db.getPendingTutors();

        if (tutors.isEmpty()) {
            Toast.makeText(this, "No tutors pending approval.", Toast.LENGTH_SHORT).show();
        } else {
            TutorApproveAdapter adapter = new TutorApproveAdapter(this, tutors, db);
            tutorListView.setAdapter(adapter);
        }
    }
}
