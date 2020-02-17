package com.example.kimschurch.Youth;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View youth_list = View.inflate(context, R.layout.youth_list, null);

        TextView att_name = youth_list.findViewById(R.id.att_name);
        CheckBox chb_att1 = youth_list.findViewById(R.id.chb_att1);
        CheckBox chb_att2 = youth_list.findViewById(R.id.chb_att2);
        CheckBox chb_att_srb = youth_list.findViewById(R.id.chb_att_srb);
        CheckBox chb_att_train = youth_list.findViewById(R.id.chb_att_train);
        CheckBox chb_att_visit = youth_list.findViewById(R.id.chb_att_visit);

        att_name.setText(youthDTOList.get(i).getName());
        youth_list.setTag(youthDTOList.get(i).getName());

        return youth_list;
    }
}
