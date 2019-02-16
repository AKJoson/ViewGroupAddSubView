package com.addsubview.xcy.view.view;

import android.databinding.BindingAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.addsubview.xcy.databinding.ActivityMainBinding;
import com.addsubview.xcy.databinding.SubViewLayoutBinding;
import com.addsubview.xcy.view.util.BaseUtil;
import com.addsubview.xcy.view.viewmodel.ViewModel;

import java.util.List;

/**
 * practice how to add subView to ViewGroup.
 * time:2019.02.14 22:35
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setViewModel(new ViewModel(MainActivity.this));
        setContentView(binding.getRoot());
    }

    @BindingAdapter(value = {"subItem", "viewModel"}, requireAll = false)
    public static void setSubItem(LinearLayout linearLayout, List<String> subItem,ViewModel viewModel) {
        if (linearLayout != null) {
            //so you should clear the LinearLayout
            if (BaseUtil.isListEmpty(subItem))
                linearLayout.removeAllViews();
            else {
                for (int i = 0; i < subItem.size(); i++) {
                    SubViewLayoutBinding binding = SubViewLayoutBinding.inflate(LayoutInflater.from(linearLayout.getContext()), linearLayout, false);
                    binding.setSubItem(subItem.get(i));
                    binding.setViewModel(viewModel);
                    linearLayout.addView(binding.getRoot());
                }
            }
        }
    }
}
