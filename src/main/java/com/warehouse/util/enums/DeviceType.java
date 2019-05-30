package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;


//设备维护台账表里的设备类型
public enum DeviceType {
    CRANE(17010, "龙门吊"),
    STEELYARD(17020, "秤");

    private Integer value;
    private String txt;

    DeviceType(Integer v, String txt) {
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
        for (DeviceType state : values()) {
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
        for (DeviceType e : DeviceType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}