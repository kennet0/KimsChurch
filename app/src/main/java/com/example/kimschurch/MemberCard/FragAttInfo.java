package com.example.kimschurch.MemberCard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kimschurch.R;

public class FragAttInfo extends Fragment {





    public static FragAttInfo newInstance(){
        FragAttInfo fragAttInfo = new FragAttInfo();
        return fragAttInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_att_info, container, false);

        return view;
    }
}
