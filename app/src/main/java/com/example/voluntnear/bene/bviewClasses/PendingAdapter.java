package com.example.voluntnear.bene.bviewClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluntnear.R;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {

    Context context;
    ArrayList<pending_req> pendinglist;

    public PendingAdapter(Context context, ArrayList<pending_req> pendinglist) {
        this.context = context;
        this.pendinglist = pendinglist;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pendType;
        TextView pendLoc;
        TextView pendDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pendType = itemView.findViewById(R.id.pendType);
            pendLoc = itemView.findViewById(R.id.pendLoc);
            pendDate = itemView.findViewById(R.id.pendDate);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pending_items, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pending_req pendingReq = pendinglist.get(position);
        holder.pendType.setText(pendingReq.getReqtype());
        holder.pendLoc.setText(pendingReq.getAddr());
        holder.pendDate.setText(pendingReq.getDate());

    }

    @Override
    public int getItemCount() {
        return pendinglist.size();
    }
}
