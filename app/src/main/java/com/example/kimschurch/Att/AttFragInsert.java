package com.example.kimschurch.Att;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.DTO.AttDTO;
import com.example.kimschurch.Util.Calculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttFragInsert extends Fragment {

    private View view;
    private ListView listView;
    private ArrayList<AttDTO> attDTOList;
    private AttListAdapter attListAdapter;
    private String weekCheck ="";


    public static AttFragInsert newInstance(String att_department, String date) {
        AttFragInsert attFragInsert = new AttFragInsert();
        Bundle args = new Bundle();
        args.putString("att_department",att_department);
        args.putString("date",date);
        attFragInsert.setArguments(args);

        return attFragInsert;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.att_frag_insert, container, false);

        final TextView att_date = view.findViewById(R.id.txt_att_date); //주별 날짜
        Button btn_att_reset = view.findViewById(R.id.btn_att_reset); //리셋버튼
        listView = view.findViewById(R.id.att_list);

        if(getArguments() != null){
            final String att_department = getArguments().getString("att_department");
            final String date = getArguments().getString("date");
            weekCheck = Calculator.calWeek(date);

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        attDTOList = new ArrayList<>();
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("response");
                        int count = 0;
                        String name, pnum, date, att1, att2, att3, att4, att5, att5a, att5b, att5c;
                        while (count < jsonArray.length()){
                            JSONObject object =jsonArray.getJSONObject(count);
                            pnum = object.getString("pnum");
                            name = object.getString("name");
                            date = object.getString("att_date");
                            att1 = object.getString("att1");
                            att2 = object.getString("att2");
                            att3 = object.getString("att3");
                            att4 = object.getString("att4");
                            att5 = object.getString("att5");
                            att5a = object.getString("att5a");
                            att5b = object.getString("att5b");
                            att5c = object.getString("att5c");

                            attDTOList.add(new AttDTO(1,pnum, name, date, att1, att2, att3, att4, att5, att5a,att5b,att5c,null));
                            count++;
                            Log.e("attInsertJsonArray", object.toString());
                        }
                        attListAdapter = new AttListAdapter(getContext(), attDTOList);
                        listView.setAdapter(attListAdapter);
                        att_date.setText(weekCheck);

                    }catch (JSONException e){
                        e.getStackTrace();
                    }
                }

            };

            AttRequest attRequest = new AttRequest(att_department, weekCheck,responseListener);
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(attRequest);



            btn_att_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AttResetRequest attResetRequest = new AttResetRequest(att_department ,weekCheck,null);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(attResetRequest);
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
        }



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
