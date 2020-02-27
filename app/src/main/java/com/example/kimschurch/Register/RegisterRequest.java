package com.example.kimschurch.Register;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/Register.php";
    private Map<String, String>  parameters;

    public RegisterRequest(String image, String pnum, String name, String phone, String sex, String position, String department, String part,
                           String srbName, String srbLeader, String work, String birthday, String birthdayCal, String familyParent, String familyCouple,
                           String familySibling, String familyChild, String familyEtc, String etc, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("image",image);
        parameters.put("name", name);
        parameters.put("phone", phone);
        parameters.put("sex", sex);
        parameters.put("position",  position);
        parameters.put("department", department);
        parameters.put("part", part);
        parameters.put("srbName", srbName);
        parameters.put("srbLeader", srbLeader);
        parameters.put("work", work);
        parameters.put("birthday", birthday);
        parameters.put("birthdayCal", birthdayCal);
        parameters.put("familyParent", familyParent);
        parameters.put("familyCouple", familyCouple);
        parameters.put("familySibling", familySibling);
        parameters.put("familyChild", familyChild);
        parameters.put("familyEtc", familyEtc);
        parameters.put("etc",etc);
        if(!(pnum==null)) {
            parameters.put("pnum", pnum);
        }
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
