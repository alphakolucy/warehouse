package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//客户表里的结算方式
public enum SettlementMethod {

	MONTH_SETTLEMENT(20010, "月结"),
	NOW_SETTLEMENT(20020, "现结");

    private Integer value;
    private String txt;

    SettlementMethod(Integer v, String txt) {
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
        for (SettlementMethod state : values()) {
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
        for (SettlementMethod e : SettlementMethod.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
