package com.lishi.demo.poetrydictionarydemo.View;

import java.util.List;

public interface DetailedPoetryView {
    void showFailedError();
    void toPoetryFragment(List list);
    void hideLoading();
    void showLoading();
    void clearView();
    void getMode();
}
