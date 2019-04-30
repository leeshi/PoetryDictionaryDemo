package com.lishi.demo.poetrydictionarydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.lishi.demo.poetrydictionarydemo.Fragment.RecyclerViewPoetryFragment;
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
    List<DetailedPoetryView> listFragmentView = new ArrayList<>();
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

        ViewPager viewPager = mViewPager.getViewPager();
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            private List<Fragment> fragmentList = new ArrayList<>();
            private FragmentManager fragmentManager = getSupportFragmentManager();

            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                //TODO 暂时设置所有页面只有一个Card
                bundle.putInt("ITEMS",1);

                switch (position % TAPS) {
                    //进行强制类型转换
                    case 0:
                        Fragment mFragment =  RecyclerViewPoetryFragment.newInstance();
                        mFragment.setArguments(bundle);
                        listFragmentView.add((DetailedPoetryView) mFragment);
                        fragmentList.add(mFragment);
                        return mFragment;
                    default:
                        Fragment nFragment =  RecyclerViewPoetryFragment.newInstance();
                        nFragment.setArguments(bundle);
                        listFragmentView.add((DetailedPoetryView) nFragment);
                        fragmentList.add(nFragment);
                        return nFragment;
                }
            }

            @Override
            public int getCount() {
                return TAPS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position ){
                    case 0:
                        return "诗词";
                    case 1:
                        return "译文及注释";
                    case 2:
                        return "赏析";
                    case 3:
                        return "背景故事";
                        default:
                            return "猜你喜欢";

                }
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Fragment fragment = (Fragment) super.instantiateItem(container, position);
                fragmentManager.beginTransaction().show(fragment).commit();
                return fragment;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Fragment fragment = fragmentList.get(position);
                fragmentManager.beginTransaction().hide(fragment).commit();
            }

        });
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        myPresenter = new DetailedPoetryPresenterImpl(listFragmentView,new DetailCrawlerImpl());
        myPresenter.onCreate("a5b8eb647c8f");
    }

}
