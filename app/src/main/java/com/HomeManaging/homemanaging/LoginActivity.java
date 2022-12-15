package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button Register_Btn, Login_Btn;
    TextView ForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Register_Btn = findViewById(R.id.register_btn);
        Register_Btn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,RegisterActivity.class)));

        ForgotPassword = findViewById(R.id._sign_in_forgot_your_password);
        ForgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class)));

        Login_Btn = findViewById(R.id._sign_in_with_user_mail_next_button);
        Login_Btn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,HomeActivity.class)));

    }
}