package com.example.kimschurch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnSearch, btnYouth ,btnSenior,btnFindMember,btnInsertMember;
    EditText txtSearch;

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


        btnInsertMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }
}
