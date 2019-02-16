package com.addsubview.xcy.view.util;

import java.util.List;

public class BaseUtil {

    public static <T> boolean isListEmpty(List<T> list) {
        if (list == null || list.size() == 0)
            return true;
        return false;
    }
}
