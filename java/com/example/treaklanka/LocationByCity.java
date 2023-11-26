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

public class LocationByCity extends AppCompatActivity implements DetailsInterface{
    TextView BackTV,CityNameTV;
    RecyclerView LocationsRV;
    DatabaseReference database;

    private LocationAdapter locationAdapter;
    private ArrayList<PlaceByCity> locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_by_city);

        BackTV = findViewById(R.id.idTVBack);
        LocationsRV = findViewById(R.id.idRVLocations);
        CityNameTV=findViewById(R.id.idTVCityName);


        locationsList = new ArrayList<PlaceByCity>();
        locationAdapter = new LocationAdapter(this,locationsList,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LocationsRV.setLayoutManager(layoutManager);
        LocationsRV.setAdapter(locationAdapter);

        // Initialize Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PlaceByCity");

        String location=getIntent().getStringExtra("Name");
        CityNameTV.setText(location);
        loadLocations(location);


        BackTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback=new Intent(LocationByCity.this,FragmentManager.class);
                startActivity(intentback);
                finish();

            }
        });


    }
    private void loadLocations(String cityName) {
        // Query the Firebase database for locations in the selected city
        database= FirebaseDatabase.getInstance().getReference("PlaceByCity").child(cityName);

        ValueEventListener valueEventListener = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                locationsList.clear(); // Clear existing data
                for (DataSnapshot citySnapshot : snapshot.getChildren()) {
                        PlaceByCity place = citySnapshot.getValue(PlaceByCity.class);
                        if (place != null) {
                            locationsList.add(place);
                        }
                    }

                locationAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error reading from Firebase: " + error.getMessage());

            }

        });
    }

    @Override
    public void onItemClick(int position) {
        String location=getIntent().getStringExtra("Name");
        Intent intent2= new Intent(LocationByCity.this,destinationDetail.class);
        String selectedCity = location;
        intent2.putExtra("selectedCity", selectedCity);
        String selectedLocation = locationsList.get(position).getName();
        intent2.putExtra("selectedLocation", selectedLocation);
        startActivity(intent2);
        finish();

    }
}