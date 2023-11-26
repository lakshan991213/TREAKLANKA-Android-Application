package com.example.treaklanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.treaklanka.FavoriteInterface;
import com.example.treaklanka.FavoriteModel;
import com.example.treaklanka.R;

import java.util.ArrayList;

public class Favorite_Adapter extends RecyclerView.Adapter<Favorite_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<FavoriteModel> favoriteList;
    private FavoriteInterface favoriteInterface;

    public Favorite_Adapter(Context context, ArrayList<FavoriteModel> favoriteList, FavoriteInterface favoriteInterface) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.favoriteInterface = favoriteInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FavoriteModel favoriteModel = favoriteList.get(position);
        holder.nameTV.setText(favoriteModel.getName());
        holder.districtTV.setText(favoriteModel.getDistrict());

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            // Notify the interface about the item click
            favoriteInterface.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, districtTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.headTitleTV);
            districtTV = itemView.findViewById(R.id.descriptionTV);
        }
    }

}
