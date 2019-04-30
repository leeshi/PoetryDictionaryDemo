package com.lishi.demo.poetrydictionarydemo.Presenter;

public interface DetailedPoetryPresenter {
    void onCreate(String serial);
    void onResume();
    void onDestroy();
    void onItemClick(int position);
}
