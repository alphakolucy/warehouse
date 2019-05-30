package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//运输方式
public enum TransportMode {
    MYSELF_TAKE(18010, "自提"),
    ENTRUST_TRANSPORT(18020, "委托运输");

    private Integer value;
    private String txt;

    TransportMode(Integer v, String txt) {
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
        for (TransportMode state : values()) {
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
        for (TransportMode e : TransportMode.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
