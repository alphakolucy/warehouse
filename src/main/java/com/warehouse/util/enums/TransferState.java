package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//过户表里的过户状态
public enum TransferState {

	NEW_CREATE(70010, "新建"),
    HAD_TRANSFER(70020, "已过户"),
    INVALID(-70000, "作废");

    private Integer value;
    private String txt;

    TransferState(Integer v, String txt) {
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
        for (TransferState state : values()) {
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
        for (TransferState e : TransferState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
