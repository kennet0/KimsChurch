package com.example.kimschurch.Util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.Util.AttDTO;
import com.example.kimschurch.Youth.YouthUpdateRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        View youth_list = View.inflate(context, R.layout.list_att, null);

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
        if(attDTOList.get(i).getAtt4().equals("1")) { chb_att4.setChecked(false);}
        else{chb_att4.setChecked(true);}
        if(attDTOList.get(i).getAtt5().equals("1")) { chb_att5.setChecked(false);}
        else{chb_att5.setChecked(true);}
        youth_list.setTag(attDTOList.get(i).getName());


        //DB 연동

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.e("response",jsonResponse.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //att1
        chb_att1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YouthUpdateRequest youthUpdateRequest;
                if(chb_att1.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"2",
                                    "","","",
                                    "", listener);
                    Log.e("checkbox","checked");
                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"1",
                                    "","","",
                                    "",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);
            }
        });

        //att2
        chb_att2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YouthUpdateRequest youthUpdateRequest;
                if(chb_att2.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "2","","",
                                    "", listener);
                    Log.e("checkbox","checked");

                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "1","","",
                                    "",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);
            }
        });


        //att3
        chb_att3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YouthUpdateRequest youthUpdateRequest;
                if(chb_att3.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","2","",
                                    "", listener);
                    Log.e("checkbox","checked");


                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","1","",
                                    "",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);
            }
        });

        //att4
        chb_att4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YouthUpdateRequest youthUpdateRequest;
                if(chb_att4.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","2",
                                    "", listener);
                    Log.e("checkbox","checked");


                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","1",
                                    "",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);
            }
        });

        //att5
        chb_att5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YouthUpdateRequest youthUpdateRequest;
                if(chb_att5.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","",
                                    "2", listener);
                    Log.e("checkbox","checked");


                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),"",
                                    "","","",
                                    "1",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);
            }
        });





        return youth_list;
    }
}
