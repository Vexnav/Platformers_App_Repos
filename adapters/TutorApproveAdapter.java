package com.peerlink.peerlinkapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;

import java.util.ArrayList;

public class TutorApproveAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> tutors;
    DatabaseHelper db;

    public TutorApproveAdapter(Context ctx, ArrayList<String> list, DatabaseHelper dbHelper) {
        super(ctx, 0, list);
        context = ctx;
        tutors = list;
        db = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String email = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tutor_approve, parent, false);
        }

        TextView emailText = convertView.findViewById(R.id.tutor_email_text);
        Button approveBtn = convertView.findViewById(R.id.approve_button);
        Button declineBtn = convertView.findViewById(R.id.decline_button);

        emailText.setText(email);

        approveBtn.setOnClickListener(v -> {
            db.updateTutorApproval(email, true);
            Toast.makeText(context, "Approved: " + email, Toast.LENGTH_SHORT).show();
            tutors.remove(position);
            notifyDataSetChanged();
        });

        declineBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Decline Tutor")
                    .setMessage("Are you sure you want to decline this tutor?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteUserByEmail(email);
                        Toast.makeText(context, "Declined: " + email, Toast.LENGTH_SHORT).show();
                        tutors.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        return convertView;
    }
}
