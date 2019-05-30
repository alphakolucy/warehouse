package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//计费方式表里的计费方式
public enum CostSettinsChargeUnit {
	TON(12010, "吨"),
    VEHICLE(12020, "车");

    private Integer value;
    private String txt;

    CostSettinsChargeUnit(Integer v, String txt) {
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
        for (CostSettinsChargeUnit state : values()) {
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
        for (CostSettinsChargeUnit e : CostSettinsChargeUnit.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
