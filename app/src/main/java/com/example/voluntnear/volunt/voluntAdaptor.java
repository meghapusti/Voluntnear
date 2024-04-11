package com.example.voluntnear.volunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluntnear.R;
import com.example.voluntnear.volunt.total_req;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class voluntAdaptor extends RecyclerView.Adapter<voluntAdaptor.MyViewHolder> {

    Context context;
    ArrayList<total_req> totalreqlist;

    public voluntAdaptor(Context context, ArrayList<total_req> totalreqlist) {
        this.context = context;
        this.totalreqlist = totalreqlist;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public Object itemLayout;
        TextView voluntType;
        TextView voluntLoc;
        TextView voluntDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            voluntType = itemView.findViewById(R.id.voluntType);
            voluntLoc = itemView.findViewById(R.id.voluntLoc);
            voluntDate = itemView.findViewById(R.id.voluntDate);

        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.total_items, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        total_req totalReq = totalreqlist.get(position);
        holder.voluntType.setText(totalReq.getReqtype());
        holder.voluntLoc.setText(totalReq.getAddr());
        holder.voluntDate.setText(totalReq.getDate());

    }
    @Override
    public int getItemCount() {
        return totalreqlist.size();
    }
}