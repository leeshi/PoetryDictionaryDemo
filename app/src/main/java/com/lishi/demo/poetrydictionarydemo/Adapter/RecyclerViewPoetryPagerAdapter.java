package com.lishi.demo.poetrydictionarydemo.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lishi.demo.poetrydictionarydemo.R;

import java.util.List;

public class RecyclerViewPoetryPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Object> contents;
    public RecyclerViewPoetryPagerAdapter(List<Object> contents) {
        this.contents = contents;
    }
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_cardview, parent, false);

        TextView tv = view.findViewById(R.id.firstTextView);

        return new RecyclerView.ViewHolder(view) {
        };

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}