package com.example.voluntnear.volunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluntnear.R;


import java.util.ArrayList;

public class AccAdapter extends RecyclerView.Adapter<AccAdapter.MyViewHolder>{
    Context context;
    ArrayList<acc_req> accList;

    public AccAdapter(Context context, ArrayList<acc_req> acclist) {
        this.context = context;
        this.accList = acclist;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView reqType;
        TextView reqLoc;
        TextView reqDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reqType = itemView.findViewById(R.id.reqType);
            reqLoc = itemView.findViewById(R.id.reqLoc);
            reqDate = itemView.findViewById(R.id.reqDate);

        }
    }

    @NonNull
    @Override
    public AccAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.accepted_items, parent, false);
        return new AccAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AccAdapter.MyViewHolder holder, int position) {
        acc_req accReq = accList.get(position);
        holder.reqType.setText(accReq.getReqtype());
        holder.reqLoc.setText(accReq.getAddr());
        holder.reqDate.setText(accReq.getDate());

    }

    @Override
    public int getItemCount() {
        return accList.size();
    }
}
