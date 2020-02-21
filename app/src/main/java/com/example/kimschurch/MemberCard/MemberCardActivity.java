package com.example.kimschurch.MemberCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
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
    private List<AttDTO> attDTOList;


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

        String intentPnum = getIntent().getStringExtra("pnum");
        String intentName = getIntent().getStringExtra("name");
        String intentPhone = getIntent().getStringExtra("phone");
        String intentSex = getIntent().getStringExtra("sex");
        String intentPosition = getIntent().getStringExtra("position");
        String intentDepartment = getIntent().getStringExtra("department");
        String intentPart = getIntent().getStringExtra("part");
        String intentSrbName =getIntent().getStringExtra("srbName");
        String intentSrbLeader = getIntent().getStringExtra("srbLeader");
        String intentWork = getIntent().getStringExtra("work");
        String intentBirthday = getIntent().getStringExtra("birthday");
        String intentBirthdayCal = getIntent().getStringExtra("birthdayCal");
        String intentEtc = getIntent().getStringExtra("etc");
        memberDTO = new MemberDTO(intentPnum,intentName,intentPhone,intentSex, intentPosition,intentDepartment,intentPart,intentSrbName,intentSrbLeader,intentWork,intentBirthday,intentBirthdayCal,intentEtc);

        Response.Listener<String> responseListener = (new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    attDTOList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    String att_pnum, att_date, att1,att2,att3,att4,att5;
                    while (count<jsonArray.length()){
                        JSONObject object = jsonArray.getJSONObject(count);
                        att_pnum = object.getString("pnum");
                        att_date = object.getString("att_date");
                        att1 = object.getString("att1");
                        att2 = object.getString("att2");
                        att3 = object.getString("att3");
                        att4 = object.getString("att4");
                        att5 = object.getString("att5");

                        attDTOList.add(new AttDTO(att_pnum, null, att_date, att1,att2,att3,att4,att5));
                        Log.e("attDTO", object.toString());
                        count++;
                    }
                }catch (JSONException e){
                    e.getStackTrace();
                }


                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

                FragMember fragMember = FragMember.newInstance(memberDTO);
                FragAttInfo fragAttInfo = FragAttInfo.newInstance();

                fragmentTransaction.add(R.id.frag_containerA, fragMember, null);
                fragmentTransaction.add(R.id.frag_containerB, fragAttInfo, null);

                fragmentTransaction.commit();
            }
        });
        MemberCardAttRequest memberCardAttRequest = new MemberCardAttRequest(intentPnum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MemberCardActivity.this);
        queue.add(memberCardAttRequest);




    }
}
