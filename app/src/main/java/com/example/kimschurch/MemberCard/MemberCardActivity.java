package com.example.kimschurch.MemberCard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.MemberDTO;

import java.util.ArrayList;

public class MemberCardActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private MemberDTO memberDTO;
    private ArrayList<MemberDTO> memberDTOList,famliyMemberDTOList;
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
        String intentPnum = memberDTO.getPnum();
        memberDTOList = (ArrayList<MemberDTO>) getIntent().getSerializableExtra("memberDTOList");
        famliyMemberDTOList = new ArrayList<>();
        stringArray = new ArrayList<>();

        int count = 0;
        while(count<memberDTOList.size()){
            if(memberDTO.getFamilyParent().equals(memberDTOList.get(count).getName())){
                stringArray.add("부모");
                famliyMemberDTOList.add(memberDTOList.get(count));
            }else if(memberDTO.getFamilyCouple().equals(memberDTOList.get(count).getName())){
                stringArray.add("부부");
                famliyMemberDTOList.add(memberDTOList.get(count));
            }else if(memberDTO.getFamilySibling().equals(memberDTOList.get(count).getName())){
                stringArray.add("형제");
                famliyMemberDTOList.add(memberDTOList.get(count));
            }else if(memberDTO.getFamilyChild().equals(memberDTOList.get(count).getName())){
                stringArray.add("자식");
                famliyMemberDTOList.add(memberDTOList.get(count));
            }else if(memberDTO.getFamilyEtc().equals(memberDTOList.get(count).getName())){
                stringArray.add("기타");
                famliyMemberDTOList.add(memberDTOList.get(count));
            }
            count++;
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
        if(memberDTOList!=null) {
            TextView textView = findViewById(R.id.memberCard_text);
            textView.setVisibility(View.VISIBLE);
            FragFamily fragFamily = FragFamily.newInstance(famliyMemberDTOList, stringArray);
            fragmentTransaction.add(R.id.frag_containerC, fragFamily, null);
        }

        fragmentTransaction.commit();
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
