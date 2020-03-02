package com.example.kimschurch.Search;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.kimschurch.R;
import com.example.kimschurch.Util.ImageProcess;
import com.example.kimschurch.DTO.MemberDTO;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View search_list_view = View.inflate(context, R.layout.search_list,null);
        de.hdodenhof.circleimageview.CircleImageView imageView = search_list_view.findViewById(R.id.circle_image);
        TextView name = search_list_view.findViewById(R.id.rs_name);
        TextView srbName = search_list_view.findViewById(R.id.rs_srbName);
        TextView position = search_list_view.findViewById(R.id.rs_position);
        TextView department = search_list_view.findViewById(R.id.rs_department);
        TextView part = search_list_view.findViewById(R.id.rs_part);
        TextView phone = search_list_view.findViewById(R.id.rs_phone);

        ImageProcess.LoadCircleImage imageProcess = new ImageProcess.LoadCircleImage(imageView);
        imageProcess.execute(memberDTOList.get(i).getPnum());

        name.setText(memberDTOList.get(i).getName());
        srbName.setText(memberDTOList.get(i).getSrbName());
        position.setText(memberDTOList.get(i).getPosition());
        department.setText(memberDTOList.get(i).getDepartment());
        part.setText(memberDTOList.get(i).getPart());
        phone.setText(memberDTOList.get(i).getPhone());

        search_list_view.setTag(memberDTOList.get(i).getName());

        return search_list_view;
    }

}
