package com.warehouse.service;

import com.warehouse.model.MeteringReceiptOrder;

import java.util.List;

public interface MeteringReceiptOrderService {
    /*
	 * 添加
	 */
    MeteringReceiptOrder add(MeteringReceiptOrder entity);

    /*
	 * 单个确认
	 */
    MeteringReceiptOrder confirm(MeteringReceiptOrder entity);

    /*
	 * 批量确认
	 */
    List<MeteringReceiptOrder> confirmList(List<MeteringReceiptOrder> entities);

}
