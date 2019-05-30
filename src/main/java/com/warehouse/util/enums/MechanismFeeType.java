package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//企业股权 货币单位(10大主要货币单位)
public enum MechanismFeeType {

	FIRST_USE_BACK_PAYMENT(10010, "先用后付"),
	RECHARGE(10020, "充值"),
	YEAR_PAYMENT(10030, "年付");

    private Integer value;
    private String txt;

    MechanismFeeType(Integer v, String txt) {
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
        for (MechanismFeeType state : values()) {
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
        for (MechanismFeeType e : MechanismFeeType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
