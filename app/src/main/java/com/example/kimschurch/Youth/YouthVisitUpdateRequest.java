package com.example.kimschurch.Youth;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class YouthVisitUpdateRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/YouthVisitUpdate.php";
    private Map<String, String>  parameters;

    public YouthVisitUpdateRequest(String pnum, String name, String date, String att5a, String att5b, String att5c, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("name", name);
        parameters.put("date", date);
        parameters.put("att5a", att5a);
        parameters.put("att5b", att5b);
        parameters.put("att5c", att5c);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
