package com.HomeManaging.homemanaging;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abidingtech.base.dao.UserDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class SettingsFragment extends Fragment {

    TextView BookingHistory, BillHistory, Complaints, ComplaintStatus, Feedback, Help,Profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TextView logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = v.findViewById(R.id.Log_out);

        logout.setOnClickListener(view -> {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("u_" + UserDao.getInstance().getUserId());
            FirebaseMessaging.getInstance().unsubscribeFromTopic("u_" + "all");

            FirebaseAuth.getInstance().signOut();
            LoginActivity.loadActivity(getContext());
            getActivity().finishAffinity();
        });

        BookingHistory = v.findViewById(R.id.Booking_History);
        BookingHistory.setOnClickListener(view -> startActivity(new Intent(getContext(), BookingHistoryActivity.class)));

        BillHistory = v.findViewById(R.id.Bill_History);
        BillHistory.setOnClickListener(view -> startActivity(new Intent(getContext(), BillHistoryActivity.class)));

        Complaints = v.findViewById(R.id.Complain_settings);
        Complaints.setOnClickListener(view -> startActivity(new Intent(getContext(), ComplaintsActivity.class)));

        ComplaintStatus = v.findViewById(R.id.Complain_Status_settings);
        ComplaintStatus.setOnClickListener(view -> startActivity(new Intent(getContext(), ComplaintStatusActivity.class)));

        Feedback = v.findViewById(R.id.Feedback_settings);
        Feedback.setOnClickListener(view -> startActivity(new Intent(getContext(), FeedBackActivity.class)));

        Help = v.findViewById(R.id.Help_settings);
        Help.setOnClickListener(view -> startActivity(new Intent(getContext(), HelpAcivity.class)));

        Profile = v.findViewById(R.id.profile);
        Profile.setOnClickListener(view -> startActivity(new Intent(getContext(),ProfileActivity.class)));


        return v;
    }
}