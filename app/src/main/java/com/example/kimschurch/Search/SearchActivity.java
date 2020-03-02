package com.example.kimschurch.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.MemberCard.MemberCardActivity;
import com.example.kimschurch.DTO.MemberDTO;
import com.example.kimschurch.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private MemberListAdapter memberListAdapter;
    private List<MemberDTO> memberDTOList;
    private String searchName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.list_search);
        memberDTOList = new ArrayList<>();
        memberListAdapter = new MemberListAdapter(getApplicationContext(), memberDTOList);
        TextView txtSort_name = findViewById(R.id.txtSort_name);
        TextView txtSort_srbName = findViewById(R.id.txtSort_srbName);
        TextView txtSort_position = findViewById(R.id.txtSort_position);
        TextView txtSort_department = findViewById(R.id.txtSort_department);
        TextView txtSort_part = findViewById(R.id.txtSort_part);



        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    Log.e("jsonObject",jsonObject.toString());
                    memberDTOList.clear();
                    int count = 0;
                    String pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                            familyParent, familyCouple, familySibling, familyChild, familyEtc,
                            familyParent2, familyCouple2, familySibling2, familyChild2, familyEtc2,
                            familyParent3, familyCouple3, familySibling3, familyChild3, familyEtc3,
                            familyParent4, familyCouple4, familySibling4, familyChild4, familyEtc4,
                            etc;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        pnum = object.getString("pnum");
                        name = object.getString("name");
                        phone = object.getString("phone");
                        sex = object.getString("sex");
                        position = object.getString("position");
                        department = object.getString("department");
                        part = object.getString("part");
                        srbName = object.getString("srbName");
                        srbLeader = object.getString("srbLeader");
                        work = object.getString("work");
                        birthday = object.getString("birthday");
                        birthdayCal = object.getString("birthdayCal");
                        familyParent = object.getString("familyParent");
                        familyCouple = object.getString("familyCouple");
                        familySibling = object.getString("familySibling");
                        familyChild = object.getString("familyChild");
                        familyEtc = object.getString("familyEtc");
                        familyParent2 = object.getString("familyParent2");
                        familyCouple2 = object.getString("familyCouple2");
                        familySibling2 = object.getString("familySibling2");
                        familyChild2 = object.getString("familyChild2");
                        familyEtc2 = object.getString("familyEtc2");
                        familyParent3 = object.getString("familyParent3");
                        familyCouple3 = object.getString("familyCouple3");
                        familySibling3 = object.getString("familySibling3");
                        familyChild3 = object.getString("familyChild3");
                        familyEtc3 = object.getString("familyEtc3");
                        familyParent4 = object.getString("familyParent4");
                        familyCouple4 = object.getString("familyCouple4");
                        familySibling4 = object.getString("familySibling4");
                        familyChild4 = object.getString("familyChild4");
                        familyEtc4 = object.getString("familyEtc4");
                        etc = object.getString("etc");

                        memberDTOList.add(new MemberDTO(pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                                familyParent, familyCouple, familySibling, familyChild, familyEtc,
                                familyParent2, familyCouple2, familySibling2, familyChild2, familyEtc2,
                                familyParent3, familyCouple3, familySibling3, familyChild3, familyEtc3,
                                familyParent4, familyCouple4, familySibling4, familyChild4, familyEtc4,
                                etc));
                        count++;
//                        Log.e("jsonObject", object.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listView.setAdapter(memberListAdapter);

                //수정 RegisterActivity로 넘기는 값
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        Intent clickIntent = new Intent(SearchActivity.this, MemberCardActivity.class);
                        clickIntent.putExtra("memberDTO", memberDTOList.get(i));
                        startActivity(clickIntent);
                    }
                });
            }
        };
        if(getIntent().hasExtra("searchName")) {
            searchName = getIntent().getStringExtra("searchName");
        }
        SearchRequest searchRequest = new SearchRequest(searchName,"name",listener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(searchRequest);

        txtSort_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRequest searchRequest = new SearchRequest(searchName,"name",listener);
                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                queue.add(searchRequest);
            }
        });

        txtSort_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRequest searchRequest = new SearchRequest(searchName,"position",listener);
                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                queue.add(searchRequest);
            }
        });

        txtSort_srbName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRequest searchRequest = new SearchRequest(searchName,"srbName",listener);
                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                queue.add(searchRequest);
            }
        });

        txtSort_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRequest searchRequest = new SearchRequest(searchName,"department",listener);
                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                queue.add(searchRequest);
            }
        });

        txtSort_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRequest searchRequest = new SearchRequest(searchName,"part",listener);
                RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                queue.add(searchRequest);
            }
        });


    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(R.id.menu_btnHome ==item.getItemId()){
            Intent intentHome = new Intent(this, MainActivity.class);
            intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intentHome);
            finish();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }

    }
}
