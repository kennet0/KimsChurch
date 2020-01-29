package com.example.kimschurch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnSearch, btnYouth ,btnSenior,btnFindMember,btnInsertMember;
    EditText txtSearch;
    String searchName, searchSRBName;

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

        // 교인등록버튼
        btnInsertMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //검색버튼

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BackgroundTask().execute();
//                final String search=txtSearch.getText().toString();
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                if(!search.isEmpty()) {
//                    if (search.contains("/")) {
//                        String[] arraySearch = search.split("/");
//                        searchName = arraySearch[0];
//                        searchSRBName = arraySearch[1];
//                        intent.putExtra("checkValue", 1);
//                        intent.putExtra("searchName", searchName);
//                        intent.putExtra("searchSRBName", searchSRBName);
//                        startActivity(intent);
//                    } else {
//                        intent.putExtra("checkValue" ,2);
//                        intent.putExtra("search", search);
//                        startActivity(intent);
//                    }
//                }else{
//                    Toast.makeText(MainActivity.this, "입력하세요", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }




    class BackgroundTask extends AsyncTask<Void,Void,String> {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://112.186.116.16:6011/Search.php";
        }


        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent =new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("userList", result);
            startActivity(intent);

        }
    }
}
