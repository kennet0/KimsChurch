package com.example.kimschurch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

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
//        Button imgUpload = findViewById(R.id.imgMember);
//        imgUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent intent =new Intent();
//               intent.setAction(Intent.ACTION_GET_CONTENT);
//               intent.setType("image/-");
//               startActivityForResult(intent,gallary);
//            }
//        })
//

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
//                String familyMember = null;


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
                        new RegisterRequest(name, phone, position, department, part, srbName, srbLeader, work, birthday, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}
