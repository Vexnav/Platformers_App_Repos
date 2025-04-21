package com.peerlink.peerlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.models.Session;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {
    Context context;
    List<Session> sessions;

    public SessionAdapter(Context ctx, List<Session> list) {
        this.context = ctx;
        this.sessions = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sessionInfo;
        public ViewHolder(View view) {
            super(view);
            sessionInfo = view.findViewById(R.id.session_info);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_session, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sessionInfo.setText(sessions.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}