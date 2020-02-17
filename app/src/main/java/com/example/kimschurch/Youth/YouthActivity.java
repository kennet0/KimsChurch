package com.example.kimschurch.Youth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.YouthDTO;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class YouthActivity extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private String date;
    private List<YouthDTO> youthDTOList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth);

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

