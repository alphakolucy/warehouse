package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//计费方式表里的费用用途
public enum CostSettinsFeePurpose {
    THEORY(13010, "理重"),
    FACT(13020, "实重");

    private Integer value;
    private String txt;

    CostSettinsFeePurpose(Integer v, String txt) {
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
        for (CostSettinsFeePurpose state : values()) {
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
        for (CostSettinsFeePurpose e : CostSettinsFeePurpose.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
