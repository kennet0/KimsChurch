package com.example.kimschurch.MemberCard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kimschurch.R;
import com.example.kimschurch.DTO.MemberDTO;

import java.util.ArrayList;

public class FragFamily extends Fragment {

    private View view;
    private MemberDTO memberDTO;
    private ArrayList<MemberDTO> memberDTOList;
    private ArrayList<String> stringArray;
    private ListView listView;
    private FamilyListAdapter familyListAdapter;

    public static FragFamily newInstance(ArrayList<MemberDTO> memberDTOList, ArrayList<String> stringArray) {
        FragFamily fragMember = new FragFamily();
        Bundle args =new Bundle();
        if(memberDTOList!=null) {
            args.putSerializable("memberDTOList", memberDTOList);
            args.putStringArrayList("stringArray", stringArray);
        }
        fragMember.setArguments(args);

        return fragMember;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.membercard_frag_family, container, false);

        if(getArguments()!=null) {
            memberDTOList = (ArrayList<MemberDTO>) getArguments().getSerializable("memberDTOList");
            stringArray = getArguments().getStringArrayList("stringArray");

            familyListAdapter = new FamilyListAdapter(getActivity(), memberDTOList, stringArray);
            listView = view.findViewById(R.id.membercard_frag_family_listview);
            listView.setAdapter(familyListAdapter);
            setListViewHeightBasedOnChildren(listView);
        }

        return view;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += (listItem.getMeasuredHeight()-1000);
//            Log.e("totalHeight", String.valueOf(totalHeight));
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
