package com.anlia.library.group;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anlia on 2017/12/28.
 */

public class GroupItem {
    private int startPosition;//起始position
    private Map<String,Object> dataMap;

    public GroupItem(int startPosition){
        this.startPosition = startPosition;
        dataMap = new HashMap<>();
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setData(String key,Object data){
        dataMap.put(key,data);
    }

    public Object getData(String key){
        return dataMap.get(key);
    }
}
