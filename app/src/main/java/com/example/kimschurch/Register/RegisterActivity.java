package com.example.kimschurch.Register;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

   String image="", pnum = null;
   CircleImageView imageView;
   EditText txtName,txtPhone, txtSRBName, txtSRBLeader, txtWork, txtBirthday, txtEtc ;
   RadioGroup rgPosition, rgDepartment,rgPart,rgSex, rgBirthdayCal;
   RadioButton btnPosition, btnDepartment, btnPart, btnSex, btnBirthdayCal;
   Button btnImage, btnRegister, btnRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        rgPosition = findViewById(R.id.rgPosition);
        rgDepartment = findViewById(R.id.rgDepartment);
        rgBirthdayCal = findViewById(R.id.rgBirthdayCal);
        rgSex = findViewById(R.id.rgSex);
        rgPart = findViewById(R.id.rgPart);
        btnPosition = findViewById(rgPosition.getCheckedRadioButtonId());
        btnDepartment = findViewById(rgDepartment.getCheckedRadioButtonId());
        btnPart = findViewById(rgPart.getCheckedRadioButtonId());
        btnSex = findViewById(rgSex.getCheckedRadioButtonId());
        btnBirthdayCal = findViewById(rgBirthdayCal.getCheckedRadioButtonId());
        txtSRBName = findViewById(R.id.txtSRBName);
        txtSRBLeader = findViewById(R.id.txtSRBLeader);
        txtWork = findViewById(R.id.txtWork);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtEtc = findViewById(R.id.txtEtc);
        btnImage = findViewById(R.id.btnImage);
        btnRegister = findViewById(R.id.btnRegister);
        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setVisibility(View.GONE);

        updateMember(getIntent());

        //이미지 등록 버튼
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }
        });

        //db등록 버튼
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String phone = txtPhone.getText().toString();
                String position = btnPosition.getText().toString();
                String department = btnDepartment.getText().toString();
                String birthdayCal = btnBirthdayCal.getText().toString();
                String sex = btnSex.getText().toString();
                String part = btnPart.getText().toString();
                String srbName =txtSRBName.getText().toString();
                String srbLeader = txtSRBLeader.getText().toString();
                String work = txtWork.getText().toString();
                String birthday = txtBirthday.getText().toString();
                String etc = txtEtc.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("교인등록성공").setPositiveButton("확인",null).create().show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "다시작성해주세요", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest =
                        new RegisterRequest(image, pnum, name, phone, sex,position, department, part, srbName, srbLeader, work, birthday, birthdayCal, etc, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                ImageProcess imageProcess = new ImageProcess();
                image = imageProcess.bitmapToString(bitmap);
                imageView = findViewById(R.id.imgView);
                imageView.setImageBitmap(bitmap);
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //수정할 때
    public void updateMember(Intent intent){
        if((intent.hasExtra("name"))){
            pnum = intent.getStringExtra("pnum");
            String intentName = intent.getStringExtra("name");
            String intentPhone = intent.getStringExtra("phone");
            String intentSex = intent.getStringExtra("sex");
            String intentPosition = intent.getStringExtra("position");
            String intentPart = intent.getStringExtra("part");
            String intentDepartment = intent.getStringExtra("department");
            String intentSrbName =intent.getStringExtra("srbName");
            String intentSrbLeader = intent.getStringExtra("srbLeader");
            String intentWork = intent.getStringExtra("work");
            String intentBirthday = intent.getStringExtra("birthday");
            String intentBirthdayCal = intent.getStringExtra("birthdayCal");
            String intentEtc = intent.getStringExtra("etc");

            imageView = findViewById(R.id.imgView);
            ImageProcess.LoadCircleImage imageProcess = new ImageProcess.LoadCircleImage(imageView);
            imageProcess.execute("http://112.186.116.16:6011/upload/" + pnum + ".png");

            txtName.setText(intentName);
            switch (intentPosition){
                case "성도":
                    rgPosition.check(R.id.rbLayman);
                    break;
                case "집사":
                    rgPosition.check(R.id.rbDecon);
                    break;
                case "권사":
                    rgPosition.check(R.id.rbSeniorDecon);
                    break;
                case "장로":
                    rgPosition.check(R.id.rbElder);
                    break;
                default:
                    rgPosition.check(R.id.rbLayman);
                    break;
            }
            switch (intentDepartment){
                case "청년":
                    rgDepartment.check(R.id.rbYouth);
                    break;
                case "장년":
                    rgDepartment.check(R.id.rbSenior);
                    break;
                default:
                    rgDepartment.check(R.id.rbYouth);
                    break;
            }
            switch (intentSex){
                case "남":
                    rgSex.check(R.id.rbMale);
                    break;
                case "여":
                    rgSex.check(R.id.rbFemale);
                    break;
                default:
                    rgSex.check(R.id.rbMale);
                    break;
            }
            switch (intentPart){
                case "임원":
                    rgPart.check(R.id.rbMinister);
                    break;
                case "목자":
                    rgPart.check(R.id.rbLeader);
                    break;
                case "예원":
                    rgPart.check(R.id.rbMember);
                    break;
                default:
                    rgPosition.check(R.id.rbMember);
                    break;
            }
            switch (intentBirthdayCal){
                case "양":
                    rgBirthdayCal.check(R.id.rbSunCalander);
                    break;
                case "음":
                    rgBirthdayCal.check(R.id.rbLunarCalander);
                    break;
                default:
                    rgBirthdayCal.check(R.id.rbSunCalander);
                    break;
            }
            txtPhone.setText(intentPhone);
            txtSRBName.setText(intentSrbName);
            txtSRBLeader.setText(intentSrbLeader);
            txtWork.setText(intentWork);
            txtBirthday.setText(intentBirthday);
            if (!(intentEtc.equals("null"))){
                txtEtc.setText(intentEtc);
            }
            btnRemove.setVisibility(View.VISIBLE);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                Log.e("response",jsonResponse.toString());
                                boolean success = jsonResponse.getBoolean("success");
                                if(success){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("삭제성공").setPositiveButton("확인",null).create().show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(RegisterActivity.this, "삭제실패", Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    };

                    RemoveRequest removeRequest = new RemoveRequest(pnum, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(removeRequest);
                    Log.e("pnum", pnum);
                }
            });
        }
    }


    public void radioCheck(View v){
        btnPosition = findViewById(rgPosition.getCheckedRadioButtonId());
        btnDepartment = findViewById(rgDepartment.getCheckedRadioButtonId());
        btnPart = findViewById(rgPart.getCheckedRadioButtonId());
        btnSex = findViewById(rgSex.getCheckedRadioButtonId());
        btnBirthdayCal = findViewById(rgBirthdayCal.getCheckedRadioButtonId());
    }
}
