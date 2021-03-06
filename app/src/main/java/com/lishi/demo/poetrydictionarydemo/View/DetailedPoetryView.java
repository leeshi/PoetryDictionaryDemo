package com.lishi.demo.poetrydictionarydemo.View;

import java.util.List;

/*
 * 每个View都只能控制一个Fragment
 */
public interface DetailedPoetryView {
    void showFailedError();
    void toPoetryFragment(List<Object> list);
    void hideLoading();
    void showLoading();
    void clearView();
    void getMode();
}
