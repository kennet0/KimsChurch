package com.example.kimschurch.Youth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kimschurch.R;

public class FragAttCheck extends Fragment {

    private View view;
    private CalendarView clnd_att;


    public static FragAttCheck newInstance() {

        FragAttCheck fragAttCheck = new FragAttCheck();

        return fragAttCheck;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.youth_att_frag_check, container, false);
        clnd_att = view.findViewById(R.id.clnd_att);
        clnd_att.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               String date = year + "/" + (month+1) + "/" + dayOfMonth;
               Intent intent = new Intent(getActivity(), YouthActivity.class);
               intent.putExtra("date", date);
               startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

