package com.example.treaklanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteFragment extends Fragment implements FavoriteInterface {

    private RecyclerView favoriteRV;
    private DatabaseReference favoritesRef;
    private FirebaseAuth auth;
    Favorite_Adapter favorite_adapter;
    ArrayList<FavoriteModel> favoriteList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        favoritesRef = database.getReference("favorites").child(getUid());

        favoriteRV = rootView.findViewById(R.id.favoritetsRV);
        favoriteRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        favoriteList = new ArrayList<>();
        favorite_adapter = new Favorite_Adapter(getActivity(), favoriteList, this);
        favoriteRV.setAdapter(favorite_adapter);

        retrieveFavorites();

        return rootView;
    }

    private void retrieveFavorites() {
        favoritesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoriteList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String district = dataSnapshot.child("district").getValue(String.class);
                    FavoriteModel favoriteModel = new FavoriteModel(name, district);
                    favoriteList.add(favoriteModel);
                }

                favorite_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors if needed
            }
        });
    }

    private String getUid() {
        return Objects.requireNonNull(auth.getCurrentUser()).getUid();
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click
        Intent intent = new Intent(getActivity(), destinationDetail.class);
        intent.putExtra("selectedLocation", favoriteList.get(position).getName());
        intent.putExtra("selectedCity", favoriteList.get(position).getDistrict());
        startActivity(intent);
    }
}
