package com.phph.x_support_lib.util;

import java.util.List;

/**
 * Created by v on 2019/12/17.
 */
public final class ListUtils {

    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

}
