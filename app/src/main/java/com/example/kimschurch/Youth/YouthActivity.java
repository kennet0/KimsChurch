package com.example.kimschurch.Youth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.kimschurch.R;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class YouthActivity extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_att);

        if (getIntent().hasExtra("date")) {
            date = getIntent().getStringExtra("date");
        } else {
            Date currentDate = Calendar.getInstance().getTime();
            date = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA).format(currentDate);
        }

//        뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.youth_viewPager);
        TabLayout tabLayout = findViewById(R.id.youth_tabLayout);

        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), date);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}

