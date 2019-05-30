package com.warehouse.util.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusinessNo {

    /**
     * 业务生成单号
     *
     * @param bCode 类型，如：库存调整（KCTZ）
     * @return
     */
    public static String getBusinessNo(String bCode) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = bCode + format.format((new Date()).getTime())
                + RandomUtil.toFixdLengthString((int) ((Math.random() * 9 + 1) * 100000), 6);
        return orderNo;
    }
}
