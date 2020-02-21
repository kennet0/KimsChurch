package com.example.kimschurch.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.kimschurch.MemberCard.MemberCardActivity;
import com.example.kimschurch.Util.MemberDTO;
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
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.list_search);
        memberDTOList = new ArrayList<>();

        memberListAdapter = new MemberListAdapter(getApplicationContext(),memberDTOList);

        listView.setAdapter(memberListAdapter);

        //수정 RegisterActivity로 넘기는 값
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               Intent clickIntent = new Intent(SearchActivity.this, MemberCardActivity.class);

               clickIntent.putExtra("pnum",memberDTOList.get(i).getPnum());
               clickIntent.putExtra("name",memberDTOList.get(i).getName());
               clickIntent.putExtra("sex", memberDTOList.get(i).getSex());
               clickIntent.putExtra("position",memberDTOList.get(i).getPosition());
               clickIntent.putExtra("department",memberDTOList.get(i).getDepartment());
               clickIntent.putExtra("part",memberDTOList.get(i).getPart());
               clickIntent.putExtra("phone",memberDTOList.get(i).getPhone());
               clickIntent.putExtra("srbName",memberDTOList.get(i).getSrbName());
               clickIntent.putExtra("srbLeader",memberDTOList.get(i).getSrbLeader());
               clickIntent.putExtra("work",memberDTOList.get(i).getWork());
               clickIntent.putExtra("birthday",memberDTOList.get(i).getBirthday());
               clickIntent.putExtra("birthdayCal",memberDTOList.get(i).getBirthdayCal());
               clickIntent.putExtra("etc", memberDTOList.get(i).getEtc());

               startActivity(clickIntent);
            }
        });

        //MainActivity에서 인텐트로 넘어온 jsonArray
        final Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("result"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0 ;
            String pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal, etc;
            while (count < jsonArray.length()){
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
                etc = object.getString("etc");

               memberDTOList.add(new MemberDTO(pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal, etc));
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
