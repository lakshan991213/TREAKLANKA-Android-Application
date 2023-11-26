package com.example.treaklanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.treaklanka.R;
import com.example.treaklanka.cityNamesModel;

import java.util.ArrayList;

public class cityName_Adapter extends RecyclerView.Adapter<cityName_Adapter.MyViewHolder> {
    private final location_interface location_interface ;
    Context context;
    ArrayList<cityNamesModel> cityNamesModels;

    public cityName_Adapter(Context context, ArrayList<cityNamesModel> cityNamesModels,location_interface location_interface) {
        this.context = context;
        this.cityNamesModels = cityNamesModels;
        this.location_interface= location_interface;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.city_search, parent, false);
        return new MyViewHolder(view, location_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cityNameTV.setText(cityNamesModels.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return cityNamesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cityNameTV;

        public MyViewHolder(@NonNull View itemView, location_interface location_interface) {
            super(itemView);
            cityNameTV = itemView.findViewById(R.id.idTVCityName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (location_interface!=null){
                        int pos= getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION){
                            location_interface.onItemClick(pos);

                        }
                    }
                }
            });


        }
    }
}
