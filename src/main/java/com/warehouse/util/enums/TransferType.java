package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//过户表里的过户方式
public enum TransferType {

    DIRECT_TRANSFER(80010, "直接过户"),//（直接调整库存）
    NEED_WAREHOUSE_CONFIRM(80020, "需要仓库确认");//（库仓库确认为调整）

    private Integer value;
    private String txt;

    TransferType(Integer v, String txt) {
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
        for (TransferType state : values()) {
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
        for (TransferType e : TransferType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
