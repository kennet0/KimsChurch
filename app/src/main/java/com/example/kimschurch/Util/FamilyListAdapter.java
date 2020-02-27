package com.example.kimschurch.Util;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.kimschurch.R;

import java.util.ArrayList;

public class FamilyListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MemberDTO> memberDTOList;
    private ArrayList<String> stringArray;

    public FamilyListAdapter(Context context, ArrayList<MemberDTO> memberDTOList, ArrayList<String> stringArray) {
        this.context = context;
        this.memberDTOList = memberDTOList;
        this.stringArray = stringArray;
    }

    @Override
    public int getCount() {
        return memberDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return memberDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View family_list = View.inflate(context, R.layout.membercard_frag_info, null);

        TextView txtname = family_list.findViewById(R.id.card_name);
        TextView txtage = family_list.findViewById(R.id.card_age);
        TextView txtsex = family_list.findViewById(R.id.card_sex);
        final TextView txtbirthday =family_list.findViewById(R.id.card_birthday);
        final TextView txtbirthdayCal = family_list.findViewById(R.id.card_birthdayCal);
        TextView txtphone = family_list.findViewById(R.id.card_phone);
        TextView txtdepartment = family_list.findViewById(R.id.card_department);
        TextView txtsrbName = family_list.findViewById(R.id.card_srbName);
        TextView txtpart = family_list.findViewById(R.id.card_part);
        final TextView txtwork = family_list.findViewById(R.id.card_work);
        final TextView txtetc = family_list.findViewById(R.id.card_etc);
        TextView txtFamily =family_list.findViewById(R.id.card_family);
        TextView txt = family_list.findViewById(R.id.textView);
        Button btnUpdate = family_list.findViewById(R.id.card_btn_update);
        Button btnConfirm = family_list.findViewById(R.id.card_btn_confirm);
        Button btnBirthdayCal = family_list.findViewById(R.id.card_btn_convertCal);
        ImageView imageView = family_list.findViewById(R.id.card_imageview);


        final String pnum = memberDTOList.get(i).getPnum();
        final String name = memberDTOList.get(i).getName();
        final String phone = memberDTOList.get(i).getPhone();
        final String sex = memberDTOList.get(i).getSex();
        final String position = memberDTOList.get(i).getPosition();
        final String department = memberDTOList.get(i).getDepartment();
        final String part = memberDTOList.get(i).getPart();
        final String srbName = memberDTOList.get(i).getSrbName();
        final String srbLeader = memberDTOList.get(i).getSrbLeader();
        final String work = memberDTOList.get(i).getWork();
        final String birthday = memberDTOList.get(i).getBirthday();
        final String birthdayCal = memberDTOList.get(i).getBirthdayCal();
        final String familyFather = memberDTOList.get(i).getFamilyParent();
        final String familyMother = memberDTOList.get(i).getFamilyCouple();
        final String familySibling = memberDTOList.get(i).getFamilySibling();
        final String familyChild = memberDTOList.get(i).getFamilyChild();
        final String familyEtc =memberDTOList.get(i).getFamilyEtc();
        final String etc = memberDTOList.get(i).getEtc();
        final String age = Calculator.calAge(memberDTOList.get(i).getBirthday());


        ImageProcess.LoadImage imageProcess = new ImageProcess.LoadImage(imageView);
        imageProcess.execute("http://112.186.116.16:6011/upload/" +
                pnum + ".png");


        txtFamily.setVisibility(View.VISIBLE);
        txtFamily.setText(stringArray.get(i));
        txtname.setText(name);
        txtage.setText(age);
        txtsex.setText(sex);
        txtbirthday.setText(birthday);
        txtbirthdayCal.setText(birthdayCal);
        txtphone.setText(phone);
        txtdepartment.setText(department);
        txtsrbName.setText(srbName);
        txtpart.setText(part);
        txtwork.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
        txtetc.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);


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


        return family_list;
    }
}
