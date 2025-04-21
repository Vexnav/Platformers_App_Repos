package com.peerlink.peerlinkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.helpers.DatabaseHelper;
import com.peerlink.peerlinkapp.helpers.SessionManager;
import com.peerlink.peerlinkapp.models.Message;

import java.util.ArrayList;

public class MessageViewerActivity extends AppCompatActivity {

    ListView messageList;
    DatabaseHelper db;
    SessionManager session;
    ArrayList<Message> messageData;
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_viewer);

        messageList = findViewById(R.id.message_list);
        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        String tutorEmail = session.getUserByEmail();
        messageData = db.getMessagesForTutorDetailed(tutorEmail);

        adapter = new MessageAdapter();
        messageList.setAdapter(adapter);
    }

    class MessageAdapter extends BaseAdapter {
        @Override public int getCount() { return messageData.size(); }
        @Override public Object getItem(int pos) { return messageData.get(pos); }
        @Override public long getItemId(int pos) { return messageData.get(pos).getId(); }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_message, parent, false);
            }

            TextView sender = convertView.findViewById(R.id.message_sender);
            TextView content = convertView.findViewById(R.id.message_content);
            Button approveBtn = convertView.findViewById(R.id.btn_approve);
            Button declineBtn = convertView.findViewById(R.id.btn_decline);

            Message msg = messageData.get(pos);
            sender.setText("From Student ID: " + msg.getSenderId());
            content.setText(msg.getContent());

            approveBtn.setOnClickListener(v -> {
                db.approveSessionRequest(msg.getId());
                Toast.makeText(MessageViewerActivity.this, "Approved", Toast.LENGTH_SHORT).show();
                redirectToDashboard();
            });

            declineBtn.setOnClickListener(v -> {
                db.declineSessionRequest(msg.getId());
                Toast.makeText(MessageViewerActivity.this, "Declined", Toast.LENGTH_SHORT).show();
                redirectToDashboard();
            });

            return convertView;
        }

        private void redirectToDashboard() {
            Intent intent = new Intent(MessageViewerActivity.this, com.peerlink.peerlinkapp.activities.dashboard.TutorDashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
