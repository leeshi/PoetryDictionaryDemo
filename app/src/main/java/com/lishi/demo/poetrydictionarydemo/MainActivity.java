package com.lishi.demo.poetrydictionarydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.lishi.demo.poetrydictionarydemo.Adapter.PoetryPagerAdapter;
import com.lishi.demo.poetrydictionarydemo.Model.DetailCrawlerImpl;
import com.lishi.demo.poetrydictionarydemo.Presenter.DetailedPoetryPresenter;
import com.lishi.demo.poetrydictionarydemo.Presenter.DetailedPoetryPresenterImpl;
import com.lishi.demo.poetrydictionarydemo.View.DetailedPoetryView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private MaterialViewPager mViewPager;
    Map<Integer,Integer> mapCardPerPage = new HashMap<>();
    static final int TAPS = 5;
    //Presenter
    DetailedPoetryPresenter myPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        mapCardPerPage.put(0,1);

        //添加监听
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_50.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.lime,
                            "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }
                return null;
            }
        });

        //添加toolbar
        Toolbar toolbar = mViewPager.getToolbar();


        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        PoetryPagerAdapter poetryPagerAdapter = new PoetryPagerAdapter(getSupportFragmentManager());
        mViewPager.getViewPager().setAdapter(poetryPagerAdapter);

        //默认加载所有Fragment，以便Presenter更新数据
        mViewPager.getViewPager().setOffscreenPageLimit(5);

        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        myPresenter = new DetailedPoetryPresenterImpl(poetryPagerAdapter.getFragmentViewList(),new DetailCrawlerImpl());
        myPresenter.onCreate("ed3504a25e60");
    }

}
