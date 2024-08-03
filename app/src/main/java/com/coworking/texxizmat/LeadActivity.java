package com.coworking.texxizmat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.coworking.tehhizmat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lead);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);





    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener

            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;

            if (item.getItemId() == R.id.navigation_home) {
                intent = new Intent(LeadActivity.this, MainMenuActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.navigation_dashboard) {
                intent = new Intent(LeadActivity.this, MainMenuActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(LeadActivity.this, MainMenuActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.navigation_notifications) {
                intent = new Intent(LeadActivity.this, MainMenuActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.navigation_settings) {
                intent = new Intent(LeadActivity.this, MainMenuActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }

        }
    };


}