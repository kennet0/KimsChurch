package com.example.kimschurch;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class RegisterRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/Register.php";
    private Map<String, String>  parameters;

    public RegisterRequest(String image, String name, String phone, String position, String department, String part, String srbName, String srbLeader,
                           String work, String birthday, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("image",image);
        parameters.put("name", name);
        parameters.put("phone", phone);
        parameters.put("position", position);
        parameters.put("department", department);
        parameters.put("part", part);
        parameters.put("srbName", srbName);
        parameters.put("srbLeader", srbLeader);
        parameters.put("work", work);
        parameters.put("birthday", birthday);
//        parameters.put("familyMemeber", familyMember);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
