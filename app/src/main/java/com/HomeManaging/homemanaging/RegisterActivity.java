package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserAuthDao;
import com.abidingtech.base.dao.UserDao;
import com.abidingtech.base.model.User;

public class RegisterActivity extends BaseActivity {

    EditText userNameEdt, emailEdt, phoneEdt, passwordEdt;

    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEdt = findViewById(R.id._user_name_sign_up);
        emailEdt = findViewById(R.id._email_sign_up);
        phoneEdt = findViewById(R.id._sign_up_phone_number);
        passwordEdt = findViewById(R.id._sign_up_password);

        registerBtn = findViewById(R.id._sign_up_next_button);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, phone, password;
                name = userNameEdt.getText().toString();
                email = emailEdt.getText().toString();
                phone = phoneEdt.getText().toString();
                password = passwordEdt.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Password length must ge greater than 7", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);

                showLoading();

                UserAuthDao.getInstance().register(user, new DataCallback<String>() {
                    @Override
                    public void onData(String data) {
                        dismiss();
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        HomeActivity.loadActivity(RegisterActivity.this);
//                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onError(String error) {
                        dismiss();
                        Toast.makeText(RegisterActivity.this, error + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}