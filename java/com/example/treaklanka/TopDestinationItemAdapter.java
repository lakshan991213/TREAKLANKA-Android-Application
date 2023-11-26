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

public class TopDestinationItemAdapter extends RecyclerView.Adapter<TopDestinationItemAdapter.DestinationViewHolder> {
    private Context context;
    private List<TopDestinations> topDestinations;

    private TopDestinationInterface topDestinationInterface;

    public TopDestinationItemAdapter(Context context, ArrayList<TopDestinations> topDestinations,TopDestinationInterface topDestinationInterface) {
        this.context = context;
        this.topDestinations = topDestinations;
        this.topDestinationInterface= topDestinationInterface;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_name, parent, false);
        return new DestinationViewHolder(view,topDestinationInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        TopDestinations topDestinations1 = topDestinations.get(position);

        holder.nameTextView.setText(topDestinations1.getName());
        //holder.description1TextView.setText(topDestinations1.getDescription1());
        //holder.description2TextView.setText(topDestinations1.getDescription2());

        // Load the image using Picasso (you can use your preferred image loading library)
        Picasso.get().load(topDestinations1.getImgurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return topDestinations.size();
    }

    public class DestinationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView description1TextView;
        TextView description2TextView;
        ImageView imageView;

        public DestinationViewHolder(@NonNull View itemView,TopDestinationInterface topDestinationInterface) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.idTVLocationName);
            imageView = itemView.findViewById(R.id.idIVLocationPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (topDestinationInterface != null){
                        int pos=getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            topDestinationInterface.onTopDestinationClick(pos);
                        }

                    }
                }
            });
        }
    }
}

