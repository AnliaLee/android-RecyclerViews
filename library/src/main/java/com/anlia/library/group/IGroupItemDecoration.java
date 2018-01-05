package com.anlia.library.group;

import android.content.Context;

/**
 * Created by anlia on 2018/1/4.
 */

public interface IGroupItemDecoration {
    public Context getContext();
    /**
     * 判断当前点击位置是否处于GroupItem区域
     * @param x
     * @param y
     * @return
     */
    public GroupItem findGroupItemUnder(int x, int y);
}
