package com.example.kimschurch.Search;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {

    final static private String URL = "http://112.186.116.16:6011/Search.php";
    private Map<String, String>  parameters;

    public SearchRequest(String name,  Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("name", name);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
