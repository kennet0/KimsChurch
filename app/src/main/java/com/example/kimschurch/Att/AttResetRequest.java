package com.example.kimschurch.Att;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kimschurch.Util.Etc;

import java.util.HashMap;
import java.util.Map;

public class AttResetRequest extends StringRequest {

    final static private String URL = Etc.URL+"/KimsChurch/AttReset.php";
    private Map<String, String>  parameters;

    public AttResetRequest(String att_department, String att_date, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("att_department",att_department);
        parameters.put("att_date", att_date);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
