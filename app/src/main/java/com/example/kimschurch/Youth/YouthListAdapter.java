package com.example.kimschurch.Youth;

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
import com.example.kimschurch.Util.YouthDTO;

import java.util.List;

public class YouthListAdapter extends BaseAdapter {

    private Context context;
    private List<YouthDTO> youthDTOList;

    public YouthListAdapter(Context context, List<YouthDTO> youthDTOList) {
        this.context = context;
        this.youthDTOList = youthDTOList;
    }

    @Override
    public int getCount() {
        return youthDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return youthDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View youth_list = View.inflate(context, R.layout.youth_list, null);

        TextView att_name = youth_list.findViewById(R.id.att_name);
        final CheckBox chb_att1 = youth_list.findViewById(R.id.chb_att1);
        CheckBox chb_att2 = youth_list.findViewById(R.id.chb_att2);
        CheckBox chb_att3 = youth_list.findViewById(R.id.chb_att3);
        CheckBox chb_att4 = youth_list.findViewById(R.id.chb_att4);
        CheckBox chb_att5 = youth_list.findViewById(R.id.chb_att5);

        chb_att1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouthUpdateRequest youthUpdateRequest;
                if(chb_att1.isChecked()){
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    youthDTOList.get(i).getPnum(),
                                    null,youthDTOList.get(i).getAtt_date(),"1",
                                    null,null,null,
                                    null,null);
                }else {
                    youthUpdateRequest =
                            new YouthUpdateRequest(
                                    youthDTOList.get(i).getPnum(),
                                    null,youthDTOList.get(i).getAtt_date(),"0",
                                    null,null,null,
                                    null,null);
                    Log.e("checkbox","unchecked");
                }
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(youthUpdateRequest);

            }
        });

        att_name.setText(youthDTOList.get(i).getName());

        youth_list.setTag(youthDTOList.get(i).getName());

        return youth_list;
    }
}
