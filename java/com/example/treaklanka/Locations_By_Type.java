package com.example.treaklanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Locations_By_Type extends AppCompatActivity implements LocationByType_Interface {
    TextView BackTV, TypeNameTV;
    RecyclerView LocationsRV;
    DatabaseReference database;

    private LocationByType_Adapter locationByType_adapter;
    private ArrayList<LocationsByType_Model> locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_by_type);

        BackTV = findViewById(R.id.idTVBack);
        LocationsRV = findViewById(R.id.idRVLocations);
        TypeNameTV = findViewById(R.id.idTVTypeNAme);

        locationsList = new ArrayList<>();
        locationByType_adapter = new LocationByType_Adapter(this, locationsList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LocationsRV.setLayoutManager(layoutManager);
        LocationsRV.setAdapter(locationByType_adapter);

        String type = getIntent().getStringExtra("TypeName");
        TypeNameTV.setText(type);
        loadLocations(type);

        BackTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback = new Intent(Locations_By_Type.this, home.class);
                startActivity(intentback);
                finish();
            }
        });
    }

    private void loadLocations(String type) {
        database = FirebaseDatabase.getInstance().getReference("LocationsByType").child(type);

        ValueEventListener valueEventListener = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                locationsList.clear(); // Clear existing data
                for (DataSnapshot citySnapshot : snapshot.getChildren()) {
                    LocationsByType_Model locations = citySnapshot.getValue(LocationsByType_Model.class);
                    if (locations != null) {
                        locationsList.add(locations);
                    }
                }

                locationByType_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error reading from Firebase: " + error.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent3 = new Intent(Locations_By_Type.this, destinationDetail.class);
        String selectedCity = locationsList.get(position).getCity();
        intent3.putExtra("selectedCity", selectedCity);
        String selectedLocation = locationsList.get(position).getName();
        intent3.putExtra("selectedLocation", selectedLocation);
        startActivity(intent3);
        finish();
    }
}
