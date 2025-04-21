package com.peerlink.peerlinkapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.models.Document;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    Context context;
    List<Document> documents;

    public DocumentAdapter(Context ctx, List<Document> list) {
        this.context = ctx;
        this.documents = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView documentName;
        public ViewHolder(View view) {
            super(view);
            documentName = view.findViewById(R.id.document_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_document, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.documentName.setText(documents.get(position).getFileName());
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }
}
