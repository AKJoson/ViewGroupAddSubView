package com.addsubview.xcy.view.viewmodel;

import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;

public class BaseViewModel extends BaseObservable {
    protected AppCompatActivity mActivity;

    BaseViewModel(AppCompatActivity activity) {
        mActivity = activity;
    }
}
