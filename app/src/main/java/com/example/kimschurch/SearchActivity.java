package com.example.kimschurch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

//    ArrayList<MemberDTO> memberDTOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);





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
