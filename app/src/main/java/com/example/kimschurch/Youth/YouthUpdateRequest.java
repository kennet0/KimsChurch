package com.example.kimschurch.Youth;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class YouthUpdateRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/YouthUpdate.php";
    private Map<String, String>  parameters;

    public YouthUpdateRequest(String pnum, String name, String date, int att1, int att2, int att_srb, int att_train, int att_visit, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("name", name);
        parameters.put("date", date);
       
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
