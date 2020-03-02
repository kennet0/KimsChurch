package com.example.kimschurch.Att;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AttUpdateRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/KimsChurch/AttUpdate.php";
    private Map<String, String>  parameters;

    public AttUpdateRequest(String pnum, String name, String date, String att1, String att2, String att3, String att4, String att5, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("name", name);
        parameters.put("date", date);
        parameters.put("att1", att1);
        parameters.put("att2", att2);
        parameters.put("att3", att3);
        parameters.put("att4", att4);
        parameters.put("att5", att5);



       
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
