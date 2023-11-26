package com.example.treaklanka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    RecyclerView todayNotificationRV, pastNotificationRV;
    DatabaseReference database,databasePast;
    notification_adapter notificationAdapter;
    PastNotification_Adapter pastNotificationAdapter;
    ArrayList<Notification_Model> new_list;
    ArrayList<Past_Notification_Model> past_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_notification, container, false);

        todayNotificationRV=rootView.findViewById(R.id.newNotificationRV);
        pastNotificationRV=rootView.findViewById(R.id.pastNotificationRV);

        database= FirebaseDatabase.getInstance().getReference("TodayNotification");
        databasePast= FirebaseDatabase.getInstance().getReference("PastNotification");

        new_list=new ArrayList<>();
        notificationAdapter=new notification_adapter(getActivity(),new_list);
        todayNotificationRV.setAdapter(notificationAdapter);

        past_list=new ArrayList<>();
        pastNotificationAdapter=new PastNotification_Adapter(getActivity(),past_list);
        pastNotificationRV.setAdapter(pastNotificationAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Notification_Model notificationModel=dataSnapshot.getValue(Notification_Model.class);
                    new_list.add(notificationModel);
                }

                notificationAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databasePast.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Past_Notification_Model notificationModel2=dataSnapshot.getValue(Past_Notification_Model.class);
                    past_list.add(notificationModel2);
                }
                pastNotificationAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }
}