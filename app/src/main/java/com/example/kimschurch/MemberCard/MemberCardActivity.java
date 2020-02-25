package com.example.kimschurch.MemberCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Register.RegisterActivity;
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Util.MemberDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberCardActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private MemberDTO memberDTO;
    private ArrayList<AttDTO> attDTOList;


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
        fragmentManager = getSupportFragmentManager();
        final String intentPnum = getIntent().getStringExtra("pnum");
        final String intentName = getIntent().getStringExtra("name");
        final String intentPhone = getIntent().getStringExtra("phone");
        final String intentSex = getIntent().getStringExtra("sex");
        final String intentPosition = getIntent().getStringExtra("position");
        final String intentDepartment = getIntent().getStringExtra("department");
        final String intentPart = getIntent().getStringExtra("part");
        final String intentSrbName =getIntent().getStringExtra("srbName");
        final String intentSrbLeader = getIntent().getStringExtra("srbLeader");
        final String intentWork = getIntent().getStringExtra("work");
        final String intentBirthday = getIntent().getStringExtra("birthday");
        final String intentBirthdayCal = getIntent().getStringExtra("birthdayCal");
        final String intentEtc = getIntent().getStringExtra("etc");
        memberDTO = new MemberDTO(intentPnum,intentName,intentPhone,intentSex, intentPosition,intentDepartment,intentPart,intentSrbName,intentSrbLeader,intentWork,intentBirthday,intentBirthdayCal,intentEtc);

        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        FragMember fragMember = FragMember.newInstance(memberDTO);
        FragAttInfo fragAttInfo = FragAttInfo.newInstance(intentPnum);

        fragmentTransaction.add(R.id.frag_containerA, fragMember, null);
        fragmentTransaction.add(R.id.frag_containerB, fragAttInfo, null);
        fragmentTransaction.commit();
    }
}
