package com.example.kimschurch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private MemberListAdapter memberListAdapter;
    private List<MemberDTO> memberDTOList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        listView = findViewById(R.id.list_view);
        memberDTOList = new ArrayList<>();

        Intent intent = getIntent();

        memberListAdapter = new MemberListAdapter(getApplicationContext(),memberDTOList);
        listView.setAdapter(memberListAdapter);

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("MemberList"));
            Log.e("jsonObject", jsonObject.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0 ;
            String name, srbName, position, department, part, phone;
            while (count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                name = object.getString("name");
                srbName = object.getString("srbName");
                position = object.getString("position");
                department = object.getString("department");
                part = object.getString("part");
                phone = object.getString("phone");
                memberDTOList.add(new MemberDTO(name,phone,position,department,part,srbName));
                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

/////////////////////////////////////////////////////////////////////////////
//        if (getIntent().getIntExtra("checkValue",1)==1) {

//            Response.Listener<String> responseListener = new Response.Listener<String>(){
//
//                @Override
//                public void onResponse(String response) {
//                    try{
//                        JSONArray jsonArray = new JSONArray(response);
//                        Log.e("before","for");
//                        for (int i=0;i<jsonArray.length();i++) {
//                            MemberDTO memberDTO = new MemberDTO();
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                            memberDTO.setName(jsonObject.getString("name"));
//                            memberDTO.setPhone(jsonObject.getString("phone"));
//                            memberDTO.setPosition(jsonObject.getString("position"));
//                            memberDTO.setDepartment(jsonObject.getString("department"));
//
//                            memberDTOS.add(memberDTO);
//                        }
//
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            };

//            SearchRequest searchRequest = new SearchRequest(getIntent().getStringExtra("searchName"),
//                                                        getIntent().getStringExtra("searchSRBName"), responseListener);
//            RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
//            queue.add(searchRequest);
//
//            RecyclerView view = findViewById(R.id.search_recyclerview);
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//
//            MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(memberDTOS);
//
//            view.setLayoutManager(layoutManager);
//            view.setAdapter(myRecyclerViewAdapter);

//        }

    }
}
