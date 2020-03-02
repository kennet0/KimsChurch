package com.example.kimschurch.Att;

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

public class AttFragCheck extends Fragment {

    private View view;
    private CalendarView clnd_att;


    public static AttFragCheck newInstance(String att_department) {

        AttFragCheck attFragCheck = new AttFragCheck();
        Bundle args = new Bundle();
        args.putString("att_department",att_department);
        attFragCheck.setArguments(args);

        return attFragCheck;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.att_frag_check, container, false);
        final String att_department = getArguments().getString("att_department");

        clnd_att = view.findViewById(R.id.att_clnd);
        clnd_att.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               String date = year + "/" + (month+1) + "/" + dayOfMonth;
               if(att_department.equals("청년")) {
                   Intent intent = new Intent(getActivity(), YouthActivity.class);
                   intent.putExtra("date", date);
                   startActivity(intent);
               }else{
                   Intent intent = new Intent(getActivity(), SeniorActivity.class);
                   intent.putExtra("date", date);
                   startActivity(intent);
               }
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

