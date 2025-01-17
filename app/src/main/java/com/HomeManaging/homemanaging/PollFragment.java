package com.HomeManaging.homemanaging;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Model.PollResult;
import com.HomeManaging.homemanaging.Model.Pool;
import com.HomeManaging.homemanaging.dao.PoolDao;
import com.abidingtech.base.Utils;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserDao;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PollFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PollFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PollFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PollFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PollFragment newInstance(String param1, String param2) {
        PollFragment fragment = new PollFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView questionTxt;
    RadioGroup group;
    RadioButton answer1, answer2;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionTxt = view.findViewById(R.id.Poll_Question);
        group = view.findViewById(R.id.group);
        answer1 = view.findViewById(R.id.PollAnswer1);
        answer2 = view.findViewById(R.id.PollAnswer2);
        submit = view.findViewById(R.id.PollSubmitButton);
        ll = view.findViewById(R.id.ll);


        PoolDao.getInstance().getPool(new DataCallback<Pool>() {
            @Override
            public void onData(Pool data) {
                Log.e("onData: ", new Gson().toJson(data));
                if (data != null) {
                    if (data.getResults() != null) {
                        for (PollResult pollResult : data.getResults()) {
                            if (pollResult.getUserId().equals(UserDao.getInstance().getUserId())) {
                                ll.setVisibility(View.GONE);
                                return;
                            }
                        }



                    }
                    ll.setVisibility(View.VISIBLE);
                    questionTxt.setText(data.getQuestion());
                    answer1.setText(data.getOption1());
                    answer2.setText(data.getOption2());

                } else {
                    ll.setVisibility(View.GONE);
                }
                submit.setOnClickListener(view1 -> {
                    if (group.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getContext(), "Please select answer", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    PollResult pollResult = new PollResult();
                    RadioButton radioButton = group.findViewById(group.getCheckedRadioButtonId());
                    pollResult.setOption(radioButton.getText().toString());
                    pollResult.setUserId(UserDao.getInstance().getUserId());
                    data.addResult(pollResult);
                    Utils.loading(getContext()
                    );

                    PoolDao.getInstance().addPool(data, new DataCallback<String>() {
                        @Override
                        public void onData(String data) {
                            Toast.makeText(getContext(), "Result Submitted", Toast.LENGTH_SHORT).show();
                            Utils.dismiss();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
                            Utils.dismiss();

                        }
                    });

                });
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    LinearLayoutCompat ll;
}