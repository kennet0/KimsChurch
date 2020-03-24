package com.example.kimschurch.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kimschurch.Att.SeniorActivity;
import com.example.kimschurch.Register.RegisterActivity;
import com.example.kimschurch.Search.SearchActivity;
import com.example.kimschurch.R;
import com.example.kimschurch.Att.YouthActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnSearch, btnYouth ,btnSenior,btnFindMember,btnInsertMember;
    EditText txtSearch;
    String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btnSearch);
        btnYouth = findViewById(R.id.btnYouth);
        btnSenior = findViewById(R.id.btnSenier);
        btnFindMember = findViewById(R.id.btnFindMember);
        btnInsertMember = findViewById(R.id.btnInsertMember);
        txtSearch = findViewById(R.id.txtSearch);

        //청년버튼
        btnYouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, YouthActivity.class);
              startActivity(intent);
            }
        });

        //장년버튼
        btnSenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeniorActivity.class);
                startActivity(intent);
            }
        });

        // 교인등록버튼
        btnInsertMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //검색버튼
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchName = txtSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("searchName",searchName);
                startActivity(intent);

            }
        });

        // 교인찾기 버튼
        btnFindMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
    }

}
