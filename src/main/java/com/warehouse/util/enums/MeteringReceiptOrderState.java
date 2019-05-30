package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//计量单表里的订单状态
public enum MeteringReceiptOrderState {
    NEW_CREATE(11010, "新建"),
    CONFIRM(11020, "确认"),
    INVALID(-11000, "作废");

    private Integer value;
    private String txt;

    MeteringReceiptOrderState(Integer v, String txt) {
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
        for (MeteringReceiptOrderState state : values()) {
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
        for (MeteringReceiptOrderState e : MeteringReceiptOrderState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
