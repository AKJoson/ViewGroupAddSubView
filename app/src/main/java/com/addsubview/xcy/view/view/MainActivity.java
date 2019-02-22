package com.addsubview.xcy.view.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.addsubview.xcy.databinding.ActivityMainBinding;
import com.addsubview.xcy.databinding.DayViewLayoutBinding;
import com.addsubview.xcy.databinding.SubViewLayoutBinding;
import com.addsubview.xcy.view.BaseApplication;
import com.addsubview.xcy.view.flowlayout.FlowLayout;
import com.addsubview.xcy.view.util.BaseUtil;
import com.addsubview.xcy.view.viewmodel.ViewModel;

import java.util.List;

/**
 * practice how to add subView to ViewGroup.
 * time:2019.02.14 22:35
 */
public class MainActivity extends AppCompatActivity {
    private AppCompatActivity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setViewModel(new ViewModel(MainActivity.this));
        setContentView(binding.getRoot());
    }

    @BindingAdapter(value = {"subItem", "viewModel"}, requireAll = false)
    public static void setSubItem(LinearLayout linearLayout, List<String> subItem, ViewModel viewModel) {
        if (linearLayout != null) {
            //so you should clear the LinearLayout
            if (BaseUtil.isListEmpty(subItem))
                linearLayout.removeAllViews();
            else {
                if (linearLayout.getChildCount() == 0) {
                    //not add sub View ,first add
                    for (int i = 0; i < subItem.size(); i++) {
                        SubViewLayoutBinding binding = SubViewLayoutBinding.inflate(LayoutInflater.from(linearLayout.getContext()), linearLayout, false);
                        binding.setSubItem(subItem.get(i));
                        //you can not add the viewModel,because this is replace.not new create
                        //  binding.setViewModel(viewModel);
                        linearLayout.addView(binding.getRoot());
                    }
                } else if (linearLayout.getChildCount() > subItem.size()) {
                    //you update data size less than now.
                    for (int i = 0; i < subItem.size(); i++) {
                        SubViewLayoutBinding binding = DataBindingUtil.getBinding(linearLayout.getChildAt(i));
                        binding.setViewModel(viewModel);
                        binding.setSubItem(subItem.get(i));
                        binding.executePendingBindings();
                    }
                    linearLayout.removeViews(subItem.size(), linearLayout.getChildCount() - subItem.size());
                } else {//you update data size more than now.
                    int childCount = linearLayout.getChildCount();
                    for (int i = 0; i < subItem.size(); i++) {
                        if (i < childCount) {
                            //replace current view data
                            SubViewLayoutBinding binding = DataBindingUtil.getBinding(linearLayout.getChildAt(i));
                            binding.setSubItem(subItem.get(i));
                        } else {
                            //add new view data
                            SubViewLayoutBinding binding = SubViewLayoutBinding.inflate(LayoutInflater.from(linearLayout.getContext()), linearLayout, false);
                            binding.setSubItem(subItem.get(i));
                            binding.setViewModel(viewModel);
                            linearLayout.addView(binding.getRoot());
                        }
                    }
                }

            }
        }
    }

    @BindingAdapter(value = {"dayItem", "viewModel"}, requireAll = true)
    public static void setDayItem(LinearLayout layout, List<String> dayList, ViewModel viewModel) {
        if (layout == null)
            return;
        if (dayList != null) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(layout.getContext().getResources().getDisplayMetrics().widthPixels / 7, ViewGroup.LayoutParams.MATCH_PARENT);
            for (String s : dayList) {
                DayViewLayoutBinding binding = DayViewLayoutBinding.inflate(LayoutInflater.from(layout.getContext()), layout, false);
                binding.setDayItem(s);
                layout.addView(binding.getRoot(), layoutParams);
            }

        }
    }

    @BindingAdapter(value = {"monthItem", "viewModel"}, requireAll = true)
    public static void setFlowItem(FlowLayout flowLayout, List<Integer> itemList, ViewModel viewModel) {
        if (flowLayout == null)
            return;
        if (itemList != null) {
            FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(flowLayout.getContext().getResources().getDisplayMetrics().widthPixels / 4, BaseUtil.dp2Px(30));
            for (int item : itemList) {
                DayViewLayoutBinding binding = DayViewLayoutBinding.inflate(LayoutInflater.from(flowLayout.getContext()), flowLayout, false);
                binding.setDayItem(String.valueOf(item));
                flowLayout.addView(binding.getRoot(), layoutParams);
            }
        }

    }
}
