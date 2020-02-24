package com.example.kimschurch.MemberCard;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kimschurch.R;
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Util.AttListAdapter;

import java.util.ArrayList;

public class FragAttInfo extends Fragment {

    private ListView listView;
    private AttListAdapter attListAdapter;

    public static FragAttInfo newInstance(ArrayList<AttDTO> attDTOList){
        FragAttInfo fragAttInfo = new FragAttInfo();
        Bundle args = new Bundle();
        args.putSerializable("attDTOList", attDTOList);
        fragAttInfo.setArguments(args);
        return fragAttInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.membercard_frag_att_info, container, false);

        listView = view.findViewById(R.id.att_info_listview);
        ArrayList<AttDTO> attDTOList = (ArrayList<AttDTO>) getArguments().get("attDTOList");
        Log.e("size", String.valueOf(attDTOList.size()));

        attListAdapter = new AttListAdapter(getContext(), attDTOList);
        listView.setAdapter(attListAdapter);



        return view;
    }
}
