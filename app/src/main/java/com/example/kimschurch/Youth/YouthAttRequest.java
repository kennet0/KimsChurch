package com.example.kimschurch.Youth;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class YouthAttRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/Youth.php";
    private Map<String, String>  parameters;

    public YouthAttRequest(String date, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("date", date);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
