package com.example.kimschurch.MemberCard;


import android.os.Bundle;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Util.AttVisitListAdapter;
import com.example.kimschurch.Util.Calculator;
import com.example.kimschurch.Util.ImageProcess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragAttInfo extends Fragment {

    private ListView listView;
    private AttVisitListAdapter attVisitListAdapter;
    private ArrayList<AttDTO> attDTOList;
    private String intentPnum;
    static int staticCount= 0;

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
        Button btnLess = view.findViewById(R.id.membercard_btnLess);
        Button btnSearch = view.findViewById(R.id.membercard_btnSearch);
        final EditText txtSearch = view.findViewById(R.id.membercard_txtSearch);

        intentPnum = getArguments().getString("intentPnum");
        listView = view.findViewById(R.id.membercard_frag_listview);
//

        final Response.Listener<String> responseListener = (new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    attDTOList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
//                    Log.e("jsonArray", jsonArray.toString());
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
                setListViewHeightBasedOnChildren(listView);
            }
        });

        MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, "2" ,"", responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(memberCardAttRequest);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticCount += 2;
                String count = String.valueOf(staticCount);
                MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, count, "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(memberCardAttRequest);
            }
        });
        btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticCount -= 2;
                String count = String.valueOf(staticCount);
                MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, count, "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(memberCardAttRequest);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = txtSearch.getText().toString();
                MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, "1000", search, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(memberCardAttRequest);
            }
        });


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
            totalHeight += (listItem.getMeasuredHeight()-350);
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
