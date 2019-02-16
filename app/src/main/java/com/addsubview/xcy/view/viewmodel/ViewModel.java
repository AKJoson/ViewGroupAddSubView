package com.addsubview.xcy.view.viewmodel;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends BaseViewModel {
    public List<String> subItem;

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            subItem = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                subItem.add("LinearLayout add sub view:" + i);
            }
            notifyChange();
        }
    };

    public ViewModel(AppCompatActivity activity) {
        super(activity);
        new Handler().postDelayed(runnable, 10000);//ten seconds.
    }

    public void itemClick(String itemValue) {
        Toast.makeText(mActivity, "you click the item is:" + itemValue, Toast.LENGTH_SHORT).show();
    }
}
