package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//计费方式表里的收费类型
public enum CostSettinsOpType {
	IN_STOCK(40010, "入库"),
	OUT_STOCK(40020, "出库"),
    PROCESS(40030, "加工"),
	REMOVE_STOCK(40040, "移库"),
    BUTT_JOINT(40050, "对接"),
    TRANSFER_NAME(40060, "过户"),
	TRAY(40070, "托盘"),
    STORAGE(40080, "仓储");

    private Integer value;
    private String txt;

    CostSettinsOpType(Integer v, String txt) {
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
        for (CostSettinsOpType state : values()) {
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
        for (CostSettinsOpType e : CostSettinsOpType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
