package com.example.treaklanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PlanTrip_Activity extends AppCompatActivity {

    Button submitBtn;
    TextView backTV;
    Spinner travelModeS;
    ImageView startDateIV, endDateIV, startTimeIV;
    EditText  startDateTV, endDateTV, startTimeTV, noOfVisitorsTV, commentsTV;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        databaseReference = FirebaseDatabase.getInstance().getReference("Requests");

        backTV = findViewById(R.id.backTV);
        submitBtn = findViewById(R.id.submitBtn);
        startDateTV = findViewById(R.id.startDayInput);
        endDateTV = findViewById(R.id.endDayInput);
        startTimeTV = findViewById(R.id.startTimeInput);
        noOfVisitorsTV = findViewById(R.id.visitorNumberInput);
        travelModeS = findViewById(R.id.travelModeInput);
        commentsTV = findViewById(R.id.commentsInput);
        startDateIV = findViewById(R.id.startDayIV);
        endDateIV = findViewById(R.id.endDayIV);
        startTimeIV = findViewById(R.id.startTimeIV);

        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(PlanTrip_Activity.this, FragmentManager.class);
                startActivity(backIntent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    sendDataToFirebase();
                    Toast.makeText(getApplicationContext(), "Request submitted successfully", Toast.LENGTH_LONG).show();
                    Intent backIntent = new Intent(PlanTrip_Activity.this, FragmentManager.class);
                    startActivity(backIntent);
                }
            }
        });

        startDateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(startDateTV);
            }
        });

        endDateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(endDateTV);
            }
        });

        startTimeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(startTimeTV);
            }
        });
    }

    private void showDatePicker(final TextView dateTextView) {
        int style = AlertDialog.THEME_HOLO_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(PlanTrip_Activity.this, style, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
                dateTextView.setText(date);
            }
        }, 2023, 0, 15);
        datePickerDialog.show();
    }

    private void showTimePicker(final TextView timeTextView) {
        int style = AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePicker = new TimePickerDialog(PlanTrip_Activity.this, style, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                String time = String.valueOf(hours) + ":" + String.valueOf(minutes);
                timeTextView.setText(time);
            }
        }, 3, 60, true);
        timePicker.show();
    }

    private boolean validateForm() {
        if (
                TextUtils.isEmpty(startDateTV.getText().toString()) ||
                TextUtils.isEmpty(endDateTV.getText().toString()) ||
                TextUtils.isEmpty(startTimeTV.getText().toString()) ||
                TextUtils.isEmpty(noOfVisitorsTV.getText().toString()) ||
                TextUtils.isEmpty(travelModeS.getSelectedItem().toString())) {

            Toast.makeText(getApplicationContext(), "Please fill in all the required fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void sendDataToFirebase() {
        String name = getIntent().getStringExtra("NAME");
        String email = getIntent().getStringExtra("EMAIL");
        String contact = getIntent().getStringExtra("PHONE");
        String startDate = startDateTV.getText().toString().trim();
        String endDate = endDateTV.getText().toString().trim();
        String startTime = startTimeTV.getText().toString().trim();
        String noOfVisitors = noOfVisitorsTV.getText().toString().trim();
        String travelMode = travelModeS.getSelectedItem().toString().trim();
        String comments = commentsTV.getText().toString().trim();

        String requestId = databaseReference.push().getKey();

        Requests tripRequest = new Requests(name, email, contact, startDate, endDate, startTime, noOfVisitors, travelMode, comments);
        databaseReference.push().setValue(tripRequest);
    }
}
