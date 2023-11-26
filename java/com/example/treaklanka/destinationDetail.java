package com.example.treaklanka;

//import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class destinationDetail extends AppCompatActivity {

    TextView locationName, city, locationDescriptionOne, temperatureTV, conditionTV, locationDescriptionTwo, backTV, seeWeatherTV,favoriteTV;
    ImageView locationImage, iconIV, favoriteBtn;
    RecyclerView weatherRV;
    ProgressBar loadingPB;
    RelativeLayout weatherRL;
    private boolean isWeatherDetailShow = false;

    private DatabaseReference favoritesRef;
    private FirebaseAuth auth;

    private ArrayList<RVWeatherModel> rvWeatherModels;
    private RVWeatherAdapter rvWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_detail);

        temperatureTV = findViewById(R.id.idTVTemprature);
        iconIV = findViewById(R.id.idIVIcon);
        conditionTV = findViewById(R.id.idTVCondition);
        backTV = findViewById(R.id.idTVBack);
        seeWeatherTV = findViewById(R.id.idTVSeeWeather);
        weatherRV = findViewById(R.id.idRVWeather);
        loadingPB = findViewById(R.id.idPBLoading2);
        weatherRL = findViewById(R.id.idRLWeather);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        favoriteTV = findViewById(R.id.favoriteTV);

        rvWeatherModels = new ArrayList<>();
        rvWeatherAdapter = new RVWeatherAdapter(this, rvWeatherModels);
        weatherRV.setAdapter(rvWeatherAdapter);

        String selectedLocation = getIntent().getStringExtra("selectedLocation");
        String selectedCity = getIntent().getStringExtra("selectedCity");
        loadDataLocation(selectedCity, selectedLocation);
        getWeatherInfo(selectedCity);

        //favorite
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        favoritesRef = database.getReference("favorites").child(getUid());

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorites(selectedLocation, selectedCity);
            }
        });


        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(destinationDetail.this, LocationByCity.class);
                String selectedCity = getIntent().getStringExtra("selectedCity");
                back.putExtra("Name", selectedCity);
                startActivity(back);
                finish();
            }
        });

        seeWeatherTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isWeatherDetailShow = !isWeatherDetailShow;
                if (isWeatherDetailShow) {
                    weatherRV.setVisibility(View.VISIBLE);
                    seeWeatherTV.setText("Less Weather Data");
                } else {
                    weatherRV.setVisibility(View.GONE);
                    seeWeatherTV.setText("Show Weather Data");
                }

            }
        });


    }

    private void loadDataLocation(String cityName, String locationName) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PlaceByCity")
                .child(cityName)
                .child(locationName);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PlaceByCity place = dataSnapshot.getValue(PlaceByCity.class);

                if (place != null) {
                    TextView locationNameTextView = findViewById(R.id.idTVLocationName);
                    locationNameTextView.setText(place.getName());

                    TextView cityNameTextView = findViewById(R.id.idTVCity);
                    cityNameTextView.setText(place.getPoint());

                    TextView description1TextView = findViewById(R.id.idTVLocationMainDescription);
                    description1TextView.setText(place.getDescription1());

                    TextView description2TextView = findViewById(R.id.idTVLocationDescription);
                    description2TextView.setText(place.getDescription2());

                    ImageView locationImageView = findViewById(R.id.idIVLocation);
                    Picasso.get().load(place.getImageURL()).into(locationImageView);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(destinationDetail.this, "NOT FOUND DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToFavorites(String location, String district) {
        DatabaseReference locationRef = favoritesRef.child(location);

        locationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    locationRef.removeValue();
                    Toast.makeText(destinationDetail.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                    favoriteTV.setText("Added to Favorites");
                    favoriteBtn.setImageResource(R.drawable.baseline_favorite_border_24);
                } else {
                    FavoriteModel destination = new FavoriteModel(location, district);
                    favoritesRef.child(location).setValue(destination);
                    Toast.makeText(destinationDetail.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    favoriteTV.setText("Remove from favorites");
                    favoriteBtn.setImageResource(R.drawable.baseline_favorite_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Call this method in your onCreate or onResume to set the initial state of the button
    private void setFavoriteButtonState(String location) {
        DatabaseReference locationRef = favoritesRef.child(location);

        locationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Location is in favorites, set the button image to fill
                    favoriteBtn.setImageResource(R.drawable.baseline_favorite_24);
                    favoriteTV.setText("Remove from favorites");
                } else {
                    // Location is not in favorites, set the button image to not fill
                    favoriteBtn.setImageResource(R.drawable.baseline_favorite_border_24);
                    favoriteTV.setText("Added to Favorites");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors if needed
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String selectedLocation = getIntent().getStringExtra("selectedLocation");
        setFavoriteButtonState(selectedLocation);
    }





    private String getUid() {
        return Objects.requireNonNull(auth.getCurrentUser()).getUid();
    }


    private void getWeatherInfo(String cityName){
        String url= "https://api.weatherapi.com/v1/forecast.json?key=6ecf8262d0f44543bb391631233108&q="+cityName+"&days=7&aqi=no&alerts=no";
        RequestQueue requestQueue= Volley.newRequestQueue(destinationDetail.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        rvWeatherModels.clear();

                        try {
                            String temperature = response.getJSONObject("current").getString("temp_c");
                            temperatureTV.setText(temperature + "°C ");
                            int isDay=response.getJSONObject("current").getInt("is_day");
                            String condition= response.getJSONObject("current").getJSONObject("condition").getString("text");
                            String conditionIcon= response.getJSONObject("current").getJSONObject("condition").getString("icon");
                            Picasso.get().load("https:".concat(conditionIcon)).into(iconIV);
                            conditionTV.setText(condition);
                            /*if (isDay==1) {
                                Picasso.get().load("https://img.freepik.com/free-vector/realistic-blue-sky-background_1048-6707.jpg?w=740&t=st=1693640967~exp=1693641567~hmac=170349659f91776d7ee708ce93f2e2c861fde4f67e664c372386baa318b02a6a").into(backIV);
                            }
                            else {
                                Picasso.get().load("https://img.freepik.com/free-photo/full-moon-with-dark-cloudscapes-night-halloween-concept_9083-8075.jpg?w=1060&t=st=1693641022~exp=1693641622~hmac=adae5a9aae1cd1c6c520f974dd2d1463361a6b321f053cf1382bc60a1999dee4").into(backIV);
                            }*/

                            JSONObject forecastObj = response.getJSONObject("forecast");
                            JSONArray forecastdayArray = forecastObj.getJSONArray("forecastday");

                            for (int i = 0; i < forecastdayArray.length(); i++) {
                                JSONObject dayobj = forecastdayArray.getJSONObject(i);

                                String date = dayobj.getString("date");
                                String condi = dayobj.getJSONObject("day").getJSONObject("condition").getString("text");
                                String temp = dayobj.getJSONObject("day").getString("avgtemp_c");
                                String icon = dayobj.getJSONObject("day").getJSONObject("condition").getString("icon");
                                rvWeatherModels.add(new RVWeatherModel(date,temp,icon,condi));



                            }
                            loadingPB.setVisibility(View.GONE);
                            rvWeatherAdapter.notifyDataSetChanged();
                            weatherRL.setVisibility(View.VISIBLE);





                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(destinationDetail.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Toast.makeText(destinationDetail.this, "Please enter a valid city", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

        requestQueue.add(jsonObjectRequest);


    }

}