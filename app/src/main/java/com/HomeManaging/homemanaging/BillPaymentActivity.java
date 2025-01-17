package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.Payment;
import com.HomeManaging.homemanaging.dao.PaymentDao;
import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.callback.ImagePickerCallback;
import com.abidingtech.base.callback.ImageUploadCallback;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Calendar;

public class BillPaymentActivity extends BaseActivity {

    TextView PaymentDate;
    private int mDate, mMonth, mYear;
    Uri imageUri;

    Button save;

    EditText nameTxt, transactionId;
    ImageView imageView;

    Bill bill;

    public static void loadActivity(Context context, Bill bill) {
        Intent intent = new Intent(context, BillPaymentActivity.class);
        intent.putExtra("data", new Gson().toJson(bill));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bill = new Gson().fromJson(getIntent().getStringExtra("data"), Bill.class);
        setContentView(R.layout.activity_bill_payment);
        nameTxt = findViewById(R.id.BillPayment_Person_Name);
        transactionId = findViewById(R.id.Transaction_ID);
        imageView = findViewById(R.id.Receipt_image);
        save = findViewById(R.id.Send_Payment_Details);

        imageView.setOnClickListener(view -> getImage(new ImagePickerCallback() {
            @Override
            public void onImage(Uri uri) {
                imageUri = uri;
                Glide.with(BillPaymentActivity.this).load(imageUri).into(imageView);
            }

            @Override
            public void onError(String error) {

            }
        }));

        PaymentDate = findViewById(R.id.Bill_Payment_date);
        PaymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BillPaymentActivity.this, (datePicker, year, month, date) -> PaymentDate.setText(date + "/" + (month + 1) + "/" + year), mYear, mMonth, mDate);
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri == null) {
                    Toast.makeText(BillPaymentActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nameTxt.getText().toString().isEmpty()) {
                    Toast.makeText(BillPaymentActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (transactionId.getText().toString().isEmpty()) {
                    Toast.makeText(BillPaymentActivity.this, "Please enter transaction id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PaymentDate.getText().toString().isEmpty()) {
                    Toast.makeText(BillPaymentActivity.this, "Please select date", Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoading();
                uploadImage(imageUri, new ImageUploadCallback() {
                    @Override
                    public void onComplete(String imagePath) {
                        Payment payment = new Payment();
                        payment.setPaymentDate(PaymentDate.getText().toString());
                        payment.setBillId(bill.getId());
                        payment.setTransactionId(transactionId.getText().toString());
                        payment.setName(nameTxt.getText().toString());
                        payment.setImageUrl(imagePath);
//                        showLoading();
                        PaymentDao.getInstance().add(payment, new DataCallback<String>() {
                            @Override
                            public void onData(String data) {
                                dismiss();
                                Toast.makeText(BillPaymentActivity.this, data + "", Toast.LENGTH_SHORT).show();
                                HomeActivity.loadActivity(BillPaymentActivity.this);
                            }

                            @Override
                            public void onError(String error) {
                                dismiss();
                                Toast.makeText(BillPaymentActivity.this, error + "", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onProgress(int percentage) {

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(BillPaymentActivity.this, error + "", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });

            }
        });
    }
}