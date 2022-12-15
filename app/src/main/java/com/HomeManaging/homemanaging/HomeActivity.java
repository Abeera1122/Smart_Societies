package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigation();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id._frame_home, new HomeFragment()).commit();
        }



    }

    private void navigation() {

        bottomNavigationView = findViewById(R.id._bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id._bottom_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id._frame_home,new HomeFragment()).commit();
                    break;
                case R.id._bottom_poll:
                    getSupportFragmentManager().beginTransaction().replace(R.id._frame_home,new PollFragment()).commit();
                    break;
                case R.id._bottom_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id._frame_home,new SettingsFragment()).commit();
                    break;
            }

            return true;
        });
    }
}