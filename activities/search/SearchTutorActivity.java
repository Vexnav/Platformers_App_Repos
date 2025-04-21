
package com.peerlink.peerlinkapp.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.peerlink.peerlinkapp.R;
import com.peerlink.peerlinkapp.activities.session.SessionRequestActivity;
import com.peerlink.peerlinkapp.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchTutorActivity extends AppCompatActivity {

    Spinner departmentSpinner, courseSpinner;
    ListView tutorListView;
    DatabaseHelper db;

    ArrayList<String> departments = new ArrayList<>();
    ArrayList<String> courses = new ArrayList<>();
    ArrayList<String> tutors = new ArrayList<>();
    ArrayAdapter<String> tutorAdapter;
    ArrayAdapter<String> courseAdapter;

    HashMap<String, ArrayList<String>> courseMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor);

        departmentSpinner = findViewById(R.id.department_spinner);
        courseSpinner = findViewById(R.id.course_spinner);
        tutorListView = findViewById(R.id.tutor_list);
        db = new DatabaseHelper(this);

        String[] departmentArray = {
                "Select Department",
                "Department of Information Technology",
                "Department of Applied Science",
                "Department of Engineering",
                "Biology"
        };

        for (String dept : departmentArray) {
            departments.add(dept);
        }

        courseMap.put("Department of Information Technology", new ArrayList<String>() {{
            add("Java");
            add("Python");
            add("Data Structures");
        }});
        courseMap.put("Department of Applied Science", new ArrayList<String>() {{
            add("Physics");
            add("Chemistry");
        }});
        courseMap.put("Department of Engineering", new ArrayList<String>() {{
            add("Mechanics");
            add("Electronics");
        }});
        courseMap.put("Biology", new ArrayList<String>() {{
            add("Botany");
            add("Genetics");
        }});

        departmentSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments));
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        courseSpinner.setAdapter(courseAdapter);

        tutorAdapter = new ArrayAdapter<String>(this, R.layout.item_tutor, R.id.tutor_name, tutors) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView deptView = view.findViewById(R.id.tutor_department);
                if (deptView != null) {
                    deptView.setText(departmentSpinner.getSelectedItem().toString());
                }
                return view;
            }
        };
        tutorListView.setAdapter(tutorAdapter);

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDept = departments.get(position);
                if (!selectedDept.equals("Select Department")) {
                    tutors.clear();
                    tutors.addAll(db.getTutorsByDepartment(selectedDept));
                    tutorAdapter.notifyDataSetChanged();

                    ArrayList<String> availableCourses = courseMap.get(selectedDept);
                    courseAdapter.clear();
                    if (availableCourses != null) {
                        courseAdapter.add("Select Course");
                        courseAdapter.addAll(availableCourses);
                        courseSpinner.setVisibility(View.VISIBLE);
                    } else {
                        courseSpinner.setVisibility(View.GONE);
                    }
                } else {
                    tutors.clear();
                    tutorAdapter.notifyDataSetChanged();
                    courseSpinner.setVisibility(View.GONE);
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        tutorListView.setOnItemClickListener((parent, view, position, id) -> {
            String tutorName = tutors.get(position);
            Intent intent = new Intent(this, SessionRequestActivity.class);
            intent.putExtra("tutorName", tutorName);
            startActivity(intent);
        });
    }
}
