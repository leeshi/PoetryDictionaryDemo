package com.lishi.demo.poetrydictionarydemo.Presenter;

import android.os.Handler;

import com.lishi.demo.poetrydictionarydemo.Model.Crawler;
import com.lishi.demo.poetrydictionarydemo.Model.OnLoadListener;
import com.lishi.demo.poetrydictionarydemo.View.DetailedPoetryView;

import java.util.ArrayList;
import java.util.List;

/*
 * 此处，一个Presenter对应多个View，在对象构造时，将所有View都保存下来
 */
public class DetailedPoetryPresenterImpl implements DetailedPoetryPresenter {
    List<DetailedPoetryView> listAllFragmentView;
    Crawler DetailCrawler;
    private Handler mHandler = new Handler();


    public DetailedPoetryPresenterImpl(List<DetailedPoetryView> listAllFragmentView,Crawler DetailCrawler){
        this.listAllFragmentView = listAllFragmentView;
        this.DetailCrawler = DetailCrawler;
    }

    @Override
    public void onCreate(String serial){
        //TODO 显示正在加载
        DetailCrawler.search(serial, new OnLoadListener() {
            @Override
            public void onFinish() {
                //TODO
            }

            @Override
            public void loadFailed() {
                //TODO 使用另一个View进行操作，需要把Fragment都设置为GONE
            }

            /*
             * 此处一定要控制好每个TextView所对应的内容
             * 顺序为 title,poet,time,contson,fanyi,shangxi,background
             * 可以考虑使用HashMap使得关系更加清晰
             */
            @Override
            public void loadSuccess(List list) {
                for(int i = 0;i < listAllFragmentView.size();i++){
                    switch (i){
                        case 0:
                            mHandler.post(()-> {
                                    listAllFragmentView.get(0).toPoetryFragment(list.subList(0,1));
                            });
                            continue;
                        case 1:
                            mHandler.post(()-> {
                                listAllFragmentView.get(1).toPoetryFragment(list.subList(4, 5));
                            });
                            continue;
                        case 2:
                            mHandler.post(()-> {
                                listAllFragmentView.get(2).toPoetryFragment((list.subList(5, 6)));
                            });
                            continue;
                        case 3:
                            mHandler.post(()-> {
                                listAllFragmentView.get(3).toPoetryFragment(list.subList(6, 7));
                            });
                            continue;
                            //....more
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        //TODO 在恢复以后进行的工作
    }

    @Override
    public void onDestroy() {
        //TODO
    }

    @Override
    public void onItemClick(int position) {
        //TODO 获取点击到的信息
    }
}
