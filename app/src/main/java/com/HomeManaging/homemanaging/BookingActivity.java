package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Model.BookingListModel;
import com.HomeManaging.homemanaging.Model.Room;
import com.HomeManaging.homemanaging.dao.RoomDao;
import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserDao;
import com.google.gson.Gson;

import java.util.Calendar;

public class BookingActivity extends BaseActivity {

    EditText BookingStartDate, BookingEndDate, personsEdt;
    int mDate, mMonth, mYear;
    Button BookingBtn;

    Room room;

    Button save;


    TextView nameText, locationText;

    public static void loadActivity(Context context, Room room) {
        // Intent intent = new Intent(context, BookingActivity.class);
        // intent.putExtra("data", new Gson().toJson(room));
        // context.startActivity(intent);
    }

    TextView totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        room = new Gson().fromJson(getIntent().getStringExtra("data"), Room.class);

        totalTxt = findViewById(R.id.total_txt);

        nameText = findViewById(R.id.RentHouseName);
        locationText = findViewById(R.id.RentHouseLocation);
        personsEdt = findViewById(R.id.BookingPerson);

        nameText.setText(room.getName());

        save = findViewById(R.id._booking_successfully_button_btn);
        locationText.setText(room.getAddress());


        personsEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    totalTxt.setText(0 + "");
                } else {
                    Integer value = Integer.parseInt(charSequence.toString());
                    totalTxt.setText((value * 500) + "");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String persons, startDate, endDate;
                persons = personsEdt.getText().toString();
                startDate = BookingStartDate.getText().toString();
                endDate = BookingEndDate.getText().toString();
                if (startDate.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter start date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (endDate.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter end date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (persons.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter persons", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (totalTxt.equals("0")) {
                    Toast.makeText(BookingActivity.this, "Please enter persons", Toast.LENGTH_SHORT).show();
                    return;
                }

                BookingListModel bookingListModel = new BookingListModel();
                bookingListModel.setStartDate(startDate);
                bookingListModel.setStatus("Pending");
                bookingListModel.setEndDate(endDate);
                bookingListModel.setRoom(room);
                bookingListModel.setTotal(totalTxt.getText().toString());
                bookingListModel.setPersons(persons);
                bookingListModel.setName(UserDao.getInstance().getUser().getName());

                showLoading();
                RoomDao.getInstance().addBooking(bookingListModel, new DataCallback<String>() {
                    @Override
                    public void onData(String data) {
                        dismiss();
                        Toast.makeText(BookingActivity.this, "House Booked", Toast.LENGTH_SHORT).show();
                        Dialog dialog = new Dialog(BookingActivity.this);
                        dialog.setContentView(R.layout.succesfully_booking_dialog);
                        dialog.show();
                        dialog.setOnDismissListener(dialogInterface -> {
                           Intent intent = new Intent(BookingActivity.this, HomeActivity.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           startActivity(intent);
                            HomeActivity.loadActivity(BookingActivity.this);

                        });
//                        HomeActivity.loadActivity(BookingActivity.this);
                    }

                    @Override
                    public void onError(String error) {
                        dismiss();
                        Toast.makeText(BookingActivity.this, error + "", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        BookingStartDate = findViewById(R.id.BookingStartDate);
        BookingStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, (datePicker, year, month, date) -> BookingStartDate.setText(date + "/" + (month + 1) + "/" + year), mYear, mMonth, mDate);
                datePickerDialog.show();
            }
        });

        BookingEndDate = findViewById(R.id.BookingEndDate);
        BookingEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, (datePicker, year, month, date) -> BookingEndDate.setText(date + "/" + (month + 1) + "/" + year), mYear, mMonth, mDate);
                datePickerDialog.show();
            }
        });

//        BookingBtn = findViewById(R.id._booking_successfully_button_btn);
//        BookingBtn.setOnClickListener(view -> {
//
//        });
    }
}