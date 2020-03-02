package com.example.kimschurch.Att;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.DTO.MemberDTO;
import com.example.kimschurch.MemberCard.MemberCardActivity;
import com.example.kimschurch.R;
import com.example.kimschurch.DTO.AttDTO;
import com.example.kimschurch.Search.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AttDTO> attDTOList;

    public AttListAdapter(Context context, ArrayList<AttDTO> attDTOList) {
        this.context = context;
        this.attDTOList = attDTOList;
    }

    @Override
    public int getCount() {
        return attDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return attDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View youth_list = View.inflate(context, R.layout.att_list, null);

        TextView att_name = youth_list.findViewById(R.id.att_name);
        final CheckBox chb_att1 = youth_list.findViewById(R.id.chb_att1);
        final CheckBox chb_att2 = youth_list.findViewById(R.id.chb_att2);
        final CheckBox chb_att3 = youth_list.findViewById(R.id.chb_att3);
        final CheckBox chb_att4 = youth_list.findViewById(R.id.chb_att4);
        final CheckBox chb_att5 = youth_list.findViewById(R.id.chb_att5);

        att_name.setText(attDTOList.get(i).getName());

        if(attDTOList.get(i).getAtt1().equals("1")) { chb_att1.setChecked(false);}
        else{chb_att1.setChecked(true);}

        if(attDTOList.get(i).getAtt2().equals("1")) { chb_att2.setChecked(false);}
        else{chb_att2.setChecked(true);}

        if(attDTOList.get(i).getAtt3().equals("1")) { chb_att3.setChecked(false);}
        else{chb_att3.setChecked(true);}

        if(attDTOList.get(i).getAtt4().equals("1")){ chb_att4.setChecked(false);}
        else {chb_att4.setChecked(true);}

        if(attDTOList.get(i).getAtt5a().equals("1") && attDTOList.get(i).getAtt5b().equals("1") && attDTOList.get(i).getAtt5c().equals("1")) { chb_att5.setChecked(false);}
        else{chb_att5.setChecked(true);}

        youth_list.setTag(attDTOList.get(i).getName());

        //DB 연동


        att_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MemberDTO memberDTO = null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("response");
//                            Log.e("jsonObject",jsonObject.toString());

                            int count = 0;
                            String pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                                    familyParent, familyCouple, familySibling, familyChild, familyEtc,
                                    familyParent2, familyCouple2, familySibling2, familyChild2, familyEtc2,
                                    familyParent3, familyCouple3, familySibling3, familyChild3, familyEtc3,
                                    familyParent4, familyCouple4, familySibling4, familyChild4, familyEtc4,
                                    etc;
                            while (count < jsonArray.length()) {
                                JSONObject object = jsonArray.getJSONObject(count);
                                pnum = object.getString("pnum");
                                name = object.getString("name");
                                phone = object.getString("phone");
                                sex = object.getString("sex");
                                position = object.getString("position");
                                department = object.getString("department");
                                part = object.getString("part");
                                srbName = object.getString("srbName");
                                srbLeader = object.getString("srbLeader");
                                work = object.getString("work");
                                birthday = object.getString("birthday");
                                birthdayCal = object.getString("birthdayCal");
                                familyParent = object.getString("familyParent");
                                familyCouple = object.getString("familyCouple");
                                familySibling = object.getString("familySibling");
                                familyChild = object.getString("familyChild");
                                familyEtc = object.getString("familyEtc");
                                familyParent2 = object.getString("familyParent2");
                                familyCouple2 = object.getString("familyCouple2");
                                familySibling2 = object.getString("familySibling2");
                                familyChild2 = object.getString("familyChild2");
                                familyEtc2 = object.getString("familyEtc2");
                                familyParent3 = object.getString("familyParent3");
                                familyCouple3 = object.getString("familyCouple3");
                                familySibling3 = object.getString("familySibling3");
                                familyChild3 = object.getString("familyChild3");
                                familyEtc3 = object.getString("familyEtc3");
                                familyParent4 = object.getString("familyParent4");
                                familyCouple4 = object.getString("familyCouple4");
                                familySibling4 = object.getString("familySibling4");
                                familyChild4 = object.getString("familyChild4");
                                familyEtc4 = object.getString("familyEtc4");
                                etc = object.getString("etc");

                                memberDTO = new MemberDTO(pnum, name, phone, sex, position, department, part, srbName, srbLeader, work, birthday, birthdayCal,
                                        familyParent, familyCouple, familySibling, familyChild, familyEtc,
                                        familyParent2, familyCouple2, familySibling2, familyChild2, familyEtc2,
                                        familyParent3, familyCouple3, familySibling3, familyChild3, familyEtc3,
                                        familyParent4, familyCouple4, familySibling4, familyChild4, familyEtc4,
                                        etc);
                                count++;
//                              Log.e("jsonObject", object.toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(context,MemberCardActivity.class);
                        intent.putExtra("memberDTO",memberDTO);
                        context.startActivity(intent);

                    }
                };

                SearchRequest searchRequest = new SearchRequest(attDTOList.get(i).getPnum(),"","",listener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(searchRequest);


            }
        });


        //att1
        chb_att1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttUpdateRequest attUpdateRequest;
                if(chb_att1.isChecked()){
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"2",
                                    "","","",
                                    "", null);
                    Log.e("checkbox","checked");
                }else {
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"1",
                                    "","","",
                                    "",null);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attUpdateRequest);
            }
        });

        //att2
        chb_att2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttUpdateRequest attUpdateRequest;
                if(chb_att2.isChecked()){
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "2","","",
                                    "", null);
                    Log.e("checkbox","checked");

                }else {
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "1","","",
                                    "",null);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attUpdateRequest);
            }
        });


  //att3
        chb_att3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttUpdateRequest attUpdateRequest;
                if(chb_att3.isChecked()){
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","2","",
                                    "", null);
                    Log.e("checkbox","checked");


                }else {
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","1","",
                                    "",null);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attUpdateRequest);
            }
        });

        //att4
        chb_att4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttUpdateRequest attUpdateRequest;
                if(chb_att4.isChecked()){
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","2",
                                    "", null);
                    Log.e("checkbox","checked");


                }else {
                    attUpdateRequest =
                            new AttUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","1",
                                    "",null);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attUpdateRequest);
            }
        });


        return youth_list;
    }
}
