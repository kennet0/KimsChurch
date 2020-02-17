package com.example.kimschurch.Register;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RemoveRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/Remove.php";
    private Map<String, String>  parameters;

    public RemoveRequest(String pnum,  Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
