package com.example.kimschurch.Search;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/KimsChurch/Search.php";
    private Map<String, String>  parameters;

    public SearchRequest(String pnum, String name, String sort,  Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("pnum", pnum);
        parameters.put("name", name);
        parameters.put("sort", sort);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
