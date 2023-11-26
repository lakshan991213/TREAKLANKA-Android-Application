package com.example.treaklanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FragmentManager extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PlanTripFragment planTripFragment=new PlanTripFragment();
    NotificationFragment notificationFragment=new NotificationFragment();
    FavoriteFragment favoriteFragment=new FavoriteFragment();

    private static final int MENU_HOME = R.id.idHome;
    private static final int MENU_NOTIFICATION = R.id.idNotification;
    private static final int MENU_PlanTrip = R.id.idPlan;
    private static final int MENU_Favorite = R.id.idFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);

        bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        getSupportFragmentManager().beginTransaction().replace(R.id.idFrameLayout, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == MENU_HOME) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.idFrameLayout, homeFragment).commit();
                    return true;
                } else if (itemId == MENU_NOTIFICATION) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.idFrameLayout, notificationFragment).commit();
                    return true;
                }else if (itemId == MENU_PlanTrip) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.idFrameLayout, planTripFragment).commit();
                    return true;
                }
                else if (itemId == MENU_Favorite) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.idFrameLayout, favoriteFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

}