package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Model.Feedback;
import com.HomeManaging.homemanaging.dao.FeedbackDao;
import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;

public class FeedBackActivity extends BaseActivity {

    EditText nameTxt, emailTxt, feedbackTxt;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        nameTxt = findViewById(R.id._your_name_feedback);
        emailTxt = findViewById(R.id._your_mail_feedback);
        feedbackTxt = findViewById(R.id._your_feedback);

        saveBtn = findViewById(R.id.SendFeedbackBtn);

        saveBtn.setOnClickListener(view -> {
            if (nameTxt.getText().toString().isEmpty()) {
                Toast.makeText(FeedBackActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (emailTxt.getText().toString().isEmpty()) {
                Toast.makeText(FeedBackActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (feedbackTxt.getText().toString().isEmpty()) {
                Toast.makeText(FeedBackActivity.this, "Please enter feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            Feedback feedback = new Feedback();
            feedback.setFeedback(feedbackTxt.getText().toString());
            feedback.setName(nameTxt.getText().toString());
            feedback.setEmail(emailTxt.getText().toString());
            showLoading();
            FeedbackDao.getInstance().add(feedback, new DataCallback<String>() {
                @Override
                public void onData(String data) {
                    dismiss();
                    Toast.makeText(FeedBackActivity.this, data + "", Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onError(String error) {
                    dismiss();
                    Toast.makeText(FeedBackActivity.this, error + "", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}