package com.example.kimschurch.MemberCard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimschurch.R;

public class FragMember extends Fragment {

    private View view;


    public static FragMember newInstance() {
        FragMember fragMember = new FragMember();
        return fragMember;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_member_info, container, false);

        return view;
    }
}
