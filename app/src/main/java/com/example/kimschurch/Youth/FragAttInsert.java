package com.example.kimschurch.Youth;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Util.AttListAdapter;
import com.example.kimschurch.Util.Calculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragAttInsert extends Fragment {

    private View view;
    private ListView listView;
    private List<AttDTO> attDTOList;
    private AttListAdapter attListAdapter;
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
        view = inflater.inflate(R.layout.youth_att_frag_insert, container, false);

        final TextView att_date = view.findViewById(R.id.att_date); //주별 날짜
        Button btn_att_reset = view.findViewById(R.id.btn_att_reset); //리셋버튼
        listView = view.findViewById(R.id.youth_list_att);

        if(getArguments() != null){
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
                        String name, pnum, date, att1, att2, att3, att4, att5;
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
                            attDTOList.add(new AttDTO(pnum, name, date, att1, att2, att3, att4, att5));
                            count++;
                        }
                        attListAdapter = new AttListAdapter(getContext(), attDTOList);
                        listView.setAdapter(attListAdapter);
                        att_date.setText(weekCheck);

                    }catch (JSONException e){
                        e.getStackTrace();
                    }
                }

            };

            YouthAttRequest youthAttRequest = new YouthAttRequest(weekCheck,responseListener);
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(youthAttRequest);

            btn_att_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    YouthResetRequest youthResetRequest = new YouthResetRequest(weekCheck,null);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(youthResetRequest);
                    Intent intent = new Intent(getActivity(),YouthActivity.class);
                    intent.putExtra("date",date);
                    startActivity(intent);
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
