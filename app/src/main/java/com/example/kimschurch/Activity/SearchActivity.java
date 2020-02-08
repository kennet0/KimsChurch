package com.example.kimschurch.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.kimschurch.Util.MemberDTO;
import com.example.kimschurch.Util.MemberListAdapter;
import com.example.kimschurch.R;

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

        final Intent intent = getIntent();


        memberListAdapter = new MemberListAdapter(getApplicationContext(),memberDTOList);
        listView.setAdapter(memberListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               Intent clickIntent = new Intent(SearchActivity.this, RegisterActivity.class);

               clickIntent.putExtra("pnum",memberDTOList.get(i).getPnum());
               clickIntent.putExtra("name",memberDTOList.get(i).getName());
               clickIntent.putExtra("position",memberDTOList.get(i).getPosition());
               clickIntent.putExtra("department",memberDTOList.get(i).getDepartment());
               clickIntent.putExtra("part",memberDTOList.get(i).getPart());
               clickIntent.putExtra("phone",memberDTOList.get(i).getPhone());
               clickIntent.putExtra("srbName",memberDTOList.get(i).getSrbName());
               clickIntent.putExtra("srbLeader",memberDTOList.get(i).getSrbLeader());
               clickIntent.putExtra("work",memberDTOList.get(i).getWork());
               clickIntent.putExtra("birthday",memberDTOList.get(i).getBirthday());
               clickIntent.putExtra("etc", memberDTOList.get(i).getEtc());

               startActivity(clickIntent);

            }
        });

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("MemberList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0 ;
            String pnum, name, phone, picture, position,  department, part, srbName, srbLeader, work, birthday, etc;
            while (count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                pnum = object.getString("pnum");
                name = object.getString("name");
                phone = object.getString("phone");
                picture = object.getString("picture");
                position = object.getString("position");
                department = object.getString("department");
                part = object.getString("part");
                srbName = object.getString("srbName");
                srbLeader = object.getString("srbLeader");
                work = object.getString("work");
                birthday = object.getString("birthday");
                etc = object.getString("etc");

               memberDTOList.add(new MemberDTO(pnum, name, phone, picture, position, department, part, srbName, srbLeader, work, birthday, etc));
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
