package com.lishi.demo.poetrydictionarydemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.lishi.demo.poetrydictionarydemo.Adapter.RecyclerViewPoetryPagerAdapter;
import com.lishi.demo.poetrydictionarydemo.R;
import com.lishi.demo.poetrydictionarydemo.View.DetailedPoetryView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPoetryFragment extends Fragment implements DetailedPoetryView {
    public static Fragment newInstance(){return  new RecyclerViewPoetryFragment();}
    final List<Object> items = new ArrayList<>();
    private RecyclerView.Adapter mViewPagerAdapter;
    //static final int ITEMS = 6;
    //添加自定义数目

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        int ITEMS = getArguments().getInt("ITEMS");
        for (int i=0;i<ITEMS;i++){
            items.add(new Object());
        }

        mViewPagerAdapter = new RecyclerViewPoetryPagerAdapter(items);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(mViewPagerAdapter);
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void toPoetryFragment(Object object) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void clearView() {

    }

    @Override
    public void getMode() {

    }
}
