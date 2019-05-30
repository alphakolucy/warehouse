package com.warehouse.service;

import com.warehouse.model.Customer;
import com.warehouse.model.MeteringReceiptOrder;
import com.warehouse.model.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptService {
    /**
     * 收费确认（同时修改库存）
     * @param entity 实体
     * @param opNum 操作数量(+正数,-负数)
     * @param opTonNum 操作吨数(+正数,-负数)
     * @param opMan 操作人
     * @param opManId 操作人Id
     * @return
     */
    Receipt feeConfirm(Receipt entity,
                       BigDecimal opNum,
                       BigDecimal opTonNum,
                       String opMan,
                       String opManId,
                       Customer newCustomer,
                       List<MeteringReceiptOrder> confirmOrders);

    /**
     * 添加
     * @param entity
     * @return
     */
    Receipt add(Receipt entity);

}
