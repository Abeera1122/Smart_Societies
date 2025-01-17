package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Model.Complaint;
import com.HomeManaging.homemanaging.dao.ComplaintDao;
import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;

public class ComplaintsActivity extends BaseActivity {

    EditText nameEdt, phoneEdt, emailEdt, subjectEdt, messageEdt;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        nameEdt = findViewById(R.id._your_name_contact_us);
        phoneEdt = findViewById(R.id._your_phone_number_contact_us);
        emailEdt = findViewById(R.id._your_mail_contact_us);
        subjectEdt = findViewById(R.id.Complaint_Subject);
        messageEdt = findViewById(R.id._your_message_contact_us);
        save = findViewById(R.id.RentHouseBookNow);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String subject = subjectEdt.getText().toString();
                String message = messageEdt.getText().toString();
                Complaint complaint = new Complaint();
                complaint.setName(name);
                complaint.setPhone(phone);
                complaint.setEmail(email);
                complaint.setSubject(subject);
                complaint.setMessage(message);
                if (name.isEmpty()) {
                    Toast.makeText(ComplaintsActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(ComplaintsActivity.this, "Please enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.isEmpty()) {
                    Toast.makeText(ComplaintsActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (subject.isEmpty()) {
                    Toast.makeText(ComplaintsActivity.this, "Please enter subject", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (message.isEmpty()) {
                    Toast.makeText(ComplaintsActivity.this, "Please enter message", Toast.LENGTH_SHORT).show();
                    return;
                }

                showLoading();
                ComplaintDao.getInstance().add(complaint, new DataCallback<String>() {
                    @Override
                    public void onData(String data) {
                        dismiss();
                        Toast.makeText(ComplaintsActivity.this, data + "", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onError(String error) {
                        dismiss();

                        Toast.makeText(ComplaintsActivity.this, error + "", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}