package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;


//仓库移动表里的状态类型
public enum MoveStockOrderState {
    NEW_CREATE(50010, "新建"),
    CONFIRM(50020, "确认"),
    INVALID(-50000, "作废");

    private Integer value;
    private String txt;

    MoveStockOrderState(Integer v, String txt) {
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
        for (MoveStockOrderState state : values()) {
            if (state.getValue().equals(value)) {
                return state.getTxt();
            }
        }
        return "";
    }

    /**
     * value是否存在此枚举中
     *
     * @param value
     * @return boolean
     */
    public static boolean isExist(Integer value) {
        if (EmptyUtil.isEmpty(value)) {
            return false;
        }
        for (MoveStockOrderState e : MoveStockOrderState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}