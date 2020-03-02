package com.example.kimschurch.Att;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    String date;
    String att_department;

    public ViewPagerAdapter(@NonNull FragmentManager fm, String att_department, String date) {
        super(fm);
        this.att_department = att_department;
         this.date = date;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return AttFragInsert.newInstance(att_department,date);
            case 1:
                return AttFragCheck.newInstance(att_department);

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    //상단의 탭레이아웃 인디케이터쪽에 텍스트를 선언
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        switch (position){
            case 0:
                return "출석체크";
            case 1:
                return "날짜별 출석체크";

            default:
                return null;
        }


    }
}
