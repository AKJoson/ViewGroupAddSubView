package com.addsubview.xcy.view.util;

import com.addsubview.xcy.view.BaseApplication;

import java.util.List;

public class BaseUtil {

    public static <T> boolean isListEmpty(List<T> list) {
        if (list == null || list.size() == 0)
            return true;
        return false;
    }

    public static int dp2Px(int dp) {
        float scale = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);

    }
}
