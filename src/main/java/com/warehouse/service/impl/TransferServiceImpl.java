package com.warehouse.service.impl;

import com.warehouse.model.Transfer;
import com.warehouse.model.TransferDetail;
import com.warehouse.model.WarehouseStock;
import com.warehouse.service.TransferService;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.TransferRepository;
import com.warehouse.util.enums.TransferType;
import com.warehouse.util.enums.WarehouseStockLogOpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private WarehouseStockService warehouseStockService;

    @Transactional
    @Override
    public Transfer warehouseConfirm(Transfer entity, String opMan, String opManId) {
        //修改库存
        List<WarehouseStock> warehouseStockList = new ArrayList<>();
        Set<TransferDetail> transferDetailSet = entity.getTransferDetailSet();
        Iterator<TransferDetail> iterator = transferDetailSet.iterator();
        while (iterator.hasNext()){
            //1.减去 "从客户" 物资库存
            TransferDetail detail = iterator.next();
            WarehouseStock warehouseStockFrom = new WarehouseStock();
            warehouseStockFrom.setWarehouseLevel(detail.getWarehouseStock().getWarehouseLevel());//库层
            warehouseStockFrom.setProduct(detail.getWarehouseStock().getProduct());
            warehouseStockFrom.setCustomer(entity.getFromCustomer());
            warehouseStockFrom.setNum(detail.getNum().negate());//通过negate函数转为负数
            warehouseStockFrom.setTonNum(detail.getTonNum().negate());//通过negate函数转为负数
            warehouseStockFrom.setCreateMan(opMan);
            warehouseStockFrom.setCreateManId(opManId);

            //2.增加 "到客户" 物资库存
            WarehouseStock warehouseStockTo = new WarehouseStock();
            warehouseStockTo.setWarehouseLevel(detail.getWarehouseStock().getWarehouseLevel());//库层
            warehouseStockTo.setProduct(detail.getWarehouseStock().getProduct());
            warehouseStockTo.setCustomer(entity.getToCustomer());
            warehouseStockTo.setNum(detail.getNum());
            warehouseStockTo.setTonNum(detail.getTonNum());
            warehouseStockTo.setCreateMan(opMan);
            warehouseStockTo.setCreateManId(opManId);

            warehouseStockList.add(warehouseStockFrom);
            warehouseStockList.add(warehouseStockTo);
        }

        Boolean result = warehouseStockService.saveOrUpdate(warehouseStockList,
                entity.getOrderNo(), entity.getId(),
                WarehouseStockLogOpType.TRANSFER_NAME.getValue());

        if(result){
            return this.transferRepository.save(entity);
        }

        return null;
    }

    @Override
    public Transfer add(Transfer entity) throws Exception{
        return this.transferRepository.save(entity);
    }
}
