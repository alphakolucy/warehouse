package com.warehouse.util.enums;

import com.warehouse.util.tools.EmptyUtil;

//仓库库存日志表里操作单类型
public enum WarehouseStockLogOpType {
    TAKE_OVER(15010, "入库"),
    CARRY_GOOD(15020, "提货"),
    REMOVE_STOCK(15030, "移库"),
    TRANSFER_NAME(15040, "过户"),
    ADJUST_NAME(15050, "调整"),
    INVENTORY_NAME(15060, "盘库");


    private Integer value;
    private String txt;

    WarehouseStockLogOpType(Integer v, String txt) {
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
        for (WarehouseStockLogOpType state : values()) {
            if (state.getValue().equals(value)) {
                return state.getTxt();
            }
        }
        return "";
    }

    /**
     * value是否存在此枚举中
     *
     * @param value
     * @return boolean
     */
    public static boolean isExist(Integer value) {
        if (EmptyUtil.isEmpty(value)) {
            return false;
        }
        for (WarehouseStockLogOpType e : WarehouseStockLogOpType.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
