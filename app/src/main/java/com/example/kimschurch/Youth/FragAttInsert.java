package com.example.kimschurch.Youth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.YouthDTO;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragAttInsert extends Fragment {

    private View view;
    private ListView listView;
    private List<YouthDTO> youthDTOList;
    private YouthListAdapter youthListAdapter;
    private String weekCheck ="";



    public static FragAttInsert newInstance(String date) {
        FragAttInsert fragAttInsert = new FragAttInsert();
        Bundle args = new Bundle();
        args.putString("date",date);
        fragAttInsert.setArguments(args);

        return fragAttInsert;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.youth_frag_att_insert, container, false);

        final TextView att_date = view.findViewById(R.id.att_date);
        listView = view.findViewById(R.id.youth_list_att);


        int thisWeek=0;
        if(getArguments() != null){
            String date = getArguments().getString("date");
            String[] splitDate = date.split("/");

            int year = Integer.parseInt(splitDate[0]);
            int month = Integer.parseInt(splitDate[1]);
            int day = Integer.parseInt(splitDate[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month-1,day);
            thisWeek = calendar.get(Calendar.WEEK_OF_MONTH);
            weekCheck = year + "." + month + " " + thisWeek + "주차";



            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        youthDTOList = new ArrayList<>();
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("response");

                        int count = 0;
                        String name, pnum;
                        while (count < jsonArray.length()){
                            JSONObject object =jsonArray.getJSONObject(count);
                            pnum = object.getString("pnum");
                            name = object.getString("name");
                            youthDTOList.add(new YouthDTO(pnum, name,false,false, false, false,false));
                            count++;

                        }
                        youthListAdapter = new YouthListAdapter(getContext(),youthDTOList);
                        listView.setAdapter(youthListAdapter);
                        att_date.setText(weekCheck);


                    }catch (JSONException e){
                        e.getStackTrace();
                    }
                }

            };

            YouthAttRequest youthAttRequest = new YouthAttRequest(weekCheck,responseListener);
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(youthAttRequest);
        }



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
