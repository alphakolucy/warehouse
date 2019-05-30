package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//委托单表里的订单状态
public enum EntrustOrderState {
    NEW_CREATE(50010, "新建"),
    CONFIRM(50020, "确认"),
//    IN_STOCK(50030, "入库"),
//    OUT_STOCK(50040, "出库"),
    FINISH(50050, "完成"),
    RECEIVING(50060, "入库中"),
    RECEIVE_FINISH(50070, "入库完成"),
    ABOLISH(50080, "废止"),
    SENDING(50090, "发货中"),
    SEND_FINISH(50100, "发货完成"),
    INVALID(-50000, "作废");

    private Integer value;
    private String txt;

    EntrustOrderState(Integer v, String txt) {
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
        for (EntrustOrderState state : values()) {
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
        for (EntrustOrderState e : EntrustOrderState.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
