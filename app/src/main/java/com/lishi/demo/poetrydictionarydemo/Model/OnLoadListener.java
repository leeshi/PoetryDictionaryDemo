package com.lishi.demo.poetrydictionarydemo.Model;

import java.util.List;

public interface OnLoadListener {
    void onFinish();
    void loadFailed();
    void loadSuccess(List list);
}
