package com.example.treaklanka;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlanTripFragment extends Fragment {

    LinearLayout continueButton;
    TextView backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_plan_trip, container, false);

        continueButton=rootView.findViewById(R.id.continueBtn);
        continueButton.setBackgroundResource(R.drawable.stoke_button);
        backBtn=rootView.findViewById(R.id.backBtn);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),PlanTripContactDetails.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(getActivity(),FragmentManager.class);
                startActivity(intent2);
            }
        });


        return rootView;
    }
}