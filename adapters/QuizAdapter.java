package com.peerlink.peerlinkapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.models.Quiz;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    Context context;
    List<Quiz> quizList;

    public QuizAdapter(Context ctx, List<Quiz> list) {
        this.context = ctx;
        this.quizList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView quizTitle;
        public ViewHolder(View view) {
            super(view);
            quizTitle = view.findViewById(R.id.quiz_title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_quiz, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.quizTitle.setText(quizList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}