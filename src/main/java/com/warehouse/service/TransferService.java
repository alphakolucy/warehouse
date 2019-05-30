package com.warehouse.service;

import com.warehouse.model.Transfer;
import org.springframework.stereotype.Service;

public interface TransferService {
    /**
     * 仓库确认（同时修改库存）
     * @param entity 过户实体
     * @param opMan 操作人
     * @param opManId 操作人Id
     * @return
     */
    Transfer warehouseConfirm(Transfer entity, String opMan, String opManId);

    /**
     * 过户添加
     * @param entity 过户实体
     * @return
     */
    Transfer add(Transfer entity) throws Exception;

}
