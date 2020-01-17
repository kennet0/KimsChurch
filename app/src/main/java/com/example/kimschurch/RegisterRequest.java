package com.example.kimschurch;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class RegisterRequest extends StringRequest {

    final static private String URL = "http://172.30.1.50/Register.php";
    private Map<String, String>  parameters;

    public RegisterRequest(String name, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("name", name);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
