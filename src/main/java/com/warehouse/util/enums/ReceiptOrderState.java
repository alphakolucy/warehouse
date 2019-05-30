package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//收发货单表里的订单状态
public enum ReceiptOrderState {
    NEW_CREATE(90010, "新建"),
    ALLOCATION(90011, "已分配"),
    IN_METERING(90020, "计量中"),
    HAD_METERING(90030, "已计量"),
    CONFIRM(90040, "已确认"),
    SETTLEMENT(90050, "已结算"),//暂时没用到
    PASS(90060, "已放行"),
    INVALID(-90000, "作废");

    private Integer value;
    private String txt;

    ReceiptOrderState(Integer v, String txt) {
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
        for (ReceiptOrderState state : values()) {
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
        for (ReceiptOrderState e : ReceiptOrderState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
