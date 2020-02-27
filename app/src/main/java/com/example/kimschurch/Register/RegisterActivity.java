package com.example.kimschurch.Register;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.Main.MainActivity;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.MemberDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

   String image="", pnum = null;
   CircleImageView imageView;
   EditText txtName,txtPhone, txtSRBName, txtSRBLeader, txtWork, txtBirthday, txtEtc ;
   RadioGroup rgPosition, rgDepartment,rgPart,rgSex, rgBirthdayCal;
   RadioButton btnPosition, btnDepartment, btnPart, btnSex, btnBirthdayCal;
   Button btnImage, btnRegister, btnRemove;
   AutoCompleteTextView txtFamily_parent, txtFamily_couple,txtFamily_sibling,txtFamily_child,txtFamily_etc;
   ArrayList<String> familyList;


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
        txtFamily_parent =findViewById(R.id.txtFamily_parent);
        txtFamily_couple = findViewById(R.id.txtFamily_couple);
        txtFamily_sibling = findViewById(R.id.txtFamily_sibling);
        txtFamily_child = findViewById(R.id.txtFamily_child);
        txtFamily_etc = findViewById(R.id.txtFamily_etc);

        getFamilyList(getIntent());
        updateMember(getIntent());

        txtFamily_parent.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_couple.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_sibling.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_child.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_etc.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));

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
                String familyParent = txtFamily_parent.getText().toString();
                String familyCouple = txtFamily_couple.getText().toString();
                String familySibling = txtFamily_sibling.getText().toString();
                String familyChild = txtFamily_child.getText().toString();
                String familyEtc = txtFamily_etc.getText().toString();
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
                        new RegisterRequest(image, pnum, name, phone, sex,position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                                familyParent,familyCouple,familySibling,familyChild,familyEtc, etc, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
    public void getFamilyList(Intent intent) {
        if ((intent.getIntExtra("tag", 0) == 1)) {
            try {
                JSONObject jsonObject = new JSONObject(intent.getStringExtra("result"));
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                familyList = new ArrayList<String>();
                int count = 0;
                String name ;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    name = object.getString("name");
                    familyList.add(name);
                    count++;
                }
            }catch (JSONException e){
                e.getStackTrace();
            }

        }
    }


    //수정할 때
    public void updateMember(Intent intent){
        if((intent.getIntExtra("tag",0)==2)){
            MemberDTO memberDTO = (MemberDTO) intent.getSerializableExtra("memberDTO");

            pnum = memberDTO.getPnum();
            String intentName = memberDTO.getName();
            String intentPhone = memberDTO.getPhone();
            String intentSex = memberDTO.getSex();
            String intentPosition = memberDTO.getPosition();
            String intentDepartment = memberDTO.getDepartment();
            String intentPart = memberDTO.getPart();
            String intentSrbName = memberDTO.getSrbName();
            String intentSrbLeader = memberDTO.getSrbLeader();
            String intentWork = memberDTO.getWork();
            String intentBirthday = memberDTO.getBirthday();
            String intentBirthdayCal = memberDTO.getBirthdayCal();
            String intentFamilyParent = memberDTO.getFamilyParent();
            String intentFamilyCouple = memberDTO.getFamilyCouple();
            String intentFamilySibling = memberDTO.getFamilySibling();
            String intentFamilyChild = memberDTO.getFamilyChild();
            String intentFamilyEtc =memberDTO.getFamilyEtc();
            String intentEtc = memberDTO.getEtc();


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
            if (!(intentPhone.equals("null"))){txtPhone.setText(intentPhone);}
            if (!(intentSrbName.equals("null"))){txtSRBName.setText(intentSrbName);}
            if (!(intentSrbLeader.equals("null"))){txtSRBLeader.setText(intentSrbLeader);}
            txtBirthday.setText(intentBirthday);
            if (!(intentWork.equals("null"))){txtWork.setText(intentWork);}
            if (!(intentEtc.equals("null"))){ txtEtc.setText(intentEtc); }
            if (!(intentFamilyParent.equals("null"))){txtFamily_parent.setText(intentFamilyParent);}
            if (!(intentFamilyCouple.equals("null"))){txtFamily_couple.setText(intentFamilyCouple);}
            if (!(intentFamilySibling.equals("null"))){txtFamily_sibling.setText(intentFamilySibling);}
            if (!(intentFamilyChild.equals("null"))){txtFamily_child.setText(intentFamilyChild);}
            if (!(intentFamilyEtc.equals("null"))){txtFamily_etc.setText(intentFamilyEtc);}

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
