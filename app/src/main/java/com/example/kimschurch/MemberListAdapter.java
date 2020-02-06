package com.example.kimschurch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MemberListAdapter extends BaseAdapter{

    private Context context;
    private List<MemberDTO> memberDTOList;

    public MemberListAdapter(Context context, List<MemberDTO> memberDTOList) {
        this.context = context;
        this.memberDTOList = memberDTOList;
    }

    @Override
    public int getCount() {
        return memberDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return memberDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.search_list_layout,null);
        de.hdodenhof.circleimageview.CircleImageView imageView = v.findViewById(R.id.circle_image);
        TextView name = v.findViewById(R.id.rs_name);
        TextView srbName = v.findViewById(R.id.rs_srbName);
        TextView position = v.findViewById(R.id.rs_position);
        TextView department = v.findViewById(R.id.rs_department);
        TextView part = v.findViewById(R.id.rs_part);
        TextView phone = v.findViewById(R.id.rs_phone);
        ImageProcess.LoadImage imageProcess = new ImageProcess.LoadImage(imageView);
        imageProcess.execute("http://172.30.1.50/upload/jans01098561121.png");


        name.setText(memberDTOList.get(i).getName());
        srbName.setText(memberDTOList.get(i).getSrbName());
        position.setText(memberDTOList.get(i).getPosition());
        department.setText(memberDTOList.get(i).getDepartment());
        part.setText(memberDTOList.get(i).getPart());
        phone.setText(memberDTOList.get(i).getPhone());

        v.setTag(memberDTOList.get(i).getName());

        return v;
    }

}
