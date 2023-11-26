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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RVWeatherAdapter extends RecyclerView.Adapter<RVWeatherAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RVWeatherModel> rvWeatherModels;

    public RVWeatherAdapter(Context context, ArrayList<RVWeatherModel> rvWeatherModels) {
        this.context = context;
        this.rvWeatherModels = rvWeatherModels;
    }

    @NonNull
    @Override
    public RVWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_weather_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVWeatherAdapter.ViewHolder holder, int position) {
        RVWeatherModel model= rvWeatherModels.get(position);
        holder.dayTV.setText(model.getTime());
        holder.temperatureTV.setText(model.getTemperature()+"°C");
        holder.conditionTV.setText(model.getCondition());
        Picasso.get().load("https:".concat(model.getIcon())).into(holder.iconIV);
        SimpleDateFormat input= new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output= new SimpleDateFormat("MM-dd");
        try{
            Date t = input.parse(model.getTime());
            holder.dayTV.setText(output.format(t));
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return rvWeatherModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dayTV,conditionTV,temperatureTV;
        private ImageView iconIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTV=itemView.findViewById(R.id.idTVDate);
            conditionTV=itemView.findViewById(R.id.idTVCondition);
            temperatureTV=itemView.findViewById(R.id.idTVTemperature);
            iconIV=itemView.findViewById(R.id.idIVIcon);

        }
    }
}
