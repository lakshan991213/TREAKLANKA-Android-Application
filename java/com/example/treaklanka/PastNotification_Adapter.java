package com.example.treaklanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastNotification_Adapter extends RecyclerView.Adapter<PastNotification_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Past_Notification_Model> list;

    public PastNotification_Adapter(Context context, ArrayList<Past_Notification_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PastNotification_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastNotification_Adapter.MyViewHolder holder, int position) {

        Past_Notification_Model pastNotificationModel= list.get(position);
        holder.headTv.setText(pastNotificationModel.getTitle());
        holder.descriptionTV.setText(pastNotificationModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
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
