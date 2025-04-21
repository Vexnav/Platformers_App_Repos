package com.peerlink.peerlinkapp.activities.session;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.peerlink.peerlinkapp.R;

public class SessionListActivity extends AppCompatActivity {
    ListView adminSessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        adminSessionList = findViewById(R.id.admin_session_list);
        // TODO: Load session data from DB
    }
}