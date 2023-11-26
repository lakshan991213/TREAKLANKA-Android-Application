package com.example.treaklanka;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements location_interface,TopDestinationInterface {

    ImageView searchIV;
    ProgressBar PBLoading;
    RecyclerView RVCityName, RVCityDetail,RVTopDestination;
    LinearLayout cityNameLL;
    ShapeableImageView beachIv,hikeIv,templeIv,otherIv;

    ArrayList<cityNamesModel> cityNamesModels = new ArrayList<>();
    private boolean isCityListVisible = false;

    //Data base connect
    DatabaseReference database;
    TopDestinationItemAdapter adapter_location;
    ArrayList<TopDestinations> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_home, container, false);

        //cityNameLL = findViewById(R.id.idCityNameLL);
        PBLoading = rootView.findViewById(R.id.idPBLoading);
        searchIV = rootView.findViewById(R.id.idIVSearch);
        RVCityName = rootView.findViewById(R.id.idRVCity);
        //RVCityDetail = findViewById(R.id.idRVCityDetail);
        RVTopDestination=rootView.findViewById(R.id.idRVTopDestination);
        beachIv=rootView.findViewById(R.id.idIVBeach);
        hikeIv=rootView.findViewById(R.id.idIVHike);
        templeIv=rootView.findViewById(R.id.idIVTemple);
        otherIv=rootView.findViewById(R.id.idIVOther);

        database= FirebaseDatabase.getInstance().getReference("TopDestinations");
        list=new ArrayList<TopDestinations>();
        adapter_location=new TopDestinationItemAdapter(getActivity(),list,this);
        //RVTopDestination.setLayoutManager(new LinearLayoutManager(this));

        RVTopDestination.setAdapter(adapter_location);
        ValueEventListener valueEventListener = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TopDestinations topDestinations = dataSnapshot.getValue(TopDestinations.class);
                    list.add(topDestinations);
                }
                PBLoading.setVisibility(View.GONE);
                RVTopDestination.setVisibility(View.VISIBLE);
                adapter_location.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setCityNamesModels();

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cityName_Adapter adapter = new cityName_Adapter(getActivity(), cityNamesModels, this);
        RVCityName.setAdapter(adapter);


        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCityListVisible = !isCityListVisible;
                if (isCityListVisible) {
                    RVCityName.setVisibility(View.VISIBLE);
                } else {
                    RVCityName.setVisibility(View.GONE);
                    //RVCityDetail.setVisibility(View.GONE); // Hide city details when hiding the city list
                }
            }
        });

        beachIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToType=new Intent(getActivity(),Locations_By_Type.class);
                String Type="Beach";
                intentToType.putExtra("TypeName",Type);
                startActivity(intentToType);
                getActivity().finish();


            }
        });
        hikeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToType=new Intent(getActivity(),Locations_By_Type.class);
                String Type="Hike";
                intentToType.putExtra("TypeName",Type);
                startActivity(intentToType);
                getActivity().finish();

            }
        });
        templeIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentToType=new Intent(getActivity(),Locations_By_Type.class);
                String Type="Temple";
                intentToType.putExtra("TypeName",Type);
                startActivity(intentToType);
                getActivity().finish();

            }
        });
        otherIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToType=new Intent(getActivity(),Locations_By_Type.class);
                String Type="Other";
                intentToType.putExtra("TypeName",Type);
                startActivity(intentToType);
                getActivity().finish();

            }
        });
        return rootView;

    }

    private void setCityNamesModels() {
        // Populate cityNamesModels with city names
        String[] cityNames = getResources().getStringArray(R.array.city_names);
        for (int i = 0; i < cityNames.length; i++) {
            cityNamesModels.add(new cityNamesModel(cityNames[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent= new Intent(getActivity(),LocationByCity.class);
        intent.putExtra("Name",cityNamesModels.get(position).getCityName());
        startActivity(intent);
        getActivity().finish();

    }
    public void onTopDestinationClick(int position) {
        Intent intent = new Intent(getActivity(), destinationDetail.class);
        intent.putExtra("selectedLocation", list.get(position).getName());
        intent.putExtra("selectedCity",list.get(position).getCity());
        startActivity(intent);
    }
}