package com.qgstudio.root.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxu
 * @date 2019/6/11
 */
@Data
public class FlowCircle {
    private int day;
    private int idx;
    private Map<String,Double> timeMap=new HashMap<>();

    /**
     * init the time map
     */
    public void mapInit(){
        for (int i = 0; i <24 ; i++) {
            this.timeMap.put(""+i+"",0.0);
        }
    }
}
