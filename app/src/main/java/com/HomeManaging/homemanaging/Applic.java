package com.HomeManaging.homemanaging;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class Applic extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
