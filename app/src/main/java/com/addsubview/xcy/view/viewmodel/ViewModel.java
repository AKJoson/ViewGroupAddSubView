package com.addsubview.xcy.view.viewmodel;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends BaseViewModel {
    public List<String> subItem;
    public List<String> dayItem;
    public List<Integer> monthItem;
    /*
    0 in current data to add new data,
    1 update the forground three data .
    2
     */
    private int status;

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            switch (status) {
                case 0:
                    subItem = new ArrayList<>(10);
                    for (int i = 0; i < 2; i++) {
                        subItem.add("LinearLayout add sub view:" + i);
                    }
                    ++status;
                    break;
                case 1:
                    if (subItem == null)
                        subItem = new ArrayList<>(3);
                    subItem.clear();
                    subItem.add("new data 1");
                    subItem.add("new data 2");
                    subItem.add("new data 3");
                    ++status;
                    break;
                case 2:
                    if (dayItem == null)
                        dayItem = new ArrayList<>(7);
                    dayItem.clear();
                    for (int i = 1; i <= 7; i++) {
                        dayItem.add("星期" + i);
                    }
                    ++status;
                    break;
                case 3:
                    if (monthItem == null)
                        monthItem = new ArrayList<>(32);
                    for (int i = 1; i < 32; i++) {
                        monthItem.add(i);
                    }
                    ++status;
                    break;
            }
            notifyChange();
        }
    };

    public ViewModel(AppCompatActivity activity) {
        super(activity);
        new Handler().postDelayed(runnable, 5000);// LinearLayout  Vertical
        new Handler().postDelayed(runnable, 10000);//LinearLayout   update test
        new Handler().postDelayed(runnable, 15000); //LinearLayout   HORIZONTAL
        new Handler().postDelayed(runnable, 20000); // flowlayout
    }

    public void itemClick(String itemValue) {
        Toast.makeText(mActivity, "you click the item is:" + itemValue, Toast.LENGTH_SHORT).show();
    }
}
