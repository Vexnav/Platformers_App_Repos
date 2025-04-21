package com.peerlink.peerlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.models.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<Message> messages;

    public ChatAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        public ViewHolder(View view) {
            super(view);
            messageText = view.findViewById(R.id.message_text);
        }
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        holder.messageText.setText(messages.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
