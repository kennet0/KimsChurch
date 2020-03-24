package com.example.kimschurch.MemberCard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kimschurch.Util.Etc;

import java.util.HashMap;
import java.util.Map;

public class MemberCardEtcRequest extends StringRequest {

    final static private String URL = Etc.URL+"/KimsChurch/MemberCardEtc.php";
    private Map<String, String>  parameters;

    public MemberCardEtcRequest(String pnum, String work, String etc, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("work", work);
        parameters.put("etc", etc);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
