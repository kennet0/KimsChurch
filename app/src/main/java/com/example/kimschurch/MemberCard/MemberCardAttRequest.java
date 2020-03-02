package com.example.kimschurch.MemberCard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemberCardAttRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/KimsChurch/MemberCardAtt.php";
    private Map<String, String>  parameters;

    public MemberCardAttRequest(String pnum, String tag ,String count, String search, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("tag", tag);
        parameters.put("count", count);
        parameters.put("search", search);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
