package com.example.kimschurch.MemberCard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimschurch.R;
import com.example.kimschurch.Register.RegisterActivity;
import com.example.kimschurch.Util.Calculator;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.Util.MemberDTO;

public class FragMember extends Fragment {

    private View view;

    public static FragMember newInstance(MemberDTO memberDTO) {
        FragMember fragMember = new FragMember();
        Bundle args =new Bundle();

        args.putString("pnum",memberDTO.getPnum());
        args.putString("name", memberDTO.getName());
        args.putString("phone", memberDTO.getPhone());
        args.putString("sex", memberDTO.getSex());
        args.putString("position",memberDTO.getPosition());
        args.putString("department",memberDTO.getDepartment());
        args.putString("part",memberDTO.getPart());
        args.putString("srbName",memberDTO.getSrbName());
        args.putString("srbLeader",memberDTO.getSrbLeader());
        args.putString("work",memberDTO.getWork());
        args.putString("birthday",memberDTO.getBirthday());
        args.putString("birthdayCal",memberDTO.getBirthdayCal());
        args.putString("etc",memberDTO.getEtc());

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
        TextView txtwork = view.findViewById(R.id.card_work);
        TextView txtetc = view.findViewById(R.id.card_etc);
        Button btnUpdate = view.findViewById(R.id.card_btn_update);
        Button btnBirthdayCal = view.findViewById(R.id.card_btn_convertCal);
        ImageView imageView = view.findViewById(R.id.card_imageview);

        final String pnum = getArguments().getString("pnum");
        final String name = getArguments().getString("name") ;
        final String phone = getArguments().getString("phone");
        final String sex = getArguments().getString("sex");
        final String position = getArguments().getString("position");
        final String department = getArguments().getString("department");
        final String part = getArguments().getString("part");
        final String srbName = getArguments().getString("srbName");
        final String srbLeader = getArguments().getString("srbLeader");
        final String work = getArguments().getString("work");
        final String birthday = getArguments().getString("birthday");
        final String birthdayCal = getArguments().getString("birthdayCal");
        final String etc = getArguments().getString("etc");
        String age = Calculator.calAge(getArguments().getString("birthday"));

        ImageProcess.LoadImage imageProcess = new ImageProcess.LoadImage(imageView);
        imageProcess.execute("http://112.186.116.16:6011/upload/" +
                pnum + ".png");

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
                if(birthdayCal.equals("음")){
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
                intent.putExtra("pnum",pnum);
                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("position",position);
                intent.putExtra("department",department);
                intent.putExtra("part",part);
                intent.putExtra("phone",phone);
                intent.putExtra("srbName",srbName);
                intent.putExtra("srbLeader",srbLeader);
                intent.putExtra("work",work);
                intent.putExtra("birthday",birthday);
                intent.putExtra("birthdayCal",birthdayCal);
                intent.putExtra("etc",etc);
                startActivity(intent);
            }
        });


        return view;
    }


}
