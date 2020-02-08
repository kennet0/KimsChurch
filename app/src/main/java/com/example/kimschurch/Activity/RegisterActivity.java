package com.example.kimschurch.Activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.R;
import com.example.kimschurch.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

   String image="";
   CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText txtName = findViewById(R.id.txtName);
        final EditText txtPhone = findViewById(R.id.txtPhone);
        RadioGroup rgPosition = findViewById(R.id.rgPosition);
        RadioGroup rgDepartment = findViewById(R.id.rgDepartment);
        RadioGroup rgPart = findViewById(R.id.rgPart);
        final RadioButton btnPosition = findViewById(rgPosition.getCheckedRadioButtonId());
        final RadioButton btnDepartment = findViewById(rgDepartment.getCheckedRadioButtonId());
        final RadioButton btnPart = findViewById(rgPart.getCheckedRadioButtonId());
        final EditText txtSRBName = findViewById(R.id.txtSRBName);
        final EditText txtSRBLeader = findViewById(R.id.txtSRBLeader);
        final EditText txtWork = findViewById(R.id.txtWork);
        final EditText txtBirthday = findViewById(R.id.txtBirthday);
        final EditText txtEtc = findViewById(R.id.txtEtc);

        String intentPnum = null;

        if((getIntent().hasExtra("name"))){
             intentPnum = getIntent().getStringExtra("pnum");
            String intentName = getIntent().getStringExtra("name");
            String intentPhone = getIntent().getStringExtra("phone");
            String intentPosition = getIntent().getStringExtra("position");
            String intentPart = getIntent().getStringExtra("part");
            String intentDepartment = getIntent().getStringExtra("department");
            String intentSrbName =getIntent().getStringExtra("srbName");
            String intentSrbLeader = getIntent().getStringExtra("srbLeader");
            String intentWork = getIntent().getStringExtra("work");
            String intentBirthday = getIntent().getStringExtra("birthday");
            String intentEtc = getIntent().getStringExtra("etc");
            Log.e("check",intentSrbLeader + intentBirthday);

            imageView = findViewById(R.id.imgView);
            ImageProcess.LoadImage imageProcess = new ImageProcess.LoadImage(imageView);
            imageProcess.execute("http://112.186.116.16:6011/upload/" + intentPnum + ".png");

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
            txtPhone.setText(intentPhone);
            txtSRBName.setText(intentSrbName);
            txtSRBLeader.setText(intentSrbLeader);
            txtWork.setText(intentWork);
            txtBirthday.setText(intentBirthday);
            if (!(intentEtc.equals("null"))){
                txtEtc.setText(intentEtc);
            }

        }
        final String pnum = intentPnum;

        Button btnImage = findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String phone = txtPhone.getText().toString();
                String position = btnPosition.getText().toString();
                String department = btnDepartment.getText().toString();
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
                            Log.e("response",jsonResponse.toString());
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
                        new RegisterRequest(image, pnum, name, phone, position, department, part, srbName, srbLeader, work, birthday,etc, responseListener);
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
}
