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
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private DetailsInterface detailsInterface;;
    private Context context;
    private List<PlaceByCity> placeByCityList;

    public LocationAdapter(Context context, List<PlaceByCity> placeByCityList,DetailsInterface detailsInterface) {
        this.context = context;
        this.placeByCityList = placeByCityList;
        this.detailsInterface=detailsInterface;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loca_by_city, parent, false);
        return new LocationViewHolder(view,detailsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        PlaceByCity place = placeByCityList.get(position);

        holder.locationNameTextView.setText(place.getName());
        //holder.description1TextView.setText(location.getDescription1());
        //holder.description2TextView.setText(location.getDescription2());

        // Load the image using Picasso (or your preferred image loading library)
        Picasso.get().load(place.getImageURL()).into(holder.locationImageView);
    }

    @Override
    public int getItemCount() {
        return placeByCityList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        ImageView locationImageView;
        TextView locationNameTextView;
        //TextView description1TextView;
        //TextView description2TextView;

        public LocationViewHolder(@NonNull View itemView,DetailsInterface detailsInterface) {
            super(itemView);
            locationImageView = itemView.findViewById(R.id.idIVLocationPicture);
            locationNameTextView = itemView.findViewById(R.id.idTVLocationName);
            //description1TextView = itemView.findViewById(R.id.tv_description1);
            //description2TextView = itemView.findViewById(R.id.tv_description2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (detailsInterface != null){
                        int pos=getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            detailsInterface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}

