package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserAuthDao;
import com.abidingtech.base.dao.UserDao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends BaseActivity {

    Button Register_Btn, Login_Btn;
    TextView ForgotPassword;

    EditText emailEdt, passwordEdt;

    public static void loadActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordEdt = findViewById(R.id._sign_in_with_user_mail_password);
        emailEdt = findViewById(R.id._sign_in_with_email_username);

        Register_Btn = findViewById(R.id.register_btn);
        Register_Btn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        ForgotPassword = findViewById(R.id._sign_in_forgot_your_password);
        ForgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        Login_Btn = findViewById(R.id._sign_in_with_user_mail_next_button);
//        Login_Btn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, HomeActivity.class)));
        Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass;
                email = emailEdt.getText().toString();
                pass = passwordEdt.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                showLoading();

                UserAuthDao.getInstance().login(email, pass, new DataCallback<String>() {
                    @Override
                    public void onData(String data) {
                        HomeActivity.loadActivity(LoginActivity.this);
                        FirebaseMessaging.getInstance().subscribeToTopic("u_"+ UserDao.getInstance().getUserId());
                        FirebaseMessaging.getInstance().subscribeToTopic("u_"+ "all");
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        dismiss();
                    }

                    @Override
                    public void onError(String error) {
                        dismiss();

                        Toast.makeText(LoginActivity.this, error + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}