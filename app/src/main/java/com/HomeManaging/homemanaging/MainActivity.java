package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserAuthDao;
import com.abidingtech.base.dao.UserDao;
import com.abidingtech.base.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            if (FirebaseAuth.getInstance().getCurrentUser() == null)
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            else {
                UserAuthDao.getInstance().getUser(FirebaseAuth.getInstance().getUid(), new DataCallback<User>() {
                    @Override
                    public void onData(User data) {
                        UserDao.getInstance().setUser(data);
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, error + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 2000);

    }
}