package com.lishi.demo.poetrydictionarydemo.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lishi.demo.poetrydictionarydemo.R;

import java.util.ArrayList;
import java.util.List;

/*
 * RecyclerViewPoetryPagerAdapter用于根据文本数据进行TextView的更新操作
 * 每个Fragment对应一个Adapter，因此我们只需要在对应的View中调用更新页面的方法
 * 实际上，并没有必要对数据进行更新，因为文本信息从一开始就注定是不变的，isn't it?
 * 还是说需要保存这些Fragment，以便下一次可以直接通过更新数据调用！！
 * Yes
 * **一个Presenter对应多个Adapter**
 */
public class RecyclerViewPoetryPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Object> contents;
    //每一个CardView对应一个TextView
    List<TextView> listTextView = new ArrayList<>();
    public RecyclerViewPoetryPagerAdapter(List<Object> contents) {
        this.contents = contents;
    }
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate CardView
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_cardview, parent, false);

        //获取每个CardView的TextView并保存起来
        TextView tv = view.findViewById(R.id.firstTextView);
        listTextView.add(tv);

        return new RecyclerView.ViewHolder(view) {
        };

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /*
     * 对数据进行更新，利用textView.setText方法，从这个角度来讲，不必使用notifyDataChanged
     * 必须对数据进行对齐
     * @param
     *        List<String>
     * @return void
     */
    public void update(List<String> data){
        for(int i = 0;i < data.size();i++){
            listTextView.get(i).setText(data.get(i));
        }
    }

}