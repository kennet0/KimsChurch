package com.example.kimschurch.MemberCard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.R;
import com.example.kimschurch.DTO.MemberDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MemberCardActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private MemberDTO memberDTO;
    private ArrayList<MemberDTO> familyMemberDTOList;
    private ArrayList<String> stringArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membercard);

        if(findViewById(R.id.frag_containerA) !=null){
            if(savedInstanceState!=null){
                return;
            }
        }
        if(findViewById(R.id.frag_containerB) !=null){
            if(savedInstanceState!=null){
                return;
            }
        }
        if(findViewById(R.id.frag_containerC) !=null){
            if(savedInstanceState!=null){
                return;
            }
        }

        fragmentManager = getSupportFragmentManager();

        memberDTO = (MemberDTO) getIntent().getSerializableExtra("memberDTO");
        final String intentPnum = memberDTO.getPnum();
//        Log.e("memberDTO",memberDTO.getFamilyChild2());

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    Log.e("familyArray",jsonArray.toString());
                    int count = 0 ;
                    familyMemberDTOList = new ArrayList<>();
                    stringArray = new ArrayList<>();
                    String pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
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
                        etc = object.getString("etc");
                        familyMemberDTOList.add(new MemberDTO(pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                                null,null,null,null,null,
                                null,null,null,null,null,
                                null,null,null,null,null,
                                null,null,null,null,null,
                                etc));
                        stringArray.add(object.getString("family"));
                        count++;
                        Log.e("familyObject", object.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

                if(memberDTO!=null) {
                    FragMember fragMember = FragMember.newInstance(memberDTO);
                    fragmentTransaction.add(R.id.frag_containerA, fragMember, null);
                }
                if(intentPnum!=null) {
                    FragAttInfo fragAttInfo = FragAttInfo.newInstance(intentPnum);
                    fragmentTransaction.add(R.id.frag_containerB, fragAttInfo, null);
                }
                if(familyMemberDTOList!=null) {
                    TextView textView = findViewById(R.id.memberCard_text);
                    textView.setVisibility(View.VISIBLE);
                    FragFamily fragFamily = FragFamily.newInstance(familyMemberDTOList, stringArray);
                    fragmentTransaction.add(R.id.frag_containerC, fragFamily, null);
                }

                fragmentTransaction.commit();
            }
        };

        SearchFamilyRequest searchFamilyRequest =
                new SearchFamilyRequest(
                        memberDTO.getFamilyParent(),memberDTO.getFamilyCouple(),memberDTO.getFamilySibling(), memberDTO.getFamilyChild(), memberDTO.getFamilyEtc(),
                        memberDTO.getFamilyParent2(),memberDTO.getFamilyCouple2(),memberDTO.getFamilySibling2(), memberDTO.getFamilyChild2(), memberDTO.getFamilyEtc2(),
                        memberDTO.getFamilyParent3(),memberDTO.getFamilyCouple3(),memberDTO.getFamilySibling3(), memberDTO.getFamilyChild3(), memberDTO.getFamilyEtc3(),
                        memberDTO.getFamilyParent4(),memberDTO.getFamilyCouple4(),memberDTO.getFamilySibling4(), memberDTO.getFamilyChild4(), memberDTO.getFamilyEtc4(),
                        listener);
//        Log.e("memberFamily",memberDTO.getFamilyChild2());
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(searchFamilyRequest);

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
