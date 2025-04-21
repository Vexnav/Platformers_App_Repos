package com.peerlink.peerlinkapp.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText nameField, emailField, passwordField;
    Spinner departmentSpinner, roleSpinner;
    Button registerBtn;
    DatabaseHelper db;

    String selectedDepartment = "", selectedRole = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        nameField = findViewById(R.id.username);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        departmentSpinner = findViewById(R.id.department_spinner);
        roleSpinner = findViewById(R.id.role_spinner);
        registerBtn = findViewById(R.id.register_button);
        String[] departments = {
                "Select Department", "Department of Information Technology",
                "Department of Applied Science", "Department of Engineering", "Biology"
        };
        String[] roles = {"Select Role", "student", "tutor"};
        ArrayAdapter<String> deptAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(deptAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedDepartment = departments[pos];
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        roleSpinner.setAdapter(roleAdapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedRole = roles[pos];
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        registerBtn.setOnClickListener(v -> {
            String name = nameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedDepartment.equals("Select Department")) {
                Toast.makeText(this, "Please select a department", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedRole.equals("Select Role")) {
                Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = db.registerUser(name, email, password, selectedRole, selectedDepartment);
            if (inserted) {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();

                if (selectedRole.equalsIgnoreCase("student")) {
                    startActivity(new Intent(this, com.peerlink.peerlinkapp.activities.dashboard.StudentDashboardActivity.class));
                } else if (selectedRole.equalsIgnoreCase("tutor")) {
                    Toast.makeText(this, "Registration submitted for approval", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, com.peerlink.peerlinkapp.activities.auth.LoginActivity.class));
                }

                finish();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
