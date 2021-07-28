package com.comp.track.plasck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class graphadapter extends RecyclerView.Adapter<graphadapter.Viewholdergraph> {
    private ArrayList<String> usage;
    private Context ordercontext;

    public graphadapter(ArrayList<String> namelist, Context context) {
        this.usage = namelist;
        ordercontext = context;
    }

    @NonNull
    @Override
    public graphadapter.Viewholdergraph onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgraph,parent, false);
        Viewholdergraph holder = new Viewholdergraph(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull graphadapter.Viewholdergraph holder, int position) {
        int height = Integer.parseInt(usage.get(position))/2;
        holder.usage.getLayoutParams().height = Integer.parseInt(usage.get(position));
        holder.day.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return usage.size();
    }

    public class Viewholdergraph extends RecyclerView.ViewHolder {
        Button usage;
        TextView day;
        public Viewholdergraph(@NonNull View itemView) {
            super(itemView);
            usage = itemView.findViewById(R.id.usage);
            day = itemView.findViewById(R.id.day);
        }
    }
}
