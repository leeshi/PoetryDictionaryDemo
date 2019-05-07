package com.lishi.demo.poetrydictionarydemo.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lishi.demo.poetrydictionarydemo.Fragment.RecyclerViewPoetryFragment;
import com.lishi.demo.poetrydictionarydemo.View.DetailedPoetryView;

import java.util.ArrayList;
import java.util.List;

public class PoetryPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private List<DetailedPoetryView> detailedPoetryViewList = new ArrayList<>();

    private final int TAPS = 4;


    public PoetryPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        //TODO 暂时设置所有页面只有一个Card
        bundle.putInt("ITEMS",1);

        Fragment mFragment =  RecyclerViewPoetryFragment.newInstance();
        mFragment.setArguments(bundle);
        fragmentList.add(mFragment);
        detailedPoetryViewList.add((DetailedPoetryView) mFragment);
        return mFragment;
    }

    @Override
    public int getCount() {
        return TAPS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
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


    public List<DetailedPoetryView> getFragmentViewList(){
        return detailedPoetryViewList;
    }
}
