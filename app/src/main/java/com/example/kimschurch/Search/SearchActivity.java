package com.example.kimschurch.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.MemberCard.MemberCardActivity;
import com.example.kimschurch.Util.MemberDTO;
import com.example.kimschurch.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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
               clickIntent.putExtra("memberDTO",memberDTOList.get(i));
               clickIntent.putExtra("memberDTOList", (Serializable) memberDTOList);

               startActivity(clickIntent);
            }
        });

        //MainActivity에서 인텐트로 넘어온 jsonArray
        final Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("result"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0 ;
            String pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                familyParent, familyCouple, familySibling, familyChild, familyEtc, etc;
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
                familyParent = object.getString("familyParent");
                familyCouple = object.getString("familyCouple");
                familySibling = object.getString("familySibling");
                familyChild = object.getString("familyChild");
                familyEtc = object.getString("familyEtc");
                etc = object.getString("etc");

               memberDTOList.add(new MemberDTO(pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal, familyParent,familyCouple,familySibling,familyChild,familyEtc,etc));
                count++;
//                Log.e("jsonObject", object.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
