package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//客户表里的计重方式
public enum WeightMethod {

	EXCEPT_WEIGHT(30010, "理重"),
	FACT_WEIGHT(30020, "实重");

    private Integer value;
    private String txt;

    WeightMethod(Integer v, String txt) {
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
        for (WeightMethod state : values()) {
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
        for (WeightMethod e : WeightMethod.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
