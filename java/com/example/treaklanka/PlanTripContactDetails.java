package com.example.treaklanka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlanTripContactDetails extends AppCompatActivity {
    EditText nameTV, emailTV, contactTV;
    TextView backBtn;
    LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip_contact_details);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        nameTV = findViewById(R.id.nameInput);
        emailTV = findViewById(R.id.emailInput);
        contactTV = findViewById(R.id.contactInput);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(PlanTripContactDetails.this, PlanTripFragment.class);
                startActivity(backIntent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    Intent nextIntent = new Intent(PlanTripContactDetails.this, PlanTrip_Activity.class);
                    String name = nameTV.getText().toString().trim();
                    String email = emailTV.getText().toString().trim();
                    String contact = contactTV.getText().toString().trim();
                    nextIntent.putExtra("NAME", name);
                    nextIntent.putExtra("EMAIL", email);
                    nextIntent.putExtra("PHONE", contact);
                    startActivity(nextIntent);
                }
            }
        });
    }

            private boolean validateForm() {
                if (TextUtils.isEmpty(nameTV.getText().toString()) ||
                        TextUtils.isEmpty(emailTV.getText().toString()) ||
                        TextUtils.isEmpty(contactTV.getText().toString())) {

                        Toast.makeText(getApplicationContext(), "Please fill in all the required fields", Toast.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                }
}