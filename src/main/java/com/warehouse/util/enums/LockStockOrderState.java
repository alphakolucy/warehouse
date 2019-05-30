package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;


//锁库表里的状态类型
public enum LockStockOrderState {
    NEW_CREATE(16010, "新建"),
    LOCK(16020, "锁定"),
    UNLOCK(16030, "解锁"),
    INVALID(-16000, "作废");

    private Integer value;
    private String txt;

    LockStockOrderState(Integer v, String txt) {
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
        for (LockStockOrderState state : values()) {
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
        for (LockStockOrderState e : LockStockOrderState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}