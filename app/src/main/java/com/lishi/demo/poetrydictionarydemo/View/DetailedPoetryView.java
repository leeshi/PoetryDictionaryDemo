package com.lishi.demo.poetrydictionarydemo.View;

public interface DetailedPoetryView {
    void showFailedError();
    void toPoetryFragment(Object object);
    void hideLoading();
    void showLoading();
    void clearView();
    void getMode();
}
