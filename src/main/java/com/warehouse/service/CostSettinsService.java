package com.warehouse.service;

import com.warehouse.model.Customer;
import com.warehouse.model.ProductCates;
import com.warehouse.util.enums.CostSettinsOpType;

import java.util.Map;

public interface CostSettinsService {
    /**
     * 获取计费单价
     * @param customerId 客户Id
     * @param cateId 分类Id
     * @param costSettinsOpType 收费类型枚举
     * @return map status:yes message:未找到费用设置 unitPrice:28 feePurpose:13010
     */
    Map<String,Object> getUnitPrice(String customerId, String cateId, CostSettinsOpType costSettinsOpType);

}
