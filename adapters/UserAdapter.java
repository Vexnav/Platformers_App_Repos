package com.peerlink.peerlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.peerlink.peerlinkapp.models.*;
import com.peerlink.peerlinkapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    List<User> users;

    public UserAdapter(Context ctx, List<User> userList) {
        this.context = ctx;
        this.users = userList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.username);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
