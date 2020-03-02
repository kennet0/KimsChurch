package com.example.kimschurch.MemberCard;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Register.RegisterActivity;
import com.example.kimschurch.Util.Calculator;
import com.example.kimschurch.Util.Etc;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.DTO.MemberDTO;

import org.json.JSONException;
import org.json.JSONObject;

public class FragMember extends Fragment {

    private View view;
    private MemberDTO memberDTO;

    public static FragMember newInstance(MemberDTO memberDTO) {
        FragMember fragMember = new FragMember();
        Bundle args =new Bundle();
        args.putSerializable("memberDTO",memberDTO);

        fragMember.setArguments(args);

        return fragMember;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.membercard_frag_info, container, false);

        TextView txtname = view.findViewById(R.id.card_name);
        TextView txtage = view.findViewById(R.id.card_age);
        TextView txtsex = view.findViewById(R.id.card_sex);
        final TextView txtbirthday =view.findViewById(R.id.card_birthday);
        final TextView txtbirthdayCal = view.findViewById(R.id.card_birthdayCal);
        TextView txtphone = view.findViewById(R.id.card_phone);
        TextView txtdepartment = view.findViewById(R.id.card_department);
        TextView txtsrbName = view.findViewById(R.id.card_srbName);
        TextView txtpart = view.findViewById(R.id.card_part);
        final TextView txtwork = view.findViewById(R.id.card_work);
        final TextView txtetc = view.findViewById(R.id.card_etc);
        Button btnUpdate = view.findViewById(R.id.card_btn_update);
        Button btnConfirm = view.findViewById(R.id.card_btn_confirm);
        Button btnBirthdayCal = view.findViewById(R.id.card_btn_convertCal);
        ImageView imageView = view.findViewById(R.id.card_imageview);

        memberDTO = (MemberDTO) getArguments().getSerializable("memberDTO");

        final String pnum = memberDTO.getPnum();
        final String name = memberDTO.getName();
        final String phone = memberDTO.getPhone();
        final String sex = memberDTO.getSex();
        final String position = memberDTO.getPosition();
        final String department = memberDTO.getDepartment();
        final String part = memberDTO.getPart();
        final String srbName = memberDTO.getSrbName();
        final String srbLeader = memberDTO.getSrbLeader();
        final String work = memberDTO.getWork();
        final String birthday = memberDTO.getBirthday();
        final String birthdayCal = memberDTO.getBirthdayCal();
        final String etc = memberDTO.getEtc();
        final String age = Calculator.calAge(memberDTO.getBirthday());
//        Log.e("memberDTO",memberDTO.getFamilyChild2());



        //전화
        txtphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Etc.phoneCall(view.getContext(),memberDTO);

            }
        });

        ImageProcess.LoadImage imageProcess = new ImageProcess.LoadImage(imageView);
        imageProcess.execute(pnum);

        txtname.setText(name);
        txtage.setText(age);
        txtsex.setText(sex);
        txtbirthday.setText(birthday);
        txtbirthdayCal.setText(birthdayCal);
        txtphone.setText(phone);
        txtdepartment.setText(department);
        txtsrbName.setText(srbName);
        txtpart.setText(part);
        txtwork.setText(work);
        txtetc.setText(etc);


        //달력변환
        btnBirthdayCal.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    if (txtbirthdayCal.getText().toString().equals("음")) {
                        String solarBirthday = Calculator.Solar2Lunar(birthday);
                        txtbirthday.setText(solarBirthday);
                        txtbirthdayCal.setText("양");

                    }else{
                        txtbirthday.setText(birthday);
                        txtbirthdayCal.setText(birthdayCal);
                    }
                }
            });

        //수정버튼
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);

                intent.putExtra("tag",2);
                intent.putExtra("memberDTO",memberDTO);
                startActivity(intent);
            }
        });
        //특이사항 및 섬김 업데이트
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.e("btnconfirm",jsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                String work = txtwork.getText().toString();
                String etc = txtetc.getText().toString();
                MemberCardEtcRequest memberCardEtcRequest = new MemberCardEtcRequest(pnum,work,etc,listener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(memberCardEtcRequest);
            }
        });

        return view;
    }


}
