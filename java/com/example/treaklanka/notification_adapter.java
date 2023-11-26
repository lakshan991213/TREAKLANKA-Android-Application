package com.example.treaklanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.MyViewHolder> {

    Context context;

    ArrayList<Notification_Model> notification_list;

    public notification_adapter(Context context, ArrayList<Notification_Model> notification_list) {
        this.context = context;
        this.notification_list = notification_list;
    }

    @NonNull
    @Override
    public notification_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notification_adapter.MyViewHolder holder, int position) {

        Notification_Model notification_model= notification_list.get(position);
        holder.headTv.setText(notification_model.getTitle());
        holder.descriptionTV.setText(notification_model.getDescription());

    }

    @Override
    public int getItemCount() {
        return notification_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView headTv,descriptionTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            headTv= itemView.findViewById(R.id.headTitleTV);
            descriptionTV= itemView.findViewById(R.id.descriptionTV);
        }
    }
}
