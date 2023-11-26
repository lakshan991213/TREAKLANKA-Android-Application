package com.example.treaklanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LocationByType_Adapter extends RecyclerView.Adapter<LocationByType_Adapter.TypeViewHolder> {
    private final LocationByType_Interface locationByType_interface;
    private Context context;
    private List<LocationsByType_Model> locationsByType_models;

    private TopDestinationInterface topDestinationInterface;

    public LocationByType_Adapter(Context context, ArrayList<LocationsByType_Model> locationsByType_models,LocationByType_Interface locationByType_interface) {
        this.context = context;
        this.locationsByType_models = locationsByType_models;
        this.locationByType_interface= locationByType_interface;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.locationsbytype_item, parent, false);
        return new TypeViewHolder(view,locationByType_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        LocationsByType_Model locationsByType_model = locationsByType_models.get(position);

        holder.nameTextView.setText(locationsByType_model.getName());
        Picasso.get().load(locationsByType_model.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locationsByType_models.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public TypeViewHolder(@NonNull View itemView,LocationByType_Interface locationByType_interface) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.idTVLocationName);
            imageView = itemView.findViewById(R.id.idIVLocationPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (locationByType_interface != null){
                        int pos=getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            locationByType_interface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}
