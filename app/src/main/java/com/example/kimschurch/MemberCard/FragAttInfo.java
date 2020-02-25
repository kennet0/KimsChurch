package com.example.kimschurch.MemberCard;

import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Util.AttListAdapter;
import com.example.kimschurch.Util.AttVisitListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragAttInfo extends Fragment {

    private ListView listView;
    private AttVisitListAdapter attVisitListAdapter;
    private ArrayList<AttDTO> attDTOList;
    private String intentPnum;

    public static FragAttInfo newInstance(String intentPnum){
        FragAttInfo fragAttInfo = new FragAttInfo();
        Bundle args = new Bundle();
        args.putString("intentPnum",intentPnum);
        fragAttInfo.setArguments(args);
        return fragAttInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.membercard_frag_att_info, container, false);

        Button btnMore = view.findViewById(R.id.membercard_btnMore);
        intentPnum = getArguments().getString("intentPnum");
        listView = view.findViewById(R.id.membercard_frag_listview);

        Log.e("intentPnum",intentPnum);
        final Response.Listener<String> responseListener = (new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    attDTOList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    String att_pnum, att_date, att5a, att5b, att5c, att_etc;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        att_pnum = object.getString("pnum");
                        att_date = object.getString("att_date");
                        att5a = object.getString("att5a");
                        att5b = object.getString("att5b");
                        att5c = object.getString("att5c");
                        att_etc = object.getString("att_etc");
                        attDTOList.add(new AttDTO(2, att_pnum, null, att_date, null, null, null, null, null, att5a, att5b, att5c, att_etc));
                        count++;
                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }

                attVisitListAdapter = new AttVisitListAdapter(getContext(), attDTOList);
                listView.setAdapter(attVisitListAdapter);
            }
        });

        MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(memberCardAttRequest);

//        btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(getActivity());
//                queue.add(memberCardAttRequest);
//            }
//        });



        return view;
    }
}
