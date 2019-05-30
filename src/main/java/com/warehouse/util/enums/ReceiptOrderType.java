package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//收发货表里的订单类型
public enum ReceiptOrderType {
	IN_STOCK(14010, "入库"),
	OUT_STOCK(14020, "出库");

    private Integer value;
    private String txt;

    ReceiptOrderType(Integer v, String txt) {
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
        for (ReceiptOrderType state : values()) {
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
        for (ReceiptOrderType e : ReceiptOrderType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
