package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

public enum TransState {
	NEW_CREATE(90010, "已到站"), 
    ALLOCATION(90011, "已到库"),
    IN_METERING(90020, "卸货中"), 
    HAD_METERING(90030, "卸货中"),
    CONFIRM(90040, "报空") ;

    private Integer value;
    private String txt;

    TransState(Integer v, String txt) {
        this.value = v;
        this.txt = txt;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getTxt() {
        return this.txt;
    }
    
    public static String getTxtByValue(Integer value) {
        for (TransState state : values()) {
            if (state.getValue().equals(value)) {
                return state.getTxt();
            }  
        }  
        return "";
    }

    /**
     * value是否存在此枚举中
     * @param value
     * @return boolean
     */
    public static boolean isExist(Integer value) {
        if(EmptyUtil.isEmpty(value)){
            return false;
        }
        for (TransState e : TransState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
