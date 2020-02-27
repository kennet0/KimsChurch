package com.example.kimschurch.MemberCard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Register.RegisterActivity;
import com.example.kimschurch.Search.SearchRequest;
import com.example.kimschurch.Util.Calculator;
import com.example.kimschurch.Util.FamilyListAdapter;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.Util.MemberDTO;

import org.json.JSONException;
import org.json.JSONObject;

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
            Log.e("totalHeight", String.valueOf(totalHeight));
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
