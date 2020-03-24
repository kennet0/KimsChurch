package com.example.kimschurch.MemberCard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kimschurch.Util.Etc;

import java.util.HashMap;
import java.util.Map;

public class SearchFamilyRequest extends StringRequest {

    final static private String URL = Etc.URL+"/KimsChurch/SearchFamily.php";
    private Map<String, String>  parameters;

    public SearchFamilyRequest(String familyParent, String familyCouple, String familySibling, String familyChild, String familyEtc,
                               String familyParent2, String familyCouple2, String familySibling2, String familyChild2, String familyEtc2,
                               String familyParent3, String familyCouple3, String familySibling3, String familyChild3, String familyEtc3,
                               String familyParent4, String familyCouple4, String familySibling4, String familyChild4, String familyEtc4,
                               Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("familyParent", familyParent);
        parameters.put("familyCouple", familyCouple);
        parameters.put("familySibling", familySibling);
        parameters.put("familyChild", familyChild);
        parameters.put("familyEtc", familyEtc);
        parameters.put("familyParent2", familyParent2);
        parameters.put("familyCouple2", familyCouple2);
        parameters.put("familySibling2", familySibling2);
        parameters.put("familyChild2", familyChild2);
        parameters.put("familyEtc2", familyEtc2);
        parameters.put("familyParent3", familyParent3);
        parameters.put("familyCouple3", familyCouple3);
        parameters.put("familySibling3", familySibling3);
        parameters.put("familyChild3", familyChild3);
        parameters.put("familyEtc3", familyEtc3);
        parameters.put("familyParent4", familyParent4);
        parameters.put("familyCouple4", familyCouple4);
        parameters.put("familySibling4", familySibling4);
        parameters.put("familyChild4", familyChild4);
        parameters.put("familyEtc4", familyEtc4);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
