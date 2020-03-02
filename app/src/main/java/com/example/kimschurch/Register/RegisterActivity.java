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
import com.example.kimschurch.Search.SearchRequest;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.R;
import com.example.kimschurch.DTO.MemberDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

   String pnum = null;
   Bitmap bmImage=null;

   CircleImageView imageView;
   EditText txtName,txtPhone, txtSRBName, txtSRBLeader, txtWork, txtBirthday, txtEtc ;
   RadioGroup rgPosition, rgDepartment,rgPart,rgSex, rgBirthdayCal;
   RadioButton btnPosition, btnDepartment, btnPart, btnSex, btnBirthdayCal;
   Button btnImage,btnImageRotate, btnRegister, btnRemove;
   AutoCompleteTextView txtFamily_parent, txtFamily_couple,txtFamily_sibling,txtFamily_child,txtFamily_etc,
           txtFamily_parent2, txtFamily_couple2,txtFamily_sibling2,txtFamily_child2,txtFamily_etc2,
           txtFamily_parent3, txtFamily_couple3,txtFamily_sibling3,txtFamily_child3,txtFamily_etc3,
           txtFamily_parent4, txtFamily_couple4,txtFamily_sibling4,txtFamily_child4,txtFamily_etc4;
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
        btnImageRotate = findViewById(R.id.btnImageRotate);
        btnRegister = findViewById(R.id.btnRegister);
        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setVisibility(View.GONE);
        txtFamily_parent =findViewById(R.id.txtFamily_parent);
        txtFamily_couple = findViewById(R.id.txtFamily_couple);
        txtFamily_sibling = findViewById(R.id.txtFamily_sibling);
        txtFamily_child = findViewById(R.id.txtFamily_child);
        txtFamily_etc = findViewById(R.id.txtFamily_etc);
        txtFamily_parent2 =findViewById(R.id.txtFamily_parent2);
        txtFamily_couple2 = findViewById(R.id.txtFamily_couple2);
        txtFamily_sibling2 = findViewById(R.id.txtFamily_sibling2);
        txtFamily_child2 = findViewById(R.id.txtFamily_child2);
        txtFamily_etc2 = findViewById(R.id.txtFamily_etc2);
        txtFamily_parent3 =findViewById(R.id.txtFamily_parent3);
        txtFamily_couple3 = findViewById(R.id.txtFamily_couple3);
        txtFamily_sibling3 = findViewById(R.id.txtFamily_sibling3);
        txtFamily_child3 = findViewById(R.id.txtFamily_child3);
        txtFamily_etc3 = findViewById(R.id.txtFamily_etc3);
        txtFamily_parent4 =findViewById(R.id.txtFamily_parent4);
        txtFamily_couple4 = findViewById(R.id.txtFamily_couple4);
        txtFamily_sibling4 = findViewById(R.id.txtFamily_sibling4);
        txtFamily_child4 = findViewById(R.id.txtFamily_child4);
        txtFamily_etc4 = findViewById(R.id.txtFamily_etc4);

        getFamilyList();
        updateMember(getIntent());

        txtFamily_parent.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_couple.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_sibling.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_child.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_etc.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_parent2.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_couple2.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_sibling2.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_child2.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_etc2.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_parent3.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_couple3.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_sibling3.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_child3.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_etc3.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_parent4.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_couple4.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_sibling4.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_child4.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));
        txtFamily_etc4.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, familyList));

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

        //이미지 회전 버튼
        btnImageRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mDegree= 90;
                bmImage = ImageProcess.rotateImage(bmImage,mDegree);
                imageView = findViewById(R.id.imgView);
                imageView.setImageBitmap(bmImage);
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
                String familyParent2 = txtFamily_parent2.getText().toString();
                String familyCouple2 = txtFamily_couple2.getText().toString();
                String familySibling2 = txtFamily_sibling2.getText().toString();
                String familyChild2 = txtFamily_child2.getText().toString();
                String familyEtc2 = txtFamily_etc2.getText().toString();
                String familyParent3 = txtFamily_parent3.getText().toString();
                String familyCouple3 = txtFamily_couple3.getText().toString();
                String familySibling3 = txtFamily_sibling3.getText().toString();
                String familyChild3 = txtFamily_child3.getText().toString();
                String familyEtc3 = txtFamily_etc3.getText().toString();
                String familyParent4 = txtFamily_parent4.getText().toString();
                String familyCouple4 = txtFamily_couple4.getText().toString();
                String familySibling4 = txtFamily_sibling4.getText().toString();
                String familyChild4 = txtFamily_child4.getText().toString();
                String familyEtc4 = txtFamily_etc4.getText().toString();
                String etc = txtEtc.getText().toString();
                String image = "";

                if(bmImage!=null) {
                    bmImage = ImageProcess.bitmapSize(bmImage, 100);
                    image = ImageProcess.bitmapToString(bmImage);
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
//                            Log.e("jsonResponse",jsonResponse.toString());
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
                                familyParent,familyCouple,familySibling,familyChild,familyEtc,
                                familyParent2,familyCouple2,familySibling2,familyChild2,familyEtc2,
                                familyParent3,familyCouple3,familySibling3,familyChild3,familyEtc3,
                                familyParent4,familyCouple4,familySibling4,familyChild4,familyEtc4,
                                etc, responseListener);
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
                bmImage = bitmap;
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
    public void getFamilyList() {
        familyList = new ArrayList<>();

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                Log.e("jsonArray",jsonArray.toString());
                    int count = 0;
                    String name;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        name = object.getString("name");
                        familyList.add(name);
                        count++;
                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        SearchRequest searchRequest = new SearchRequest("","","name",listener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(searchRequest);
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
            String intentFamilyParent2 = memberDTO.getFamilyParent2();
            String intentFamilyCouple2 = memberDTO.getFamilyCouple2();
            String intentFamilySibling2 = memberDTO.getFamilySibling2();
            String intentFamilyChild2 = memberDTO.getFamilyChild2();
            String intentFamilyEtc2 =memberDTO.getFamilyEtc2();
            String intentFamilyParent3 = memberDTO.getFamilyParent3();
            String intentFamilyCouple3 = memberDTO.getFamilyCouple3();
            String intentFamilySibling3 = memberDTO.getFamilySibling3();
            String intentFamilyChild3 = memberDTO.getFamilyChild3();
            String intentFamilyEtc3 =memberDTO.getFamilyEtc3();
            String intentFamilyParent4 = memberDTO.getFamilyParent4();
            String intentFamilyCouple4 = memberDTO.getFamilyCouple4();
            String intentFamilySibling4 = memberDTO.getFamilySibling4();
            String intentFamilyChild4 = memberDTO.getFamilyChild4();
            String intentFamilyEtc4 =memberDTO.getFamilyEtc4();
            String intentEtc = memberDTO.getEtc();


            imageView = findViewById(R.id.imgView);
            ImageProcess.LoadCircleImage imageProcess = new ImageProcess.LoadCircleImage(imageView);
            try {
                bmImage = imageProcess.execute(pnum).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
//                default:
//                    rgPosition.check(R.id.rbLayman);
//                    break;
            }
            switch (intentDepartment){
                case "청년":
                    rgDepartment.check(R.id.rbYouth);
                    break;
                case "장년":
                    rgDepartment.check(R.id.rbSenior);
                    break;
//                default:
//                    rgDepartment.check(R.id.rbYouth);
//                    break;
            }
            switch (intentSex){
                case "남":
                    rgSex.check(R.id.rbMale);
                    break;
                case "여":
                    rgSex.check(R.id.rbFemale);
                    break;
//                default:
//                    rgSex.check(R.id.rbMale);
//                    break;
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
//                default:
//                    rgPosition.check(R.id.rbMember);
//                    break;
            }
            switch (intentBirthdayCal){
                case "양":
                    rgBirthdayCal.check(R.id.rbSunCalander);
                    break;
                case "음":
                    rgBirthdayCal.check(R.id.rbLunarCalander);
                    break;
//                default:
//                    rgBirthdayCal.check(R.id.rbSunCalander);
//                    break;
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
            if (!(intentFamilyParent2.equals("null"))){txtFamily_parent2.setText(intentFamilyParent2);}
            if (!(intentFamilyCouple2.equals("null"))){txtFamily_couple2.setText(intentFamilyCouple2);}
            if (!(intentFamilySibling2.equals("null"))){txtFamily_sibling2.setText(intentFamilySibling2);}
            if (!(intentFamilyChild2.equals("null"))){txtFamily_child2.setText(intentFamilyChild2);}
            if (!(intentFamilyEtc2.equals("null"))){txtFamily_etc2.setText(intentFamilyEtc2);}
            if (!(intentFamilyParent3.equals("null"))){txtFamily_parent3.setText(intentFamilyParent3);}
            if (!(intentFamilyCouple3.equals("null"))){txtFamily_couple3.setText(intentFamilyCouple3);}
            if (!(intentFamilySibling3.equals("null"))){txtFamily_sibling3.setText(intentFamilySibling3);}
            if (!(intentFamilyChild3.equals("null"))){txtFamily_child3.setText(intentFamilyChild3);}
            if (!(intentFamilyEtc3.equals("null"))){txtFamily_etc3.setText(intentFamilyEtc3);}
            if (!(intentFamilyParent4.equals("null"))){txtFamily_parent4.setText(intentFamilyParent4);}
            if (!(intentFamilyCouple4.equals("null"))){txtFamily_couple4.setText(intentFamilyCouple4);}
            if (!(intentFamilySibling4.equals("null"))){txtFamily_sibling4.setText(intentFamilySibling4);}
            if (!(intentFamilyChild4.equals("null"))){txtFamily_child4.setText(intentFamilyChild4);}
            if (!(intentFamilyEtc4.equals("null"))){txtFamily_etc4.setText(intentFamilyEtc4);}

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
//                    Log.e("pnum", pnum);
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
