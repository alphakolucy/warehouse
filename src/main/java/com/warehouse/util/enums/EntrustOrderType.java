package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//委托单表里的订单类型
public enum EntrustOrderType {
	RECEIVE_GOODS(60010, "入库"),
	TAKE_GOODS(60020, "出库"),
	BUTT_JOINT(60030, "对接");

    private Integer value;
    private String txt;

    EntrustOrderType(Integer v, String txt) {
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
        for (EntrustOrderType state : values()) {
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
        for (EntrustOrderType e : EntrustOrderType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
