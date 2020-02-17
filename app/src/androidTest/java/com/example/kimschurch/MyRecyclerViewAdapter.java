package com.example.kimschurch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kimschurch.Util.MemberDTO;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MemberDTO> memberDTOS= new ArrayList<>();

    public MyRecyclerViewAdapter(ArrayList<MemberDTO> memberDTOS) {

       this.memberDTOS = memberDTOS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //XML

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search,parent,false);

        return new RowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RowCell)holder).name.setText(memberDTOS.get(position).getName());
        ((RowCell)holder).srbName.setText(memberDTOS.get(position).getSrbName());
        ((RowCell)holder).phone.setText(memberDTOS.get(position).getPhone());

    }

    @Override
    public int getItemCount() {

        return memberDTOS.size();
    }

    private static class RowCell extends RecyclerView.ViewHolder {

        TextView name;
        TextView srbName;
        TextView phone;


        public RowCell(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.rs_name);
            srbName =(TextView) view.findViewById(R.id.rs_srbName);
            phone = (TextView)view.findViewById(R.id.rs_phone);

        }
    }
}
