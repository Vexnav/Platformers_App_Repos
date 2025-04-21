package com.peerlink.peerlinkapp.activities.chat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {
    ListView chatListView;
    ArrayList<String> chatUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chatListView = findViewById(R.id.chat_list);
        chatUsers = new ArrayList<>();

        chatUsers.add("Tutor - Alice");
        chatUsers.add("Student - Bob");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatUsers);
        chatListView.setAdapter(adapter);
    }
}
