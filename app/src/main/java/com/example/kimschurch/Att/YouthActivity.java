package com.example.kimschurch.Att;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kimschurch.Main.MainActivity;
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

        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), "청년", date);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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

