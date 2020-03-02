package com.example.kimschurch.Att;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kimschurch.R;
import com.example.kimschurch.DTO.AttDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttVisitListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AttDTO> attDTOList;

    public AttVisitListAdapter(Context context, ArrayList<AttDTO> attDTOList) {
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
        View youth_list = View.inflate(context, R.layout.att_list_visit, null);

        TextView txt_att_date = youth_list.findViewById(R.id.txt_att_date);
        final CheckBox chb_att5a = youth_list.findViewById(R.id.chb_att5a);
        final CheckBox chb_att5b = youth_list.findViewById(R.id.chb_att5b);
        final CheckBox chb_att5c = youth_list.findViewById(R.id.chb_att5c);
        final EditText txt_att_etc = youth_list.findViewById(R.id.txt_att_etc);
        final Button btn_att_etc_update = youth_list.findViewById(R.id.btn_att_etc_update);


        txt_att_date.setText(attDTOList.get(i).getAtt_date());
        if(!attDTOList.get(i).getAtt_etc().equals("null"))
        txt_att_etc.setText(attDTOList.get(i).getAtt_etc());

        if(attDTOList.get(i).getAtt5a().equals("1")) { chb_att5a.setChecked(false);}
        else{chb_att5a.setChecked(true);}
        if(attDTOList.get(i).getAtt5b().equals("1")) { chb_att5b.setChecked(false);}
        else{chb_att5b.setChecked(true);}
        if(attDTOList.get(i).getAtt5c().equals("1")) { chb_att5c.setChecked(false);}
        else{chb_att5c.setChecked(true);}

        youth_list.setTag(attDTOList.get(i).getName());


        //DB 연동

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonResponse = new JSONObject(response);
//                    Log.e("jsonresponse",jsonResponse.toString());
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        //att5a
        chb_att5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttVisitUpdateRequest attVisitUpdateRequest;

                if(chb_att5a.isChecked()){
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "2","","","",listener);
                    Log.e("checkbox","checked");
                }else {
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "1","","","",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attVisitUpdateRequest);
            }
        });

        //att5b
        chb_att5b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttVisitUpdateRequest attVisitUpdateRequest;
                if(chb_att5b.isChecked()){
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "","2","","",listener);
                    Log.e("checkbox","checked");
                }else {
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "","1","","",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attVisitUpdateRequest);
            }
        });

        //att5c
        chb_att5c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttVisitUpdateRequest attVisitUpdateRequest;
                if(chb_att5c.isChecked()){
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "","","2","",listener);
                    Log.e("checkbox","checked");

                }else {
                    attVisitUpdateRequest =
                            new AttVisitUpdateRequest(
                                    attDTOList.get(i).getPnum(),
                                    "", attDTOList.get(i).getAtt_date(),
                                    "","","1","",listener);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attVisitUpdateRequest);
            }
        });

        btn_att_etc_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttVisitUpdateRequest attVisitUpdateRequest =
                        new AttVisitUpdateRequest(
                                attDTOList.get(i).getPnum(),
                                "", attDTOList.get(i).getAtt_date(),
                                "","","",txt_att_etc.getText().toString(),listener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(attVisitUpdateRequest);
            }
        });

        return youth_list;
    }
}
